package com.company;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorApp extends JFrame implements ActionListener {

    private JTextField displayField;
    private JButton[] numberButtons;
    private JButton[] operationButtons;
    private JButton equalsButton;
    private JButton clearButton;
    private JButton backspaceButton;
    private JPanel buttonPanel;
    private StringBuilder currentInput;
    private StringBuilder completeInput;
    private double num1, num2, result;
    private char operator;

    public CalculatorApp() {
        setTitle("Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);

        displayField = new JTextField(20);
        displayField.setEditable(false);
        displayField.setBackground(Color.WHITE);
        displayField.setForeground(Color.GRAY);
        displayField.setFont(displayField.getFont().deriveFont(displayField.getFont().getSize() * 2f));
        displayField.setPreferredSize(new Dimension(displayField.getPreferredSize().width, displayField.getPreferredSize().height * 2));
        add(displayField, BorderLayout.NORTH);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 4));
        buttonPanel.setBackground(Color.GRAY);

        // Initialize number buttons
        numberButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].setBackground(Color.GRAY);
            numberButtons[i].setForeground(Color.WHITE);
            numberButtons[i].addActionListener(this);
            buttonPanel.add(numberButtons[i]);
        }

        // Initialize operation buttons
        operationButtons = new JButton[4];
        operationButtons[0] = new JButton("+");
        operationButtons[1] = new JButton("-");
        operationButtons[2] = new JButton("*");
        operationButtons[3] = new JButton("/");
        for (int i = 0; i < 4; i++) {
            operationButtons[i].setBackground(Color.GRAY);
            operationButtons[i].setForeground(Color.WHITE);
            operationButtons[i].addActionListener(this);
            buttonPanel.add(operationButtons[i]);
        }

        // Initialize other buttons
        equalsButton = new JButton("=");
        equalsButton.setBackground(Color.GRAY);
        equalsButton.setForeground(Color.WHITE);
        equalsButton.addActionListener(this);
        clearButton = new JButton("C");
        clearButton.setBackground(Color.GRAY);
        clearButton.setForeground(Color.WHITE);
        clearButton.addActionListener(this);
        backspaceButton = new JButton("⌫");
        backspaceButton.setBackground(Color.GRAY);
        backspaceButton.setForeground(Color.WHITE);
        backspaceButton.addActionListener(this);

        buttonPanel.add(equalsButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(backspaceButton);

        add(buttonPanel, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setSize(400, 400);

        currentInput = new StringBuilder();
        completeInput = new StringBuilder();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        // Handle button clicks
        if (Character.isDigit(command.charAt(0))) {
            currentInput.append(command);
            completeInput.append(command);
            displayField.setText(completeInput.toString());
        } else if (command.equals("C")) {
            currentInput.setLength(0);
            completeInput.setLength(0);
            displayField.setText("");
            num1 = num2 = result = 0;
            operator = '\0';
        } else if (command.equals("=")) {
            num2 = Double.parseDouble(currentInput.toString());
            completeInput.append(" =");
            switch (operator) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '*':
                    result = num1 * num2;
                    break;
                case '/':
                    result = num1 / num2;
                    break;
            }
            currentInput.setLength(0);
            currentInput.append(result);
            completeInput.append(" ");
            completeInput.append(result);
            displayField.setText(completeInput.toString());
            num1 = result;
            operator = '\0';
        } else if (command.equals("⌫")) {
            if (currentInput.length() > 0) {
                currentInput.deleteCharAt(currentInput.length() - 1);
                if (completeInput.length() > 0) {
                    char lastChar = completeInput.charAt(completeInput.length() - 1);
                    if (lastChar == ' ') {
                        completeInput.deleteCharAt(completeInput.length() - 1);
                    }
                }
                completeInput.deleteCharAt(completeInput.length() - 1);
                displayField.setText(completeInput.toString());
            }
        } else {
            if (operator != '\0') {
                completeInput.setLength(completeInput.length() - 1);
            }
            num1 = Double.parseDouble(currentInput.toString());
            currentInput.setLength(0);
            completeInput.append(command);
            displayField.setText(completeInput.toString());
            operator = command.charAt(0);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CalculatorApp());
    }
}
