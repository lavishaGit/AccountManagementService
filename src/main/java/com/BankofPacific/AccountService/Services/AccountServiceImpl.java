package com.BankofPacific.AccountService.Services;

import com.BankofPacific.AccountService.Dto.Accountdto;
import com.BankofPacific.AccountService.Mapper.AccountMapper;
import com.BankofPacific.AccountService.Model.Account;
import com.BankofPacific.AccountService.Repositories.AccountRepository;
import com.BankofPacific.AccountService.Services.Interfaces.AccountService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountrepo;
    private static final Logger logger = LogManager.getLogger(AccountServiceImpl.class);

    public AccountServiceImpl(AccountRepository accountrepo) {
        this.accountrepo = accountrepo;
    }

    @Override
    public Accountdto addAccount(Accountdto accountdto) {
        logger.info("new request to addAccount is executed");
        Account account = AccountMapper.mapToAccount(accountdto);
        Account savedAccount = accountrepo.save(account);
        return AccountMapper.maptoAccountdto(savedAccount);
    }

    @Override
    public Accountdto getAccountById(Long accountID) throws Exception {
        logger.info("new request to getAccountbyID is executed");
        Optional<Account> accountDetails = accountrepo.findById(accountID);
        if (accountDetails.isPresent()) {
            Account getAccount = accountDetails.get();

            return AccountMapper.maptoAccountdto(getAccount);
        } else throw new Exception("AccountId is not valid");
    }

    @Override
    public List<Account> getAllAccounts() {

        return accountrepo.findAll();
    }

    @Override
    public void deleteAccount(Long accountId) throws Exception {
        if (!accountrepo.existsById(accountId)) {
            throw new Exception("AccountId not found");
        }
        accountrepo.deleteById(accountId);


    }

    @Override
    public List<Account> getAccountsByUserId(Long userId) {
        return accountrepo.findByUserId(userId);

    }

    public Accountdto updateAccount(Long accountId, Accountdto accountdto) throws Exception {
        logger.info("new request to updateAccount is executed");
        Optional<Account> accountDetails = accountrepo.findById(accountId);
        if (accountDetails.isPresent()) {
            Account account = accountDetails.get();
            //Update fields from accountdto
            account.setAccountNumber(accountdto.getAccountNumber());
            account.setAccountType(accountdto.getAccountType());
            account.setBalance(accountdto.getBalance());
            account.setCurrency(accountdto.getCurrency());

            // Save the updated account entity
            Account updatedAccount = accountrepo.save(account);

            // Map the updated entity back to DTO
            return AccountMapper.maptoAccountdto(updatedAccount);
        } else {
            throw new Exception("Account with ID " + accountId + " not found");
        }
    }


}

