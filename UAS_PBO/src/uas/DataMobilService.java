package uas;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataMobilService {

    
    public void createMobil(String merk, int tahun, double harga) {
        String query = "INSERT INTO data_mobil (merk, tahun, harga) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, merk);
            stmt.setInt(2, tahun);
            stmt.setDouble(3, harga);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    public List<DataMobil> readMobil() {
        List<DataMobil> mobilList = new ArrayList<>();
        String query = "SELECT * FROM data_mobil";
        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                mobilList.add(new DataMobil(
                        rs.getInt("id_mobil"),
                        rs.getString("merk"),
                        rs.getInt("tahun"),
                        rs.getDouble("harga")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mobilList;
    }

    
    public void updateMobil(int id, String merk, int tahun, double harga) {
        String query = "UPDATE data_mobil SET merk = ?, tahun = ?, harga = ? WHERE id_mobil = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, merk);
            stmt.setInt(2, tahun);
            stmt.setDouble(3, harga);
            stmt.setInt(4, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void deleteMobil(int id) {
        String query = "DELETE FROM data_mobil WHERE id_mobil = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
