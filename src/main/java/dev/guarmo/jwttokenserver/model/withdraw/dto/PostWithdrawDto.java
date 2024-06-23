package dev.guarmo.jwttokenserver.model.withdraw.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostWithdrawDto {
    private Double withdrawAmount;
    private String currency;
    private String cryptoAddress;
    private String description;
    private String email;
}
