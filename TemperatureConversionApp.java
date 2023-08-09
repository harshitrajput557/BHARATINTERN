package com.company;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TemperatureConversionApp extends JFrame implements ActionListener {

    private JTextField inputField;
    private JComboBox<String> unitComboBox;
    private JTextArea displayArea;
    private JButton convertButton;

    public TemperatureConversionApp() {
        setTitle("Temperature Converter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(500, 300)); // Set the preferred size of the panel
        getContentPane().setBackground(Color.WHITE);

        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.setBackground(Color.WHITE);

        inputField = new JTextField(10);
        inputField.setHorizontalAlignment(JTextField.RIGHT);
        inputField.setFont(inputField.getFont().deriveFont(Font.BOLD, 16f));
        inputField.setForeground(Color.GRAY);
        inputPanel.add(inputField, BorderLayout.WEST);

        String[] units = {"Celsius", "Fahrenheit", "Kelvin"};
        unitComboBox = new JComboBox<>(units);
        inputPanel.add(unitComboBox, BorderLayout.CENTER);

        convertButton = new JButton("Convert");
        convertButton.setBackground(Color.GRAY);
        convertButton.setForeground(Color.WHITE);
        convertButton.addActionListener(this);
        inputPanel.add(convertButton, BorderLayout.EAST);

        add(inputPanel, BorderLayout.NORTH);

        displayArea = new JTextArea(10, 20);
        displayArea.setEditable(false);
        displayArea.setFont(displayArea.getFont().deriveFont(Font.PLAIN, 16f));
        displayArea.setForeground(Color.GRAY);
        displayArea.setBackground(Color.WHITE);
        JScrollPane scrollPane = new JScrollPane(displayArea);
        add(scrollPane, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String inputText = inputField.getText();
        String selectedUnit = (String) unitComboBox.getSelectedItem();

        if (!inputText.isEmpty()) {
            try {
                double inputValue = Double.parseDouble(inputText);
                double celsius = 0;
                double fahrenheit = 0;
                double kelvin = 0;

                switch (selectedUnit) {
                    case "Celsius":
                        celsius = inputValue;
                        fahrenheit = (inputValue * 9 / 5) + 32;
                        kelvin = inputValue + 273.15;
                        break;
                    case "Fahrenheit":
                        fahrenheit = inputValue;
                        celsius = (inputValue - 32) * 5 / 9;
                        kelvin = (inputValue + 459.67) * 5 / 9;
                        break;
                    case "Kelvin":
                        kelvin = inputValue;
                        celsius = inputValue - 273.15;
                        fahrenheit = (inputValue * 9 / 5) - 459.67;
                        break;
                }

                String outputText = String.format("Output in Celsius: %.2f °C\n", celsius);
                outputText += String.format("Output in Fahrenheit: %.2f °F\n", fahrenheit);
                outputText += String.format("Output in Kelvin: %.2f K\n", kelvin);

                displayArea.setText(outputText);
            } catch (NumberFormatException ex) {
                displayArea.setText("Invalid input. Please enter a valid number.");
            }
        } else {
            displayArea.setText("Please enter a temperature value.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TemperatureConversionApp());
    }
}
