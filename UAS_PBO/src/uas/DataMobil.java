package uas;

public class DataMobil {
    private int id;
    private String merk;
    private int tahun;
    private double harga;

    public DataMobil(int id, String merk, int tahun, double harga) {
        this.id = id;
        this.merk = merk;
        this.tahun = tahun;
        this.harga = harga;
    }

    public int getId() {
        return id;
    }

    public String getMerk() {
        return merk;
    }

    public int getTahun() {
        return tahun;
    }

    public double getHarga() {
        return harga;
    }
}
