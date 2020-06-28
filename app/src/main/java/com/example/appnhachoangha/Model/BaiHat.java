package com.example.appnhachoangha.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class BaiHat implements  Parcelable{
    @SerializedName("IdBaiHat")
    @Expose
    private String idBaiHat;
    @SerializedName("TenBaiHat")
    @Expose
    private String tenBaiHat;
    @SerializedName("HinhBaiHat")
    @Expose
    private String hinhBaiHat;
    @SerializedName("CaSi")
    @Expose
    private String caSi;
    @SerializedName("LinkBaiHat")
    @Expose
    private String linkBaiHat;

    public BaiHat(String idBaiHat, String tenBaiHat, String hinhBaiHat, String caSi, String linkBaiHat) {
        this.idBaiHat = idBaiHat;
        this.tenBaiHat = tenBaiHat;
        this.hinhBaiHat = hinhBaiHat;
        this.caSi = caSi;
        this.linkBaiHat = linkBaiHat;
    }

    protected BaiHat(Parcel in) {
        idBaiHat = in.readString();
        tenBaiHat = in.readString();
        hinhBaiHat = in.readString();
        caSi = in.readString();
        linkBaiHat = in.readString();

    }

    public static final Creator<BaiHat> CREATOR = new Creator<BaiHat>() {
        @Override
        public BaiHat createFromParcel(Parcel in) {
            return new BaiHat(in);
        }

        @Override
        public BaiHat[] newArray(int size) {
            return new BaiHat[size];
        }
    };

    public String getIdBaiHat() {
        return idBaiHat;
    }

    public void setIdBaiHat(String idBaiHat) {
        this.idBaiHat = idBaiHat;
    }

    public String getTenBaiHat() {
        return tenBaiHat;
    }

    public void setTenBaiHat(String tenBaiHat) {
        this.tenBaiHat = tenBaiHat;
    }

    public String getHinhBaiHat() {
        return hinhBaiHat;
    }

    public void setHinhBaiHat(String hinhBaiHat) {
        this.hinhBaiHat = hinhBaiHat;
    }

    public String getCaSi() {
        return caSi;
    }

    public void setCaSi(String caSi) {
        this.caSi = caSi;
    }

    public String getLinkBaiHat() {
        return linkBaiHat;
    }

    public void setLinkBaiHat(String linkBaiHat) {
        this.linkBaiHat = linkBaiHat;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(idBaiHat);
        dest.writeString(tenBaiHat);
        dest.writeString(hinhBaiHat);
        dest.writeString(caSi);
        dest.writeString(linkBaiHat);

    }

    @Override
    public String toString() {
        return "BaiHat{" +
                "idBaiHat='" + idBaiHat + '\'' +
                ", tenBaiHat='" + tenBaiHat + '\'' +
                ", hinhBaiHat='" + hinhBaiHat + '\'' +
                ", caSi='" + caSi + '\'' +
                ", linkBaiHat='" + linkBaiHat + '\'' +
                '}';
    }
}
