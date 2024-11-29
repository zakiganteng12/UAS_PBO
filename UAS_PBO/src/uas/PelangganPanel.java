package uas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class PelangganPanel extends JPanel {
    private JTextField namaField, nikField, notelpField, alamatField;
    private JTable table;
    private DefaultTableModel tableModel;
    private PelangganService pelangganService;

    public PelangganPanel() {
        pelangganService = new PelangganService();

        setLayout(new BorderLayout());

        // Input Panel
        JPanel inputPanel = new JPanel();
        JLabel namaLabel = new JLabel("Nama:");
        namaField = new JTextField(20);
        JLabel nikLabel = new JLabel("NIK:");
        nikField = new JTextField(20);
        JLabel notelpLabel = new JLabel("No Telp:");
        notelpField = new JTextField(20);
        JLabel alamatLabel = new JLabel("Alamat:");
        alamatField = new JTextField(20);

        JButton createButton = new JButton("Create");
        createButton.addActionListener(this::handleCreate);
        JButton updateButton = new JButton("Update");
        updateButton.addActionListener(this::handleUpdate);
        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(this::handleDelete);
        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(e -> refreshTable());

        GroupLayout layout = new GroupLayout(inputPanel);
        inputPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(namaLabel)
                                .addComponent(nikLabel)
                                .addComponent(notelpLabel)
                                .addComponent(alamatLabel))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(namaField)
                                .addComponent(nikField)
                                .addComponent(notelpField)
                                .addComponent(alamatField)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(createButton)
                                        .addComponent(updateButton)
                                        .addComponent(deleteButton)
                                        .addComponent(refreshButton)))
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(namaLabel)
                                .addComponent(namaField))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(nikLabel)
                                .addComponent(nikField))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(notelpLabel)
                                .addComponent(notelpField))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(alamatLabel)
                                .addComponent(alamatField))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(createButton)
                                .addComponent(updateButton)
                                .addComponent(deleteButton)
                                .addComponent(refreshButton))
        );

        tableModel = new DefaultTableModel(new String[]{"ID", "Nama", "NIK", "No Telp", "Alamat"}, 0);
        table = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(table);

        add(inputPanel, BorderLayout.NORTH);
        add(tableScrollPane, BorderLayout.CENTER);

        setSize(800, 600);
        refreshTable();
    }

    private void handleCreate(ActionEvent e) {
        pelangganService.createPelanggan(
                namaField.getText(),
                nikField.getText(),
                notelpField.getText(),
                alamatField.getText()
        );
        refreshTable();
        clearFields();
    }

    private void handleUpdate(ActionEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            int id = (int) tableModel.getValueAt(selectedRow, 0);
            pelangganService.updatePelanggan(
                    id,
                    namaField.getText(),
                    nikField.getText(),
                    notelpField.getText(),
                    alamatField.getText()
            );
            refreshTable();
            clearFields();
        } else {
            JOptionPane.showMessageDialog(this, "Select a row to update.");
        }
    }

    private void handleDelete(ActionEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            int id = (int) tableModel.getValueAt(selectedRow, 0);
            pelangganService.deletePelanggan(id);
            refreshTable();
        } else {
            JOptionPane.showMessageDialog(this, "Select a row to delete.");
        }
    }

    private void refreshTable() {
        List<Pelanggan> pelangganList = pelangganService.readPelanggan();
        tableModel.setRowCount(0);
        for (Pelanggan pelanggan : pelangganList) {
            tableModel.addRow(new Object[]{
                    pelanggan.getId(),
                    pelanggan.getNama(),
                    pelanggan.getNik(),
                    pelanggan.getNotelp(),
                    pelanggan.getAlamat()
            });
        }
    }

    private void clearFields() {
        namaField.setText("");
        nikField.setText("");
        notelpField.setText("");
        alamatField.setText("");
    }
}
