package com.example.appnhachoangha.Service;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.os.PowerManager;
import android.util.Log;
import android.widget.Toast;

import com.example.appnhachoangha.Model.BaiHat;

import java.util.ArrayList;
import java.util.Random;

public class MusicService extends Service implements
        MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener,
        MediaPlayer.OnCompletionListener{

    private MediaPlayer player;
    private ArrayList<BaiHat> baiHats;
    int indexSong = 0;

    int repeat = 0;
    boolean random = false;

    // trạng thái tải nhạc xong chưa
    boolean statusDownload = false;

    private final IBinder musicBind = new MusicBinder();

    public class MusicBinder extends Binder {
        public MusicService getService() {
            return MusicService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return musicBind;
    }

    @Override
    public boolean onUnbind(Intent intent){
        player.stop();
        player.release();
        return false;
    }


    public MusicService() {
    }

    @Override
    public void onCreate(){
        super.onCreate();
        player = new MediaPlayer();
        baiHats = new ArrayList<>();
        initMusicPlayer();
    }

    private void initMusicPlayer(){
        player.setWakeMode(getApplicationContext(), PowerManager.PARTIAL_WAKE_LOCK);
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
        player.setOnPreparedListener(this);
        player.setOnCompletionListener(this);
        player.setOnErrorListener(this);
    }


    // các phương thức cho phát nhạc


        public void playSong(int indexSong){
        player.reset();
        BaiHat baiHat = baiHats.get(indexSong);

        try{
            player.setDataSource(baiHat.getLinkBaiHat());
        }
        catch(Exception e){
            Log.e("MUSIC SERVICE", "Error setting data source", e);
            Toast.makeText(getBaseContext(), "Error setting data source", Toast.LENGTH_LONG).show();
        }
        player.prepareAsync();
    }

    public int getCurrentPosition(){
        return player.getCurrentPosition();
    }

    public int getDuration() {
        return player.getDuration();
    }

    public void seekTo(int position) {
        player.seekTo(position);
    }

    public boolean isStatusDownload() {
        return statusDownload;
    }

    public boolean isPlaying() {return player.isPlaying(); }

    public void pause() {player.pause(); }

    public void start() {player.start(); }

    public void sleepAndPlay(){
        try {
            Thread.sleep(700);
            playSong(indexSong);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void nextSong() {
        indexSong++;
        if (indexSong == baiHats.size()) indexSong = 0;
        sleepAndPlay();
    }

    public void backSong() {
        indexSong--;
        if (indexSong == -1) indexSong = baiHats.size()-1;
        sleepAndPlay();
    }

    public ArrayList<BaiHat> getBaiHats() {
        return baiHats;
    }

    public void setBaiHats(ArrayList<BaiHat> baiHats) {
        this.baiHats = baiHats;
    }

    public int getIndexSong() {
        return indexSong;
    }

    public int getRepeat() {
        return repeat;
    }

    public void setRepeat(int repeat) {
        this.repeat = repeat;
    }

    public boolean isRandom() {
        return random;
    }

    public void setRandom(boolean random) {
        this.random = random;
    }


    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        if (player.getCurrentPosition() > 100) {
            if (repeat == 0) { // dừng bài hát
            }
            else if (repeat == 1) {
                try {
                    Thread.sleep(700);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                player.start();
            }
            else {
                nextSong();
            }
        }

    }

    @Override
    public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        mediaPlayer.start();
        statusDownload = true;
//        Toast.makeText(getApplicationContext(), "download complete", Toast.LENGTH_SHORT).show();
    }
}
