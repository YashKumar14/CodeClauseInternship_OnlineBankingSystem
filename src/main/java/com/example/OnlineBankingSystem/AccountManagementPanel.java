package com.example.OnlineBankingSystem;

import javax.swing.*;
import com.example.OnlineBankingSystem.Entity.Account;
import java.awt.*;

@SuppressWarnings("serial")
public class AccountManagementPanel extends JPanel {

    private JTextField accountNumberField;
    private JTextField accountHolderField;
    private JTextField balanceField;

    public AccountManagementPanel() {
        setLayout(new GridLayout(4, 2));

        JLabel accountNumberLabel = new JLabel("Account Number:");
        accountNumberField = new JTextField();
        JLabel accountHolderLabel = new JLabel("Account Holder:");
        accountHolderField = new JTextField();
        JLabel balanceLabel = new JLabel("Balance:");
        balanceField = new JTextField();

        JButton addButton = new JButton("Add Account");
        addButton.addActionListener(e -> addAccount());

        add(accountNumberLabel);
        add(accountNumberField);
        add(accountHolderLabel);
        add(accountHolderField);
        add(balanceLabel);
        add(balanceField);
        add(new JLabel());
        add(addButton);
    }

    private void addAccount() {
        try {
            String accountNumber = accountNumberField.getText();
            String accountHolder = accountHolderField.getText();
            double balance = Double.parseDouble(balanceField.getText());

            Account account = new Account();
            account.setAccountNumber(accountNumber);
            account.setAccountHolder(accountHolder);
            account.setBalance(balance);
            RestClient.addAccount(account);

            JOptionPane.showMessageDialog(this, "Account added successfully!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Failed to add account: " + e.getMessage());
        }
    }
}
