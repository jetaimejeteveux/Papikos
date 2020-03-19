package com.battistradadeveloper.papikos;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AlertDialogLayout;

import java.util.List;

public class KosAdapter extends ArrayAdapter<Kos> {


    Context mContext;
    int layoutRes;
    List<Kos> kosList;
    SQLiteDatabase mDatabase;


    public KosAdapter(Context mContext, int layoutRes, List<Kos> kosList) {
        super(mContext, layoutRes, kosList);

        this.mContext = mContext;
        this.layoutRes = layoutRes;
        this.kosList = kosList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mContext);

        View view = inflater.inflate(layoutRes, null);
        TextView tvNama = view.findViewById(R.id.name1);
        TextView tvJenis = view.findViewById(R.id.jeniskos);
        TextView tvAlamat = view.findViewById(R.id.alamat);
        TextView tvFasilitas = view.findViewById(R.id.fasilitas1);
        TextView tvKontak = view.findViewById(R.id.kontak);
        TextView tvPemilik = view.findViewById(R.id.pemilik);
        Button btnEdit = view.findViewById(R.id.btn_edit);
        Button btnDelete = view.findViewById(R.id.btn_delete);
        final Kos kos = kosList.get(position);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            updateKos(kos);
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("R U Sure?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String sql = "DELETE FROM kost WHERE id = ?";
                        mDatabase.execSQL(sql, new Integer[]{kos.getId()});
                        reloadKosan();

                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        TextView tvHarga = view.findViewById(R.id.harga);


        tvNama.setText(kos.getNama());
        tvJenis.setText(kos.getJenis());
        tvAlamat.setText(kos.getAlamat());
        tvFasilitas.setText(kos.getFasilitas());
        tvKontak.setText(kos.getKontak());
        tvPemilik.setText(kos.getPemilik());
        tvHarga.setText(String.valueOf(kos.getHarga()));

        return view;

    }



    private void updateKos(Kos kos) {
        androidx.appcompat.app.AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        LayoutInflater inflater = LayoutInflater.from(mContext);

        View view = inflater.inflate(R.layout.dialog_update, null);
        builder.setView(view);

        final EditText edtNama = view.findViewById(R.id.edt_update_nama);
        final Spinner spinnerJenis = view.findViewById(R.id.spinner_update_jenis);
        final EditText edtFasilitas = view.findViewById(R.id.edt_update_fasilitas);
        final EditText edtAlamat = view.findViewById(R.id.edt_update_alamat);
        final EditText edtKontak = view.findViewById(R.id.edt_update_kontak);
        final EditText edtPemilik = view.findViewById(R.id.edt_update_pemilik);
        final EditText edtHarga = view.findViewById(R.id.edt_update_harga);

        edtNama.setText(kos.getNama());
        edtFasilitas.setText(kos.getNama());
        edtAlamat.setText(kos.getNama());
        edtKontak.setText(kos.getNama());
        edtPemilik.setText(kos.getNama());
        edtHarga.setText(kos.getNama());


        final AlertDialog dialog = builder.create();
        dialog.show();

        view.findViewById(R.id.btn_update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = edtNama.getText().toString();
                String fasilitas = edtFasilitas.getText().toString();
                String kontak = edtKontak.getText().toString();
                String pemilik = edtPemilik.getText().toString();
                String alamat = edtAlamat.getText().toString();
                String harga = edtHarga.getText().toString();
                String jenis = spinnerJenis.getSelectedItem().toString();
                if (nama.isEmpty()) {
                    edtNama.setError("Tidak Boleh Kosong");
                    edtNama.requestFocus();
                }
                if (fasilitas.isEmpty()) {
                    edtNama.setError("Tidak Boleh Kosong");
                    edtNama.requestFocus();
                }
                if (kontak.isEmpty()) {
                    edtNama.setError("Tidak Boleh Kosong");
                    edtNama.requestFocus();

                }
                if (alamat.isEmpty()) {
                    edtNama.setError("Tidak Boleh Kosong");
                    edtNama.requestFocus();

                }
                if (harga.isEmpty()) {
                    edtNama.setError("Tidak Boleh Kosong");
                    edtNama.requestFocus();
                }
                if (pemilik.isEmpty()) {
                    edtNama.setError("Tidak Boleh Kosong");
                    edtNama.requestFocus();
                }
                String sql = "UPDATE kost \n" +
                        "SET nama = ?, \n" +
                        "jenis = ?, \n" +
                        "fasilitas = ?, \n" +
                        "alamat = ?, \n" +
                        "kontak = ?, \n" +
                        "pemilik = ?, \n" +
                        "harga = ? \n " +
                        "where id = ?; \n";

                mDatabase.execSQL(sql, new String[]{nama, jenis, fasilitas, alamat, kontak, harga });
                Toast.makeText(mContext, "Terupdate", Toast.LENGTH_LONG).show();
                reloadKosan();
                dialog.dismiss();
            }

        });

            }
    private void reloadKosan() {
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM kost", null);
        if (cursor.moveToFirst()) {
            kosList.clear();
            do {
                kosList.add(new Kos(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getDouble(7)));

            } while (cursor.moveToNext());
            cursor.close();
            notifyDataSetChanged();
        }
    }
}


