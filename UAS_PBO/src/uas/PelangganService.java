package uas;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PelangganService {

    // CREATE
    public void createPelanggan(String nama, String nik, String notelp, String alamat) {
        String query = "INSERT INTO data_pelanggan (nama, nik, notelp, alamat) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, nama);
            stmt.setString(2, nik);
            stmt.setString(3, notelp);
            stmt.setString(4, alamat);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ
    public List<Pelanggan> readPelanggan() {
        List<Pelanggan> pelangganList = new ArrayList<>();
        String query = "SELECT * FROM data_pelanggan";
        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                pelangganList.add(new Pelanggan(
                        rs.getInt("idpelanggan"),
                        rs.getString("nama"),
                        rs.getString("nik"),
                        rs.getString("notelp"),
                        rs.getString("alamat")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pelangganList;
    }

    // UPDATE
    public void updatePelanggan(int id, String nama, String nik, String notelp, String alamat) {
        String query = "UPDATE data_pelanggan SET nama = ?, nik = ?, notelp = ?, alamat = ? WHERE idpelanggan = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, nama);
            stmt.setString(2, nik);
            stmt.setString(3, notelp);
            stmt.setString(4, alamat);
            stmt.setInt(5, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public void deletePelanggan(int id) {
        String query = "DELETE FROM data_pelanggan WHERE idpelanggan = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

