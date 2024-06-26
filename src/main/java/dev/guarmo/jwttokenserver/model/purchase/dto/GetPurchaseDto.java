package dev.guarmo.jwttokenserver.model.purchase.dto;

import java.time.LocalDateTime;

public class GetPurchaseDto {
    private Long id;
    private Double purchaseAmount;
    private String description;
    private LocalDateTime createdAt;
}
