package com.serhat.nobetcieczanemvvm.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.serhat.nobetcieczanemvvm.data.model.Ilce;

@Database(entities = {Ilce.class}, version = 1)
public abstract class DB extends RoomDatabase {
    public abstract IlceDao getIlceDao();
}
