package com.BankofPacific.AccountService.Controllers;

import com.BankofPacific.AccountService.Dto.AccountResponseDto;
import com.BankofPacific.AccountService.Dto.Accountdto;
import com.BankofPacific.AccountService.Model.Account;
import com.BankofPacific.AccountService.Services.AccountServiceImpl;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
public class AccountController {
    private static final Logger logger = LogManager.getLogger(AccountController.class);
    private final AccountServiceImpl accountService;

    @PostMapping("/account")
    public ResponseEntity<AccountResponseDto> addAccount(@RequestBody Accountdto accountdto) {
             accountService.addAccount(accountdto);
        // Prepare the response DTO
        AccountResponseDto response = new AccountResponseDto(

                "Successfully account created"
        );
        return ResponseEntity.status( HttpStatus.CREATED).body(response);
    }

    @PutMapping("/account/{accountId}")
    public ResponseEntity<String> updateAccount(@PathVariable Long accountId ,@RequestBody Accountdto accountdto) throws Exception {
        accountService.updateAccount(accountId,accountdto);
        return  ResponseEntity.status(HttpStatus.OK).body("Updated account details");
    }
    @GetMapping("/account")
    public ResponseEntity<List<Account>> getAccount() {

        return new ResponseEntity<>(accountService.getAllAccounts(), HttpStatus.OK);
    }

    @GetMapping("/account/{accountID}")
    public ResponseEntity<Accountdto>getAccountbyID(@PathVariable Long accountID) throws Exception {
     Accountdto accountdto=  accountService.getAccountById(accountID);
        return ResponseEntity.status(HttpStatus.OK).body(accountdto);
    }
    @DeleteMapping("/account/{accountID}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long accountID) {
        try {
            accountService.deleteAccount(accountID);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();  // Returns 204 No Content
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Returns 404 Not Found
        }}
        // Endpoint: GET /account?userId={userId}
        @GetMapping("/accounts")
        public ResponseEntity<List<Account>> getAccountsByUserId(@RequestParam("userId") Long userId) {
            List<Account> accounts = accountService.getAccountsByUserId(userId);
            if (accounts.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(accounts);
        }


    @ExceptionHandler
    public ResponseEntity<String> responseWithError(Exception e) {
        logger.error("Exception Occured.Details : {}", e.getMessage());
        return new ResponseEntity<>("Exception Occured.More Info :"
                + e.getMessage(), HttpStatus.BAD_REQUEST);

}}