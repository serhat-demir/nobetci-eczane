package com.serhat.nobetcieczanemvvm.data.repository;

import androidx.lifecycle.MutableLiveData;

import com.serhat.nobetcieczanemvvm.api.ApiInterface;
import com.serhat.nobetcieczanemvvm.api.ApiUtils;
import com.serhat.nobetcieczanemvvm.data.model.Eczane;
import com.serhat.nobetcieczanemvvm.data.model.EczaneResponse;
import com.serhat.nobetcieczanemvvm.helper.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EczaneRepository {
    public MutableLiveData<List<Eczane>> eczaneler;
    public MutableLiveData<String> toastObserver;
    private ApiInterface apiService;

    public EczaneRepository(ApiInterface apiService) {
        this.apiService = apiService;
        this.eczaneler = new MutableLiveData<>();
        this.toastObserver = new MutableLiveData<>();
    }

    public void eczaneAra(String ilce) {
        if (ilce.equalsIgnoreCase("İlçe Seçin")) {
            toastObserver.setValue("İlçe seçilmedi.");
        } else {
            apiService.eczaneleriGetir(ApiUtils.API_KEY, Constants.il, ilce).enqueue(new Callback<EczaneResponse>() {
                @Override
                public void onResponse(Call<EczaneResponse> call, Response<EczaneResponse> response) {
                    if (response.body() != null && response.isSuccessful()) {
                        eczaneler.setValue(response.body().getEczaneler());
                        toastObserver.setValue("Açık eczaneler listelendi.");
                    } else {
                        toastObserver.setValue("Eczane listesi yüklenemedi.");
                    }
                }

                @Override
                public void onFailure(Call<EczaneResponse> call, Throwable t) {
                    toastObserver.setValue("Eczane listesi yüklenemedi.");
                }
            });
        }
    }
}
