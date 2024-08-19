package com.example.OnlineBankingSystem.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.OnlineBankingSystem.Entity.Account;
import com.example.OnlineBankingSystem.Entity.Transaction;
import com.example.OnlineBankingSystem.Repository.TransactionRepository;
import com.example.OnlineBankingSystem.Service.AccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/accounts")
public class AccountController {
	@Autowired
	private AccountService accountService;
	@Autowired
	private TransactionRepository transactionRepository;
	
	@PostMapping
	public Account addAccount(@RequestBody Account account) {
		return accountService.addAccount(account);
	}
	
	@PostMapping("/transfer")
	public void transferFunds(@RequestParam String fromAccount, @RequestParam String toAccount, @RequestParam double amount) {
		accountService.transferFunds(fromAccount, toAccount, amount);
	}
	
	@GetMapping
	public List<Account> getAllAccounts(){
		return accountService.getAllAccounts();
	}
	
	@GetMapping("/{accountNumber}")
	public Account getAccountByNumber(@PathVariable String accountNumber) {
		return accountService.getAccountByNumber(accountNumber);
	}
	
	@GetMapping("/{accountNumber}/transactions")
	public List<Transaction> getTransactionsByAccount(@PathVariable String accountNumber) {
	    Account account = accountService.getAccountByNumber(accountNumber);
	    return transactionRepository.findByFromAccountOrToAccount(account, account);
	}

}
