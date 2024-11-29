package uas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.util.List;

public class DataPenjualanFrame extends JFrame {
    private JTextField pelangganIdField, mobilIdField, totalBiayaField;
    private JTable table;
    private DefaultTableModel tableModel;
    private DataPenjualanService dataPenjualanService;

    public DataPenjualanFrame() {
        dataPenjualanService = new DataPenjualanService();

        setTitle("CRUD Data Penjualan");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        
        JPanel inputPanel = new JPanel();
        JLabel pelangganIdLabel = new JLabel("ID Pelanggan:");
        pelangganIdField = new JTextField(20);
        JLabel mobilIdLabel = new JLabel("ID Mobil:");
        mobilIdField = new JTextField(20);
        JLabel totalBiayaLabel = new JLabel("Total Biaya:");
        totalBiayaField = new JTextField(20);

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
                                .addComponent(pelangganIdLabel)
                                .addComponent(mobilIdLabel)
                                .addComponent(totalBiayaLabel))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(pelangganIdField)
                                .addComponent(mobilIdField)
                                .addComponent(totalBiayaField)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(createButton)
                                        .addComponent(updateButton)
                                        .addComponent(deleteButton)
                                        .addComponent(refreshButton)))
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(pelangganIdLabel)
                                .addComponent(pelangganIdField))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(mobilIdLabel)
                                .addComponent(mobilIdField))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(totalBiayaLabel)
                                .addComponent(totalBiayaField))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(createButton)
                                .addComponent(updateButton)
                                .addComponent(deleteButton)
                                .addComponent(refreshButton))
        );

        
        tableModel = new DefaultTableModel(new String[]{"ID Penjualan", "ID Pelanggan", "ID Mobil", "Total Biaya"}, 0);
        table = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(table);

        
        JPanel mainPanel = new JPanel();
        GroupLayout mainLayout = new GroupLayout(mainPanel);
        mainPanel.setLayout(mainLayout);
        mainLayout.setAutoCreateGaps(true);
        mainLayout.setAutoCreateContainerGaps(true);

        mainLayout.setHorizontalGroup(
                mainLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(inputPanel)
                        .addComponent(tableScrollPane)
        );

        mainLayout.setVerticalGroup(
                mainLayout.createSequentialGroup()
                        .addComponent(inputPanel)
                        .addComponent(tableScrollPane)
        );

        add(mainPanel);

        setSize(800, 600);
        refreshTable();
    }

    private void handleCreate(ActionEvent e) {
        dataPenjualanService.createPenjualan(
                Integer.parseInt(pelangganIdField.getText()),
                Integer.parseInt(mobilIdField.getText()),
                Double.parseDouble(totalBiayaField.getText())
        );
        refreshTable();
        clearFields();
    }

    private void handleUpdate(ActionEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            try {
                
                int id = Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString());

                
                int pelangganId = Integer.parseInt(pelangganIdField.getText());
                int mobilId = Integer.parseInt(mobilIdField.getText());
                double totalBiaya = Double.parseDouble(totalBiayaField.getText());

                
                dataPenjualanService.updatePenjualan(id, pelangganId, mobilId, totalBiaya);

                
                refreshTable();
                clearFields();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid number format. Please check the input fields.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Select a row to update.");
        }
    }


    private void handleDelete(ActionEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            int id = (int) tableModel.getValueAt(selectedRow, 0);
            dataPenjualanService.deletePenjualan(id);
            refreshTable();
        } else {
            JOptionPane.showMessageDialog(this, "Select a row to delete.");
        }
    }

    private void refreshTable() {
        List<DataPenjualan> penjualanList = dataPenjualanService.readPenjualan();
        tableModel.setRowCount(0);
        for (DataPenjualan penjualan : penjualanList) {
            tableModel.addRow(new Object[]{
                    penjualan.getId(),
                    penjualan.getPelangganId(),
                    penjualan.getMobilId(),
                    penjualan.getTotalBiaya()
            });
        }
    }

    private void clearFields() {
        pelangganIdField.setText("");
        mobilIdField.setText("");
        totalBiayaField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DataPenjualanFrame().setVisible(true));
    }
}
