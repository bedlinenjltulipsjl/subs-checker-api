package dev.guarmo.jwttokenserver.model.withdraw.dto;

import dev.guarmo.jwttokenserver.model.withdraw.WithdrawStatus;
import lombok.Data;

@Data
public class GetWithdrawDto {
    private Long id;
    private Double withdrawAmount;
    private String currency;
    private String cryptoAddress;
    private String description;
    private String email;
    private WithdrawStatus withdrawStatus;
}
