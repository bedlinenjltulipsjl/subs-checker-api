package dev.guarmo.jwttokenserver.model.deposit.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GetDepositDto {
    private Long id;
    private Long transactionId;
    private Double amount;
    private String address;
    private String destTag;
    private String label; // Just marker, no relations in DB
    private String currency;
    private String status;
    private Integer blockchainConfirmations;
    private String fee;
    private String blockchainHash;
    private LocalDateTime createdAt;
}
