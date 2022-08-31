package com.serhat.nobetcieczanemvvm.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.serhat.nobetcieczanemvvm.R;
import com.serhat.nobetcieczanemvvm.data.model.Ilce;
import com.serhat.nobetcieczanemvvm.databinding.SpinnerItemBinding;

import java.util.List;

public class IlceAdapter extends ArrayAdapter<Ilce>{
    private Context context;
    private List<Ilce> ilceler;

    public IlceAdapter(@NonNull Context context, int textViewResourceId, List<Ilce> ilceler) {
        super(context, textViewResourceId, ilceler);
        this.context = context;
        this.ilceler = ilceler;
    }

    @Override
    public int getCount(){
        return ilceler.size();
    }

    @Override
    public Ilce getItem(int position){
        return ilceler.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        @SuppressLint("ViewHolder") SpinnerItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.spinner_item, parent, false);
        binding.setIlce(ilceler.get(position));
        return binding.txtIlceAd;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        TextView label = (TextView) super.getDropDownView(position, convertView, parent);
        label.setText(ilceler.get(position).getIlce_ad());
        return label;
    }
}