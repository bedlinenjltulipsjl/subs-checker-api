package dev.guarmo.jwttokenserver.model.income.dto;

 import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostIncomeDto {
    private Double bonusAmount;
    private String incomeCausedByUserTgName;
}
