package dev.guarmo.jwttokenserver.model.transaction.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Data
public class GetTransactionDto {
    private Long id;
    private Long transactionId;
    private Double amount;
    private String address;
    private String destTag;
    private Long ownerId; // Just marker, no relations in DB
    private String currency;
    private String status;
    private Integer blockchainConfirmations;
    private String fee;
    private String blockchainHash;
    private LocalDateTime createdAt;
}
