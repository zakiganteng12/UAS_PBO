package uas;

public class DataPenjualan {
    private int id;
    private int pelangganId;
    private int mobilId;
    private double totalBiaya;

    public DataPenjualan(int id, int pelangganId, int mobilId, double totalBiaya) {
        this.id = id;
        this.pelangganId = pelangganId;
        this.mobilId = mobilId;
        this.totalBiaya = totalBiaya;
    }

    public int getId() {
        return id;
    }

    public int getPelangganId() {
        return pelangganId;
    }

    public int getMobilId() {
        return mobilId;
    }

    public double getTotalBiaya() {
        return totalBiaya;
    }
}
