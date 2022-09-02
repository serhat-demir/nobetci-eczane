package com.serhat.nobetcieczanemvvm.ui.adapter;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.serhat.nobetcieczanemvvm.R;
import com.serhat.nobetcieczanemvvm.data.model.Eczane;
import com.serhat.nobetcieczanemvvm.databinding.CardEczaneBinding;

import java.util.List;
import java.util.Locale;

public class EczaneAdapter extends RecyclerView.Adapter<EczaneAdapter.EczaneViewHolder> {
    private Context mContext;
    private List<Eczane> eczaneler;

    public EczaneAdapter(Context mContext, List<Eczane> eczaneler) {
        this.mContext = mContext;
        this.eczaneler = eczaneler;
    }

    public class EczaneViewHolder extends RecyclerView.ViewHolder {
        public CardEczaneBinding binding;

        public EczaneViewHolder(@NonNull CardEczaneBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    @NonNull
    @Override
    public EczaneViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardEczaneBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.card_eczane, parent, false);
        return new EczaneViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull EczaneViewHolder holder, int position) {
        holder.binding.setEczaneAdapter(this);
        holder.binding.setEczane(eczaneler.get(position));
    }

    public void ara(String telefon) {
        if (telefon.trim().isEmpty()) {
            Toast.makeText(mContext, mContext.getResources().getString(R.string.msg_telefon_bulunamadi), Toast.LENGTH_SHORT).show();
            return;
        }

        String tel = (telefon.startsWith("0")) ? telefon : ("0" + telefon);
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + tel));
        mContext.startActivity(intent);
    }

    public void adresiKopyala(String adres) {
        ClipboardManager clipboard = (ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("", adres);
        clipboard.setPrimaryClip(clip);

        Toast.makeText(mContext, mContext.getResources().getString(R.string.msg_adres_kopyalandi), Toast.LENGTH_SHORT).show();
    }

    public void haritadaGoster(String loc) {
        String[] locArrStr = loc.split(",");
        Double lat = Double.parseDouble(locArrStr[0]);
        Double lng = Double.parseDouble(locArrStr[1]);

        String uri = String.format(Locale.ENGLISH, "http://maps.google.com/maps?q=loc:%f,%f", lat, lng);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        mContext.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return eczaneler.size();
    }
}
