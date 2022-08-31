package com.serhat.nobetcieczanemvvm.ui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.serhat.nobetcieczanemvvm.data.model.Ilce;
import com.serhat.nobetcieczanemvvm.data.repository.IlceRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class IlceViewModel extends ViewModel {
    private IlceRepository iRepo;
    public MutableLiveData<List<Ilce>> ilceler;
    public MutableLiveData<String> toastObserver;

    @Inject
    public IlceViewModel(IlceRepository iRepo) {
        this.iRepo = iRepo;
        ilceler = iRepo.ilceler;
        toastObserver = iRepo.toastObserver;
    }

    public void ilceleriGetir() {
        iRepo.ilceleriGetir();
    }
}
