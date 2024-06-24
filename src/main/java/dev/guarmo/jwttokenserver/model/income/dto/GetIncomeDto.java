package dev.guarmo.jwttokenserver.model.income.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GetIncomeDto {
    private Long id;
    private Double bonusAmount;
    private LocalDateTime createdAt;
    private String incomeCausedByUserTgName;
    private String incomeSendToTgName;
}
