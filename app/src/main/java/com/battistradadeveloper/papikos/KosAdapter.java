package com.battistradadeveloper.papikos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class KosAdapter extends ArrayAdapter<Kos> {


        Context mContext;
        int layoutRes;
        List<Kos> kosList;
        public KosAdapter (Context mContext, int layoutRes, List<Kos> kosList) {
            super(mContext, layoutRes, kosList);

            this.mContext = mContext;
            this.layoutRes = layoutRes;
            this.kosList = kosList;
        }
        @NonNull
        @Override
        public View getView (int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(mContext);

            View view = inflater.inflate(layoutRes, null);
            TextView tvNama = view.findViewById(R.id.name1);
            TextView tvJenis = view.findViewById(R.id.jeniskos);
            TextView tvAlamat = view.findViewById(R.id.alamat);
            TextView tvFasilitas = view.findViewById(R.id.fasilitas1);
            TextView tvKontak = view.findViewById(R.id.kontak);
            TextView tvPemilik = view.findViewById(R.id.pemilik);

            TextView tvHarga = view.findViewById(R.id.harga);

            Kos kos = kosList.get(position);

            tvNama.setText(kos.getNama());
            tvJenis.setText(kos.getJenis());
            tvAlamat.setText(kos.getAlamat());
            tvFasilitas.setText(kos.getFasilitas());
            tvKontak.setText(kos.getKontak());
            tvPemilik.setText(kos.getPemilik());
            tvHarga.setText(String.valueOf(kos.getHarga()));

            return view;

        }

}

