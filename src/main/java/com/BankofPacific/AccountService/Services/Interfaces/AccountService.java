package com.BankofPacific.AccountService.Services.Interfaces;

import com.BankofPacific.AccountService.Dto.Accountdto;
import com.BankofPacific.AccountService.Model.Account;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    Accountdto addAccount(Accountdto account);


   Accountdto getAccountById(Long accountID) throws Exception;
    List<Account> getAllAccounts();

   void deleteAccount(Long accountId) throws Exception;
    List<Account> getAccountsByUserId(Long userId);

}
