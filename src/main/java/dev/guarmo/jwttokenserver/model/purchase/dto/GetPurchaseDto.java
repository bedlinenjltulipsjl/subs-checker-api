package dev.guarmo.jwttokenserver.model.purchase.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class GetPurchaseDto {
    private Long id;
    private Double purchaseAmount;
    private String description;
    private LocalDateTime createdAt;
}
