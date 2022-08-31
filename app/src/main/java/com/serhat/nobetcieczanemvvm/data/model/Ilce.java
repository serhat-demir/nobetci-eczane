package com.serhat.nobetcieczanemvvm.data.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "ilceler")
public class Ilce {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ilce_id")
    @NonNull
    private int ilce_id;

    @ColumnInfo(name = "ilce_ad")
    @NonNull
    private String ilce_ad;

    public Ilce(int ilce_id, @NonNull String ilce_ad) {
        this.ilce_id = ilce_id;
        this.ilce_ad = ilce_ad;
    }

    public int getIlce_id() {
        return ilce_id;
    }

    public void setIlce_id(int ilce_id) {
        this.ilce_id = ilce_id;
    }

    @NonNull
    public String getIlce_ad() {
        return ilce_ad;
    }

    public void setIlce_ad(@NonNull String ilce_ad) {
        this.ilce_ad = ilce_ad;
    }
}
