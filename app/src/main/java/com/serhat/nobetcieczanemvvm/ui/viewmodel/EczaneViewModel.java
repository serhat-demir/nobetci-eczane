package com.serhat.nobetcieczanemvvm.ui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.serhat.nobetcieczanemvvm.data.model.Eczane;
import com.serhat.nobetcieczanemvvm.data.repository.EczaneRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class EczaneViewModel extends ViewModel {
    private EczaneRepository eRepo;
    public MutableLiveData<List<Eczane>> eczaneler;
    public MutableLiveData<String> toastObserver;

    @Inject
    public EczaneViewModel(EczaneRepository eRepo) {
        this.eRepo = eRepo;
        eczaneler = eRepo.eczaneler;
        toastObserver = eRepo.toastObserver;
    }

    public void eczaneAra(String ilce) {
        eRepo.eczaneAra(ilce);
    }
}
