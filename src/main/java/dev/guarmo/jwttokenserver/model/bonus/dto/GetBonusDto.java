package dev.guarmo.jwttokenserver.model.bonus.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Data
public class GetBonusDto {
    private Long id;
    private Double bonusAmount;
    private LocalDateTime createdAt;
    private String bonusFromLogin;
    private String bonusFromUserTgName;
    private String bonusOwnerUpperReferralTgName;
}
