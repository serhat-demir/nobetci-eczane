package com.serhat.nobetcieczane.api;

import com.serhat.nobetcieczane.model.EczaneResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("dutyPharmacy")
    Call<EczaneResponse> eczaneleriGetir(@Header("Authorization") String authHeader, @Query("il") String il, @Query("ilce") String ilce);
}
