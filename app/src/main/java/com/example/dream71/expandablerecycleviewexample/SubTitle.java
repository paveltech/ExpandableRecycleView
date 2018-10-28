package com.example.dream71.expandablerecycleviewexample;

import android.os.Parcel;
import android.os.Parcelable;

public class SubTitle implements Parcelable {

    private String name;


    public SubTitle(String name){
        this.name = name;
    }


    protected SubTitle(Parcel in) {
        name = in.readString();
    }

    public static final Creator<SubTitle> CREATOR = new Creator<SubTitle>() {
        @Override
        public SubTitle createFromParcel(Parcel in) {
            return new SubTitle(in);
        }

        @Override
        public SubTitle[] newArray(int size) {
            return new SubTitle[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
    }
}
