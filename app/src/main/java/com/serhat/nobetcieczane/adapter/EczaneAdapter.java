package com.serhat.nobetcieczane.adapter;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.serhat.nobetcieczane.R;
import com.serhat.nobetcieczane.databinding.CardEczaneBinding;
import com.serhat.nobetcieczane.model.Eczane;

import java.util.List;
import java.util.Locale;

public class EczaneAdapter extends RecyclerView.Adapter<EczaneAdapter.EczaneHolder> {
    private Context context;
    private List<Eczane> eczaneler;

    public EczaneAdapter(Context context, List<Eczane> eczaneler) {
        this.context = context;
        this.eczaneler = eczaneler;
    }

    public class EczaneHolder extends RecyclerView.ViewHolder {
        public TextView txtEczaneAd, txtEczaneAdres, txtEczaneTelefon, txtAdresiKopyala, txtHaritadaAc;

        public EczaneHolder(@NonNull CardEczaneBinding binding) {
            super(binding.getRoot());

            txtEczaneAd = binding.txtEczaneAd;
            txtEczaneAdres = binding.txtEczaneAdres;
            txtEczaneTelefon = binding.txtEczaneTelefon;
            txtAdresiKopyala = binding.txtAdresiKopyala;
            txtHaritadaAc = binding.txtHaritadaAc;
        }
    }

    @NonNull
    @Override
    public EczaneHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardEczaneBinding binding = CardEczaneBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new EczaneHolder(binding);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull EczaneHolder holder, int position) {
        Eczane eczane = eczaneler.get(position);

        holder.txtEczaneAd.setText(eczane.getName());
        holder.txtEczaneAdres.setText(context.getResources().getString(R.string.adres_prefix) + " " + eczane.getAddress());
        holder.txtEczaneTelefon.setText(context.getResources().getString(R.string.telefon_prefix) + " " + eczane.getPhone());

        holder.txtHaritadaAc.setOnClickListener(view -> {
            String[] locArrStr = eczane.getLoc().split(",");
            Double lat = Double.parseDouble(locArrStr[0]);
            Double lng = Double.parseDouble(locArrStr[1]);

            String uri = String.format(Locale.ENGLISH, "http://maps.google.com/maps?q=loc:%f,%f", lat, lng);
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
            context.startActivity(intent);
        });

        holder.txtAdresiKopyala.setOnClickListener(view -> {
            ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText(context.getResources().getString(R.string.label_adres), eczane.getAddress());
            clipboard.setPrimaryClip(clip);

            Toast.makeText(context, context.getResources().getString(R.string.adres_kopyalandi), Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return eczaneler.size();
    }
}
