package com.AccountService.Dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Accountdto {
  private Long userId;
 private Long accountID;
    private String accountNumber;// VARCHAR(20) UNIQUE, NOT NULL
    private String accountType;
    private String currency; // VARCHAR(10) with default value 'USD'
    private Double balance; // DECIMAL(15,2) with default value 0.0
    private LocalDateTime createdAt;
}
