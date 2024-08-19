package com.example.OnlineBankingSystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.OnlineBankingSystem.Entity.Account;

public interface AccountRepository extends JpaRepository<Account,String> {

}
