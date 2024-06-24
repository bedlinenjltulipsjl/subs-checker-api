package dev.guarmo.jwttokenserver.model.bonus.dto;

 import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostBonusDto {
    private Double bonusAmount;
    private String bonusFromLogin;
}
