package com.AccountService.Controllers;

import com.AccountService.Dto.*;
import com.AccountService.Model.Account;
import com.AccountService.Services.AccountServiceImpl;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
public class AccountController {
    private static final Logger logger = LogManager.getLogger(AccountController.class);
    private final AccountServiceImpl accountService;

    @PostMapping("/accounts")
    public ResponseEntity<AccountResponseDto> addAccount(@RequestBody Accountdto accountdto) {
             accountService.addAccount(accountdto);
        // Prepare the response DTO
        AccountResponseDto response = new AccountResponseDto(

                "Successfully account created"
        );
        return ResponseEntity.status( HttpStatus.CREATED).body(response);
    }

    @PutMapping("/accounts/{accountId}")
    public ResponseEntity<String> updateAccount(@PathVariable Long accountId ,@RequestBody Accountdto accountdto) throws Exception {
        accountService.updateAccount(accountId,accountdto);
        return  ResponseEntity.status(HttpStatus.OK).body("Updated account details");
    }
    @GetMapping("/accounts")
    public ResponseEntity<List<Account>> getAccount() {

        return new ResponseEntity<>(accountService.getAllAccounts(), HttpStatus.OK);
    }

    @GetMapping("/accounts/{accountId}")
    public ResponseEntity<Accountdto>getAccountbyID(@PathVariable Long accountId) throws Exception {
     Accountdto accountdto=  accountService.getAccountById(accountId);
        return ResponseEntity.status(HttpStatus.OK).body(accountdto);
    }



    @DeleteMapping("/accounts/{accountId}")
    public ResponseEntity<DeleteAccountResponseDto> deleteAccount(@PathVariable Long accountId) {

        DeleteAccountResponseDto response = null;
        try {
 response=accountService.deleteAccount(accountId);
            return ResponseEntity.status(HttpStatus.OK).body(response);  // Returns 204 No Content
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response); // Returns 404 Not Found
        }}
//        // Endpoint: GET /account?userId={userId}
//        @GetMapping("/accounts")
//        public ResponseEntity<List<Account>> getAccountsByUserId(@RequestParam("userId") Long userId) {


@GetMapping("/accounts/users/{userId}")

    public ResponseEntity<List<Account>> getAccountsByUserId(@PathVariable Long userId) {

    List<Account> accounts = accountService.getAccountsByUserId(userId);
            if (accounts.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(accounts);
        }
    @GetMapping("/accounts/{accountId}/users/{userId}")
    public ResponseEntity<Account> getAccountByUserId(@PathVariable Long accountId,@PathVariable Long userId) {

    Account account = accountService.getAccountByUserId(accountId,userId);
        if (account==null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(account);
    }
    @PutMapping ("/accounts/{accountId}/users/{userId}")
    public ResponseEntity<AccountUpResponseDto> updateAccount(@PathVariable Long accountId, @PathVariable Long userId, @RequestBody AccountUpdateDto accountdto) {
        accountService.updateAccountDetails(accountId,userId,accountdto);
        // Prepare the response DTO
        AccountUpResponseDto response = new AccountUpResponseDto(

                "Successfully account Updated"
        );
        return ResponseEntity.status( HttpStatus.CREATED).body(response);
    }
    @DeleteMapping("accounts/{accountId}/users/{userId}")
    public ResponseEntity<DeleteAccountResponseDto> deleteAccount(
            @PathVariable Long accountId,
            @PathVariable Long userId) {

        DeleteAccountResponseDto response = accountService.unlinkAccount(accountId, userId);
        return ResponseEntity.ok(response);
    }

    @ExceptionHandler
    public ResponseEntity<String> responseWithError(Exception e) {
        logger.error("Exception Occured.Details : {}", e.getMessage());
        return new ResponseEntity<>("Exception Occured.More Info :"
                + e.getMessage(), HttpStatus.BAD_REQUEST);

}}