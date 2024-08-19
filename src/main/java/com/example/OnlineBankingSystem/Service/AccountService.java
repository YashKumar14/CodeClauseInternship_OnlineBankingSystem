package com.example.OnlineBankingSystem.Service;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.OnlineBankingSystem.Entity.Account;
import com.example.OnlineBankingSystem.Entity.Transaction;
import com.example.OnlineBankingSystem.Repository.AccountRepository;
import com.example.OnlineBankingSystem.Repository.TransactionRepository;
import jakarta.transaction.Transactional;

@Service
public class AccountService {
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
    private TransactionRepository transactionRepository;

    @Transactional
    public void transferFunds(String fromAccountNumber, String toAccountNumber, double amount) {
        Account fromAccount = accountRepository.findById(fromAccountNumber).orElseThrow();
        Account toAccount = accountRepository.findById(toAccountNumber).orElseThrow();

        if (fromAccount.getBalance() >= amount) {
            fromAccount.setBalance(fromAccount.getBalance() - amount);
            toAccount.setBalance(toAccount.getBalance() + amount);

            Transaction transaction = new Transaction(LocalDateTime.now(), amount, fromAccount, toAccount);
            transactionRepository.save(transaction);
            
            accountRepository.save(fromAccount);
            accountRepository.save(toAccount);
        } else {
            throw new IllegalStateException("Insufficient balance");
        }
    }
	
	public Account addAccount(Account account) {
		return accountRepository.save(account);
	}
	
	public List<Account> getAllAccounts(){
		return accountRepository.findAll();
	}
	
	public Account getAccountByNumber(String accountNumber) {
		return accountRepository.findById(accountNumber).orElseThrow();
	}

}
