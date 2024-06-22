package dev.guarmo.jwttokenserver.model.transaction.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostTransactionDto {
    private Long transactionId;
    private Double amount;
    private String address;
    private String destTag;
    private Long ownerId;
    private String currency;
    private String status;
    private Integer blockchainConfirmations;
    private String fee;
    private String blockchainHash;
}
