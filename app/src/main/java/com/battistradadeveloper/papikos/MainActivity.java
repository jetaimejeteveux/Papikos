package com.battistradadeveloper.papikos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String DATABASE_NAME = "koskosan";

    SQLiteDatabase mDatabase;
    EditText edtNama, edtFasil, edtAddr, edtKontak, edtPemilik, edtHarga;
    Spinner spinnerJenis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDatabase = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
        createTable();
        edtNama = findViewById(R.id.edt_nama);
        edtAddr = findViewById(R.id.edt_alamat);
        edtFasil = findViewById(R.id.edt_fasilitas);
        spinnerJenis = findViewById(R.id.spinnerJenis);
        edtKontak = findViewById(R.id.edt_kontak);
        edtPemilik = findViewById(R.id.edt_pemilik);
        edtHarga = findViewById(R.id.edt_harga);

        findViewById(R.id.btn_add).setOnClickListener(this);
        findViewById(R.id.btn_view).setOnClickListener(this);
    }

    private void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS kost (\n" +
                " id INTEGER NOT NULL CONSTRAINT kos_pk PRIMARY KEY AUTOINCREMENT, \n" +
                " nama varchar(200) NOT NULL, \n" +
                " jenis varchar (200) NOT NULL, \n" +
                " fasilitas varchar(200) NOT NULL, \n" +
                " alamat varchar (200) NOT NULL, \n" +
                " kontak varchar(200) NOT NULL, \n" +
                " pemilik varchar (200) NOT NULL, \n" +
                " harga double NOT NULL\n" +
                ");";

        mDatabase.execSQL(sql);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:
                addKos();

                break;
            case R.id.btn_view:
                startActivity(new Intent(this, AdminKosan.class));
        }
    }

    private void addKos() {
        String nama = edtNama.getText().toString().trim();
        String addr = edtAddr.getText().toString().trim();
        String jenisKos = spinnerJenis.getSelectedItem().toString().trim();
        String fasil = edtFasil.getText().toString().trim();
        String kontak = edtKontak.getText().toString().trim();
        String pemilik = edtPemilik.getText().toString().trim();
        String harga = edtHarga.getText().toString().trim();

        if(nama.isEmpty()){
            edtNama.setError("Nama ngga boleh kosong");
            edtNama.requestFocus();
            return;
        }
        if(addr.isEmpty()) {
            edtAddr.setError("Alamat ngga boleh kosong");
            edtAddr.requestFocus();
            return;
        }
        if(fasil.isEmpty()) {
            edtFasil.setError("Fasilitas jangan kosong");
            edtFasil.requestFocus();
            return;
        }
        if(kontak.isEmpty()) {
            edtKontak.setError("Kontak jangan kosong");
            edtKontak.requestFocus();
            return;
        }
        if(pemilik.isEmpty()) {
            edtPemilik.setError("Pemilik jangan kosong");
            edtPemilik.requestFocus();
            return;
        }
        if(harga.isEmpty()) {
            edtHarga.setError("Harga jangan kosong");
            edtHarga.requestFocus();
            return;
        }


        String sql = "INSERT INTO kost (nama, alamat, jenis, fasilitas, kontak, pemilik, harga)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        mDatabase.execSQL(sql, new String[]{nama, addr, jenisKos, fasil, kontak, pemilik, harga});
        Toast.makeText(this, "Sudah ditambahkan", Toast.LENGTH_LONG).show();
    }
}
