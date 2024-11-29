package uas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {

    private CardLayout cardLayout; // To manage switching between different panels
    private JPanel cardPanel; // Panel to hold different views (cards)

    public MainFrame() {
        setTitle("Main Navigator Frame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null); // Center the frame

        // Create CardLayout and set it as the layout manager for the cardPanel
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Create panels (views)
        DataPenjualanPanel dataPenjualanPanel = new DataPenjualanPanel();
        PelangganPanel pelangganPanel = new PelangganPanel();
        DataMobilPanel dataMobilPanel = new DataMobilPanel(); // New DataMobilPanel

        // Add the panels to the cardPanel
        cardPanel.add(dataPenjualanPanel, "Data Penjualan");
        cardPanel.add(pelangganPanel, "Data Pelanggan");
        cardPanel.add(dataMobilPanel, "Data Mobil"); // Add DataMobilPanel

        // Create a panel for navigation buttons
        JPanel navPanel = new JPanel();
        JButton dataPenjualanButton = new JButton("Data Penjualan");
        JButton dataPelangganButton = new JButton("Data Pelanggan");
        JButton dataMobilButton = new JButton("Data Mobil"); // Button for Data Mobil

        // Action listeners for navigation
        dataPenjualanButton.addActionListener((ActionEvent e) -> {
            cardLayout.show(cardPanel, "Data Penjualan");
        });

        dataPelangganButton.addActionListener((ActionEvent e) -> {
            cardLayout.show(cardPanel, "Data Pelanggan");
        });

        dataMobilButton.addActionListener((ActionEvent e) -> {
            cardLayout.show(cardPanel, "Data Mobil"); // Switch to Data Mobil panel
        });

        // Add buttons to the navigation panel
        navPanel.add(dataPenjualanButton);
        navPanel.add(dataPelangganButton);
        navPanel.add(dataMobilButton); // Add the button to navigate to Data Mobil

        // Layout the main frame
        setLayout(new BorderLayout());
        add(navPanel, BorderLayout.NORTH);
        add(cardPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        });
    }
}
