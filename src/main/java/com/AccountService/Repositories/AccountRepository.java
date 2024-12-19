package com.AccountService.Repositories;

import com.AccountService.Model.Account;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
   List<Account> findByUserId(Long userId);  //to serach by userId http://localhost:8020/account?userId
   @Query(value = "SELECT * FROM accounts WHERE accountid = :accountId AND user_id = :userId", nativeQuery = true)
   Optional<Account> findByAccountIdAndUserId(Long accountId, Long userId);
   @Modifying
@Transactional
   @Query(value = "DELETE FROM accounts WHERE accountid = :accountId AND user_id = :userId", nativeQuery = true)
   void deleteByAccountIdAndUserId(Long accountId, Long userId);
 // Account findByUserId(Long userId);
}


