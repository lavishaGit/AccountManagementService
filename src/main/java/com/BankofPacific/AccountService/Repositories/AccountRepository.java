package com.BankofPacific.AccountService.Repositories;

import com.BankofPacific.AccountService.Model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
   List<Account> findByUserId(Long userId);  //to serach by userId http://localhost:8020/account?userId
//SELECT * FROM account WHERE user_id = ?;
}
