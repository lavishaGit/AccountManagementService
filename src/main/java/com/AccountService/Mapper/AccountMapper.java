package com.AccountService.Mapper;

import com.AccountService.Dto.AccountUpdateDto;
import com.AccountService.Dto.Accountdto;
import com.AccountService.Model.Account;

import java.math.BigDecimal;

public class AccountMapper
{
//from  account jpa database to dto
    public static Account mapToAccount(Accountdto accountdto) {
        Account account = new Account(accountdto.getUserId(),accountdto.getAccountID(),accountdto.getAccountNumber(),accountdto.getAccountType(),accountdto.getCurrency(),   accountdto.getBalance(), accountdto.getCreatedAt());
        return account;

    }

    //from accountdto to accpontJpa
    public static Accountdto maptoAccountdto(Account account){

        Accountdto accountdto=new Accountdto(account.getUserId(),account.getAccountID(), account.getAccountNumber(), account.getAccountType(), account.getCurrency(), account.getBalance(),account.getCreatedAt() );


        return accountdto;
    }
        // Update an existing Account entity with AccountDTO data
    public static void updateEntity(Account account, AccountUpdateDto accountDTO) {
        account.setAccountType(accountDTO.getAccountType());
        account.setBalance(accountDTO.getBalance());
        account.setCurrency(accountDTO.getCurrency());
    }

}
