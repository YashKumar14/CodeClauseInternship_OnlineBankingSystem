package com.example.OnlineBankingSystem.Entity;

import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Transaction {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime date;
    private double amount;
    
    @ManyToOne
    @JoinColumn(name = "from_account_number", nullable = false)
    private Account fromAccount;
    
    @ManyToOne
    @JoinColumn(name = "to_account_number", nullable = false)
    private Account toAccount;
    
    public Transaction() {} 
    
    public Transaction(LocalDateTime date, double amount, Account fromAccount, Account toAccount) {
        this.date = date;
        this.amount = amount;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Account getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(Account fromAccount) {
        this.fromAccount = fromAccount;
    }

    public Account getToAccount() {
        return toAccount;
    }

    public void setToAccount(Account toAccount) {
        this.toAccount = toAccount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
               "date=" + date +
               ", amount=" + amount +
               ", fromAccount=" + fromAccount.getAccountNumber() +
               ", toAccount=" + toAccount.getAccountNumber() +
               '}';
    }

}
