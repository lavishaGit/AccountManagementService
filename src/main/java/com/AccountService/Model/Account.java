package com.AccountService.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name ="Accounts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    private static final Logger logger = LogManager.getLogger(Account.class);
   @Column(name = "user_id", nullable = false)
    private Long userId; // Simple reference to user_id from User MS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountID;
    @Column(name = "account_number", nullable = false, unique = true, length = 20)
    private String accountNumber;// VARCHAR(20) UNIQUE, NOT NULL
    @Column(name = "account_Type", nullable = false,length = 20)
    private String accountType;
    @Column(name = "currency",length = 10, columnDefinition = "VARCHAR(10) DEFAULT 'USD'")
    private String currency; // VARCHAR(10) with default value 'USD'
    @Column(name = "balance",columnDefinition = "DECIMAL(15,2) DEFAULT 0.0")
    private Double balance; // DECIMAL(15,2) with default value 0.0
 @CreationTimestamp
 @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")  // Optional: to control date format
 @Column(name = "created_at")
    private LocalDateTime createdAt; // TIMESTAMP with default value CURRENT_TIMESTAMP



}

//
//    @ManyToOne
//    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false, foreignKey = @ForeignKey(name = "fk_user_account"))
//    private User user;

