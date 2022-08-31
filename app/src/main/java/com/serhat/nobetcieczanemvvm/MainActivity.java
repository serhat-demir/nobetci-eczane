package com.serhat.nobetcieczanemvvm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.serhat.nobetcieczanemvvm.data.model.Ilce;
import com.serhat.nobetcieczanemvvm.databinding.ActivityMainBinding;
import com.serhat.nobetcieczanemvvm.ui.adapter.EczaneAdapter;
import com.serhat.nobetcieczanemvvm.ui.adapter.IlceAdapter;
import com.serhat.nobetcieczanemvvm.ui.viewmodel.EczaneViewModel;
import com.serhat.nobetcieczanemvvm.ui.viewmodel.IlceViewModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    private Context context;
    private ActivityMainBinding binding;

    private EczaneViewModel eczaneViewModel;
    private IlceViewModel ilceViewModel;

    private Ilce ilce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = MainActivity.this;
        eczaneViewModel = new ViewModelProvider(this).get(EczaneViewModel.class);
        ilceViewModel = new ViewModelProvider(this).get(IlceViewModel.class);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setMainActivity(this);

        //toolbar subtitle
        @SuppressLint("SimpleDateFormat") DateFormat df = new SimpleDateFormat("d MMMM yyyy, EEEE", new Locale("tr"));
        binding.setTarih(df.format(new Date()));

        //eczane viewmodel
        eczaneViewModel.eczaneler.observe(this, eczaneler -> {
            binding.setEczaneAdapter(new EczaneAdapter(context, eczaneler));
        });

        eczaneViewModel.toastObserver.observe(this, message -> {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        });

        //ilce viewmodel
        ilceViewModel.ilceler.observe(this, ilceler -> {
            IlceAdapter ilceAdapter = new IlceAdapter(context, R.layout.spinner_item, ilceler);
            ilceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            binding.setIlceAdapter(ilceAdapter);

            binding.spIlce.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    ilce = ilceler.get(position);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        });

        ilceViewModel.ilceleriGetir();

        ilceViewModel.toastObserver.observe(this, message -> {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        });
    }

    public void eczaneAra() {
        eczaneViewModel.eczaneAra(ilce.getIlce_ad());
    }
}