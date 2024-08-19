package com.example.OnlineBankingSystem.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.OnlineBankingSystem.Entity.Account;
import com.example.OnlineBankingSystem.Entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{
	List<Transaction> findByFromAccountOrToAccount(Account fromAccount, Account toAccount);
}
