package dev.guarmo.jwttokenserver.model.deposit.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDepositDto {
    private Long transactionId;
    private Double amount;
    private String address;
    private String destTag;
    private String label;
    private String currency;
    private String status;
    private Integer blockchainConfirmations;
    private String fee;
    private String blockchainHash;
}
