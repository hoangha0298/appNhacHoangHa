package com.example.appnhachoangha.Activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.StrictMode;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;


import com.example.appnhachoangha.Adapter.ViewPagerPlayListNhac;
import com.example.appnhachoangha.Fragment.Fragment_Dia_Nhac;
import com.example.appnhachoangha.Fragment.Fragment_Play_All_Bai_Hat;
import com.example.appnhachoangha.Model.BaiHat;
import com.example.appnhachoangha.R;
import com.example.appnhachoangha.Service.MusicService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class PlayNhacActivity extends AppCompatActivity {

    Toolbar toolbarplaynhac;
    TextView txtTimesong, txtTotaltimesong;
    SeekBar sktime;
    ImageButton imgplay, imgrepeat, imgnext, imgpre, imgrandom;
    ViewPager viewPager;

    public static ViewPagerPlayListNhac adapterNhac;
    Fragment_Dia_Nhac fragment_dia_nhac;
    Fragment_Play_All_Bai_Hat fragment_play_all_bai_hat;

    public static ArrayList<BaiHat> mangbaihat;

    MusicService musicSrv;
    private boolean musicBound=false;
    private int indexSong = -1;

    private final Handler handler = new Handler();
    private Runnable work;

    //connect to the service
    private ServiceConnection musicConnection = new ServiceConnection(){

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MusicService.MusicBinder binder = (MusicService.MusicBinder) service;
            musicSrv = binder.getService();

            // đồng bộ mảng bài hát
            if(mangbaihat.size()>0) { // mang bai hat gui qua intent
                musicSrv.setBaiHats(mangbaihat);
                musicSrv.playSong(0);
            }
            else {
                mangbaihat = musicSrv.getBaiHats();
            }
            updateFirst();
            updateDisplay();
            musicBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            musicBound = false;
        }
    };

    private void updateFirst() {
        final Handler handler1 = new Handler();
        Runnable work1 = new Runnable() {
            @Override
            public void run() {
                if (musicSrv.isStatusDownload()) {
                    if (musicSrv.isPlaying()) {
                        imgplay.setImageResource(R.drawable.iconpause);
                        fragment_dia_nhac.objectAnimator.resume();
                    }
                    else {
                        imgplay.setImageResource(R.drawable.iconplay);
                        fragment_dia_nhac.objectAnimator.pause();
                    }
                    if (musicSrv.isRepeat()) imgrepeat.setImageResource(R.drawable.iconsyned);
                    else imgrepeat.setImageResource(R.drawable.iconrepeat);
                    if (musicSrv.isRandom()) imgrandom.setImageResource(R.drawable.iconshuffled);
                    else imgrandom.setImageResource(R.drawable.iconsuffle);
                    handler1.removeCallbacks(this);
                }
                else handler1.postDelayed(this, 100);
            }
        };
        handler1.postDelayed(work1, 100);
    }

    private void  updateDisplay(){
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");

        work = new Runnable() {
            @Override
            public void run() {
                if (mangbaihat.size() > 0 && musicSrv.isStatusDownload()){
                    int duration = 0;
                    if (musicSrv.getIndexSong()!=indexSong || duration == 0) {           // update khi chuyen bai
                        indexSong = musicSrv.getIndexSong();
                        updateFirst();
                        duration = musicSrv.getDuration();
                        fragment_dia_nhac.PlayNhac(mangbaihat.get(indexSong).getHinhBaiHat());
                        getSupportActionBar().setTitle(mangbaihat.get(indexSong).getTenBaiHat());
                        txtTotaltimesong.setText(simpleDateFormat.format(duration));
                        sktime.setMax(duration);
                    }
                    sktime.setProgress(musicSrv.getCurrentPosition());
                    txtTimesong.setText(simpleDateFormat.format(musicSrv.getCurrentPosition()));
                }
                handler.postDelayed(this, 300);
            }
        };

        handler.postDelayed(work,300);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_nhac);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        mangbaihat = GetDataFromIntent();
        service();
        init();
        eventClick();
    }

    private ArrayList<BaiHat> GetDataFromIntent() {
        Intent intent = getIntent();
        ArrayList<BaiHat> results = new ArrayList<>();
        if(intent != null){
            if(intent.hasExtra("cakhuc")){
                BaiHat baiHat = intent.getParcelableExtra("cakhuc");
                Toast.makeText(this, baiHat.getTenBaiHat(),Toast.LENGTH_SHORT).show();
                results.add(baiHat);

            }
            else if (intent.hasExtra("cacbaihat")){
                ArrayList<BaiHat> baiHatArrayList = intent.getParcelableArrayListExtra("cacbaihat");
                Toast.makeText(this, "Play All", Toast.LENGTH_SHORT).show();
                results = baiHatArrayList;
            }
        }
        return results;
    }

    private void service(){
        Intent playIntent = new Intent(this, MusicService.class);
        bindService(playIntent, musicConnection, Context.BIND_AUTO_CREATE);
        startService(playIntent);
    }

    private void init() {
        toolbarplaynhac = findViewById(R.id.toolplaynhac);
        txtTimesong = findViewById(R.id.txtviewtimesong);
        txtTotaltimesong = findViewById(R.id.txtviewtotaltimesong);
        sktime = findViewById(R.id.seekbarsong);
        imgplay = findViewById(R.id.imgbuttonplay);
        imgrepeat = findViewById(R.id.imgbuttonrepeat);
        imgnext = findViewById(R.id.imgbuttonnext);
        imgrandom = findViewById(R.id.imgbuttonsuffle);
        imgpre = findViewById(R.id.imgbuttonpreview);
        viewPager = findViewById(R.id.viewpagerplaynhac);
        setSupportActionBar(toolbarplaynhac);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarplaynhac.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        toolbarplaynhac.setTitleTextColor(Color.WHITE);
        fragment_dia_nhac = new Fragment_Dia_Nhac();
        fragment_play_all_bai_hat = new Fragment_Play_All_Bai_Hat();
        adapterNhac = new ViewPagerPlayListNhac(getSupportFragmentManager());
        adapterNhac.AddFragment(fragment_dia_nhac);
        adapterNhac.AddFragment(fragment_play_all_bai_hat);
        viewPager.setAdapter(adapterNhac);
        fragment_dia_nhac = (Fragment_Dia_Nhac) adapterNhac.getItem(0);

    }


    private void eventClick() {

        imgplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(musicSrv.isPlaying()){
                    musicSrv.pause();
                    imgplay.setImageResource(R.drawable.iconplay);
                    if(fragment_dia_nhac.objectAnimator != null){
                        fragment_dia_nhac.objectAnimator.pause();
                    }
                }else{
                    musicSrv.start();
                    imgplay.setImageResource(R.drawable.iconpause);
                    if(fragment_dia_nhac.objectAnimator != null){
                        fragment_dia_nhac.objectAnimator.resume();
                    }
                }
            }
        });

        imgrepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (musicSrv.isRepeat() == true){
                    musicSrv.setRepeat(false);
                    imgrepeat.setImageResource(R.drawable.iconrepeat);

                }else{
                    musicSrv.setRepeat(true);
                    imgrepeat.setImageResource(R.drawable.iconsyned);
                }
            }
        });

        imgrandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (musicSrv.isRandom() == true){
                    imgrandom.setImageResource(R.drawable.iconsuffle);
                    musicSrv.setRandom(false);
                }else{
                    musicSrv.setRandom(true);
                    imgrandom.setImageResource(R.drawable.iconshuffled);
                }
            }
        });
//
//        sktime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//                musicSrv.seekTo(seekBar.getProgress());
//            }
//        });

        imgnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    musicSrv.nextSong();
            }
        });

        imgpre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    musicSrv.backSong();
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(musicConnection);
        handler.removeCallbacks(work);
    }

}
