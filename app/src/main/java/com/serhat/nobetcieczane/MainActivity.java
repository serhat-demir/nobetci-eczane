package com.serhat.nobetcieczane;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.serhat.nobetcieczane.adapter.EczaneAdapter;
import com.serhat.nobetcieczane.api.ApiClient;
import com.serhat.nobetcieczane.api.ApiInterface;
import com.serhat.nobetcieczane.databinding.ActivityMainBinding;
import com.serhat.nobetcieczane.model.Eczane;
import com.serhat.nobetcieczane.model.EczaneResponse;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private Context context;
    private ActivityMainBinding binding;
    private ApiInterface apiService;

    private List<Eczane> eczaneler;
    private EczaneAdapter eczaneAdapter;

    private final String il = "İstanbul";
    private final String[] ilceler = {"İlçe Seçin","Adalar","Arnavutköy","Ataşehir","Avcılar","Bağcılar","Bahçelievler","Bakırköy","Başakşehir","Bayrampaşa","Beşiktaş","Beylikdüzü","Beyoğlu","Büyükçekmece","Beykoz","Çatalca","Çekmeköy","Esenler","Esenyurt","Eyüp","Fatih","Gaziosmanpaşa","Güngören","Kadıköy","Kağıthane","Kartal","Küçükçekmece","Maltepe","Pendik","Sancaktepe","Sarıyer","Silivri","Sultanbeyli","Sultangazi","Şile","Şişli","Tuzla","Üsküdar","Ümraniye","Zeytinburnu"};
    private ArrayAdapter<String> ilceAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        context = MainActivity.this;
        apiService = ApiClient.getClient().create(ApiInterface.class);

        Date tarih = new Date();
        @SuppressLint("SimpleDateFormat") DateFormat df = new SimpleDateFormat("d MMMM yyyy, EEEE", new Locale("tr"));

        binding.toolbar.setTitle(getString(R.string.app_name));
        binding.toolbar.setSubtitle(df.format(tarih));

        //spinner
        ilceAdapter = new ArrayAdapter<>(context, R.layout.spinner_item, ilceler);
        ilceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spIlce.setAdapter(ilceAdapter);
        binding.spIlce.setPadding(0, binding.spIlce.getPaddingTop(), 0, binding.spIlce.getPaddingBottom());

        //recyclerview
        binding.rvEczaneler.setHasFixedSize(true);
        binding.rvEczaneler.setLayoutManager(new LinearLayoutManager(context));

        //buton
        binding.btnAra.setOnClickListener(view -> {
            if (binding.spIlce.getSelectedItemPosition() == 0) {
                Toast.makeText(context, getString(R.string.ilce_secilmedi), Toast.LENGTH_SHORT).show();
            } else {
                String ilce = binding.spIlce.getSelectedItem().toString();

                apiService.eczaneleriGetir(ApiClient.API_KEY, il, ilce).enqueue(new Callback<EczaneResponse>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onResponse(Call<EczaneResponse> call, Response<EczaneResponse> response) {
                        if (response.body() != null && response.body().getEczaneler() != null && response.body().getEczaneler().size() > 0 && response.body().getSuccess()) {
                            eczaneler = response.body().getEczaneler();
                            eczaneAdapter = new EczaneAdapter(context, eczaneler);
                            binding.rvEczaneler.setAdapter(eczaneAdapter);
                        } else {
                            Toast.makeText(context, getString(R.string.baglanti_hatasi), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<EczaneResponse> call, Throwable t) {
                        Toast.makeText(context, getString(R.string.baglanti_hatasi), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}