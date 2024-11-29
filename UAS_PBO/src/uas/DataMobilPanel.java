package uas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class DataMobilPanel extends JPanel {
    private JTextField merkField, tahunField, hargaField;
    private JTable table;
    private DefaultTableModel tableModel;
    private DataMobilService dataMobilService;

    public DataMobilPanel() {
        dataMobilService = new DataMobilService();

        setLayout(new BorderLayout());

        // Input Panel
        JPanel inputPanel = new JPanel();
        JLabel merkLabel = new JLabel("Merk:");
        merkField = new JTextField(20);
        JLabel tahunLabel = new JLabel("Tahun:");
        tahunField = new JTextField(20);
        JLabel hargaLabel = new JLabel("Harga:");
        hargaField = new JTextField(20);

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
                                .addComponent(merkLabel)
                                .addComponent(tahunLabel)
                                .addComponent(hargaLabel))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(merkField)
                                .addComponent(tahunField)
                                .addComponent(hargaField)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(createButton)
                                        .addComponent(updateButton)
                                        .addComponent(deleteButton)
                                        .addComponent(refreshButton)))
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(merkLabel)
                                .addComponent(merkField))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(tahunLabel)
                                .addComponent(tahunField))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(hargaLabel)
                                .addComponent(hargaField))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(createButton)
                                .addComponent(updateButton)
                                .addComponent(deleteButton)
                                .addComponent(refreshButton))
        );

        tableModel = new DefaultTableModel(new String[]{"ID", "Merk", "Tahun", "Harga"}, 0);
        table = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(table);

        add(inputPanel, BorderLayout.NORTH);
        add(tableScrollPane, BorderLayout.CENTER);

        setSize(800, 600);
        refreshTable();
    }

    private void handleCreate(ActionEvent e) {
        dataMobilService.createMobil(
                merkField.getText(),
                Integer.parseInt(tahunField.getText()),
                Double.parseDouble(hargaField.getText())
        );
        refreshTable();
        clearFields();
    }

    private void handleUpdate(ActionEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            int id = (int) tableModel.getValueAt(selectedRow, 0);
            dataMobilService.updateMobil(
                    id,
                    merkField.getText(),
                    Integer.parseInt(tahunField.getText()),
                    Double.parseDouble(hargaField.getText())
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
            dataMobilService.deleteMobil(id);
            refreshTable();
        } else {
            JOptionPane.showMessageDialog(this, "Select a row to delete.");
        }
    }

    private void refreshTable() {
        List<DataMobil> mobilList = dataMobilService.readMobil();
        tableModel.setRowCount(0);
        for (DataMobil mobil : mobilList) {
            tableModel.addRow(new Object[]{
                    mobil.getId(),
                    mobil.getMerk(),
                    mobil.getTahun(),
                    mobil.getHarga()
            });
        }
    }

    private void clearFields() {
        merkField.setText("");
        tahunField.setText("");
        hargaField.setText("");
    }
}
