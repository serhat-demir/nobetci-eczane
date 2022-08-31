package com.serhat.nobetcieczanemvvm.data.repository;

import androidx.lifecycle.MutableLiveData;

import com.serhat.nobetcieczanemvvm.data.model.Ilce;
import com.serhat.nobetcieczanemvvm.room.IlceDao;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class IlceRepository {
    public MutableLiveData<List<Ilce>> ilceler;
    public MutableLiveData<String> toastObserver;
    private IlceDao iDao;

    public IlceRepository(IlceDao iDao) {
        ilceler = new MutableLiveData<>();
        toastObserver = new MutableLiveData<>();

        this.iDao = iDao;
    }

    public void ilceleriGetir() {
        iDao.ilceleriGetir().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Ilce>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<Ilce> liste) {
                        ilceler.setValue(liste);
                    }

                    @Override
                    public void onError(Throwable e) {
                        toastObserver.setValue("İlçeler yüklenemedi.");
                    }
                });
    }
}
