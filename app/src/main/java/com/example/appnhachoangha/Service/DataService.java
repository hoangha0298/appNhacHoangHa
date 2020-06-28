package com.example.appnhachoangha.Service;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import com.example.appnhachoangha.Model.Album;
import com.example.appnhachoangha.Model.BaiHat;
import com.example.appnhachoangha.Model.PlayList;
import com.example.appnhachoangha.Model.Quangcao;
import com.example.appnhachoangha.Model.TheLoai;

public interface DataService {
    @GET("songbanner.php")
    Call<List<Quangcao>> GetDataBanner();

    @GET("playlist_trangchu.php")
    Call<List<PlayList>> GetDataPlayList();

    @GET("theloai.php")
    Call<List<TheLoai>> GetDataTheloai();

    @GET("albumhot.php")
    Call<List<Album>> GetAlbumhot();

    @GET("baihatduocthich.php")
    Call<List<BaiHat>> GetBaiHatHot();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> GetDanhsachbaihattheoquangcao(@Field("idquangcao") String idquangcao);

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> GetDanhsachbaihattheoplaylist(@Field("idplaylist") String idplaylist);

    @GET("danhsachplaylist.php")
    Call<List<PlayList>> GetDanhsachplaylist();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> GetDanhsachbaihattheotheloai(@Field("idtheloai") String idtheloai);

    @GET("danhsachtheloai.php")
    Call<List<TheLoai>> GetDanhsachAllTheloai();

    @GET("danhsachalbum.php")
    Call<List<Album>> GetDanhsachAllAlbum();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> GetDanhsachbaihattheoalbum(@Field("idalbum") String idalbum);

    @FormUrlEncoded
    @POST("timkiem.php")
    Call<List<BaiHat>> GetSearchBaiHat(@Field("tukhoa") String tukhoa);

 }
