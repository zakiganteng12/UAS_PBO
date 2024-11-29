package uas;

public class Pelanggan {
    private int id;
    private String nama;
    private String nik;
    private String notelp;
    private String alamat;

    public Pelanggan(int id, String nama, String nik, String notelp, String alamat) {
        this.id = id;
        this.nama = nama;
        this.nik = nik;
        this.notelp = notelp;
        this.alamat = alamat;
    }

    public int getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getNik() {
        return nik;
    }

    public String getNotelp() {
        return notelp;
    }

    public String getAlamat() {
        return alamat;
    }
}

