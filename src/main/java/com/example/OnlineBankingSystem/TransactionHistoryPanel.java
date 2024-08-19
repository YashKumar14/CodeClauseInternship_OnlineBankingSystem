package com.example.OnlineBankingSystem;

import javax.swing.*;
import com.example.OnlineBankingSystem.Entity.Account;
import com.example.OnlineBankingSystem.Entity.Transaction;
import java.util.List;
import java.awt.*;

@SuppressWarnings("serial")
public class TransactionHistoryPanel extends JPanel {

    private JTextArea transactionArea;
    private JTextField accountNumberField;

    public TransactionHistoryPanel() {
        setLayout(new BorderLayout());

        JLabel accountNumberLabel = new JLabel("Account Number:");
        accountNumberField = new JTextField();
        JButton viewButton = new JButton("View Transactions");
        viewButton.addActionListener(e -> viewTransactions());

        JPanel topPanel = new JPanel(new GridLayout(1, 3));
        topPanel.add(accountNumberLabel);
        topPanel.add(accountNumberField);
        topPanel.add(viewButton);

        transactionArea = new JTextArea();
        transactionArea.setEditable(false);

        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(transactionArea), BorderLayout.CENTER);
    }

    private void viewTransactions() {
        String accountNumber = accountNumberField.getText();
        if (accountNumber == null || accountNumber.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Account number cannot be empty.");
            return;
        }

        try {
            Account account = RestClient.getAccountByNumber(accountNumber);
            if (account == null) {
                transactionArea.setText("Account not found.");
                return;
            }

            List<Transaction> transactions = RestClient.getTransactionsByAccount(accountNumber);
            StringBuilder transactionDetails = new StringBuilder();
            if (transactions == null || transactions.isEmpty()) {
            	transactionDetails.append("No transactions available for this account.");
            } else {
            	for (Transaction transaction : transactions) {
                transactionDetails.append("Date: ").append(transaction.getDate()).append("\n")
                                   .append("Amount: ").append(transaction.getAmount()).append("\n")
                                   .append("From Account: ").append(transaction.getFromAccount().getAccountNumber()).append("\n")
                                   .append("To Account: ").append(transaction.getToAccount().getAccountNumber()).append("\n\n");
            	}
            }

            transactionArea.setText("Account Number: " + account.getAccountNumber() + "\n" +
                                    "Account Holder: " + account.getAccountHolder() + "\n" +
                                    "Balance: " + account.getBalance() + "\n\n" +
                                    "Transaction History:\n" + transactionDetails.toString());
        
        }	catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Failed to retrieve transactions: " + e.getMessage());
        }
    }

}
