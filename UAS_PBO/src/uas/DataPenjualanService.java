package uas;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataPenjualanService {

    
    public void createPenjualan(int pelangganId, int mobilId, double totalBiaya) {
        String query = "INSERT INTO data_penjualan (id_pelanggan, id_mobil, total_biaya) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, pelangganId);
            stmt.setInt(2, mobilId);
            stmt.setDouble(3, totalBiaya);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    public List<DataPenjualan> readPenjualan() {
        List<DataPenjualan> penjualanList = new ArrayList<>();
        String query = "SELECT * FROM data_penjualan";
        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                penjualanList.add(new DataPenjualan(
                        rs.getInt("id_penjualan"),
                        rs.getInt("id_pelanggan"),
                        rs.getInt("id_mobil"),
                        rs.getDouble("total_biaya")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return penjualanList;
    }

    
    public void updatePenjualan(int id, int pelangganId, int mobilId, double totalBiaya) {
        String query = "UPDATE data_penjualan SET id_pelanggan = ?, id_mobil = ?, total_biaya = ? WHERE id_penjualan = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, pelangganId);
            stmt.setInt(2, mobilId);
            stmt.setDouble(3, totalBiaya);
            stmt.setInt(4, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    public void deletePenjualan(int id) {
        String query = "DELETE FROM data_penjualan WHERE id_penjualan = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
