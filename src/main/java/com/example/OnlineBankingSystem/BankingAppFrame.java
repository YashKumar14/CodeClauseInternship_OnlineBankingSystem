package com.example.OnlineBankingSystem;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class BankingAppFrame extends JFrame {
    private JPanel mainPanel;
    private CardLayout cardLayout;

    public BankingAppFrame() {
        super("Online Banking System");

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(new AccountManagementPanel(), "AccountManagement");
        mainPanel.add(new FundTransferPanel(), "FundTransfer");
        mainPanel.add(new TransactionHistoryPanel(), "TransactionHistory");

        add(mainPanel, BorderLayout.CENTER);

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenuItem accountManagement = new JMenuItem("Account Management");
        JMenuItem fundTransfer = new JMenuItem("Fund Transfer");
        JMenuItem transactionHistory = new JMenuItem("Transaction History");

        accountManagement.addActionListener(e -> cardLayout.show(mainPanel, "AccountManagement"));
        fundTransfer.addActionListener(e -> cardLayout.show(mainPanel, "FundTransfer"));
        transactionHistory.addActionListener(e -> cardLayout.show(mainPanel, "TransactionHistory"));

        menu.add(accountManagement);
        menu.add(fundTransfer);
        menu.add(transactionHistory);
        menuBar.add(menu);
        setJMenuBar(menuBar);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BankingAppFrame frame = new BankingAppFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 400);
            frame.setVisible(true);
        });
    }
}

