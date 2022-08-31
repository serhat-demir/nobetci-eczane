package com.serhat.nobetcieczanemvvm.room;

import androidx.room.Dao;
import androidx.room.Query;

import com.serhat.nobetcieczanemvvm.data.model.Ilce;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface IlceDao {
    @Query("SELECT * FROM ilceler")
    Single<List<Ilce>> ilceleriGetir();
}
