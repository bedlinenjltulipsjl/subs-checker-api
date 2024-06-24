package dev.guarmo.jwttokenserver.model.withdraw;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class MoneyWithdraw {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double withdrawAmount;
    private String currency;
    private String cryptoAddress;
    private String description;
    private String email;
    private WithdrawStatus withdrawStatus;
}
