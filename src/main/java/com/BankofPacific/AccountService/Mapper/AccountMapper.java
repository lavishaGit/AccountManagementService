package com.BankofPacific.AccountService.Mapper;

import com.BankofPacific.AccountService.Dto.Accountdto;
import com.BankofPacific.AccountService.Model.Account;

import java.time.LocalDateTime;

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

}
