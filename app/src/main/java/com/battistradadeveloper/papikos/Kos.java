package com.battistradadeveloper.papikos;

public class Kos {

        int id;
        String nama, jenis, alamat, fasilitas, kontak, pemilik;
        double harga;

        public Kos(int id, String nama, String jenis, String alamat, String fasilitas, String kontak, String pemilik, double harga) {
            this.id = id;
            this.nama = nama;
            this.jenis = jenis;
            this.alamat = alamat;
            this.fasilitas = fasilitas;
            this.kontak = kontak;
            this.pemilik = pemilik;
            this.harga = harga;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNama() {
            return nama;
        }

        public void setNama(String nama) {
            this.nama = nama;
        }

        public String getJenis() {
            return jenis;
        }

        public void setJenis(String jenis) {
            this.jenis = jenis;
        }

        public String getAlamat() {
            return alamat;
        }

        public void setAlamat(String alamat) {
            this.alamat = alamat;
        }

        public String getFasilitas() {
            return fasilitas;
        }

        public void setFasilitas(String fasilitas) {
            this.fasilitas = fasilitas;
        }

        public String getKontak() {
            return kontak;
        }

        public void setKontak(String kontak) {
            this.kontak = kontak;
        }

        public String getPemilik() {
            return pemilik;
        }

        public void setPemilik(String pemilik) {
            this.pemilik = pemilik;
        }

        public double getHarga() {
            return harga;
        }

        public void setHarga(double harga) {
            this.harga = harga;
        }
    }

