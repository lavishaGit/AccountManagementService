package com.AccountService.Services.Interfaces;

import com.AccountService.Dto.AccountUpdateDto;
import com.AccountService.Dto.Accountdto;
import com.AccountService.Dto.DeleteAccountResponseDto;
import com.AccountService.Model.Account;

import java.util.List;

public interface AccountService {
    Accountdto addAccount(Accountdto account);


   Accountdto getAccountById(Long accountID) throws Exception;
    List<Account> getAllAccounts();

    DeleteAccountResponseDto  deleteAccount(Long accountId) throws Exception;
    List<Account> getAccountsByUserId(Long userId);
    Account getAccountByUserId(Long accountId, Long userId);


   Account  updateAccountDetails(Long userId, Long accountId, AccountUpdateDto updatedAccount);
}
