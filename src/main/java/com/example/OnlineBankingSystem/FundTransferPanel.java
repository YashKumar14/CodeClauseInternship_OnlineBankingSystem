package com.example.OnlineBankingSystem;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class FundTransferPanel extends JPanel {

    private JTextField fromAccountField;
    private JTextField toAccountField;
    private JTextField amountField;

    public FundTransferPanel() {
        setLayout(new GridLayout(4, 2));

        JLabel fromAccountLabel = new JLabel("From Account:");
        fromAccountField = new JTextField();
        JLabel toAccountLabel = new JLabel("To Account:");
        toAccountField = new JTextField();
        JLabel amountLabel = new JLabel("Amount:");
        amountField = new JTextField();

        JButton transferButton = new JButton("Transfer");
        transferButton.addActionListener(e -> transferFunds());

        add(fromAccountLabel);
        add(fromAccountField);
        add(toAccountLabel);
        add(toAccountField);
        add(amountLabel);
        add(amountField);
        add(new JLabel());
        add(transferButton);
    }

    private void transferFunds() {
        try {
            String fromAccount = fromAccountField.getText();
            String toAccount = toAccountField.getText();
            double amount = Double.parseDouble(amountField.getText());

            RestClient.transferFunds(fromAccount, toAccount, amount);

            JOptionPane.showMessageDialog(this, "Funds transferred successfully!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Failed to transfer funds: " + e.getMessage());
        }
    }
}

