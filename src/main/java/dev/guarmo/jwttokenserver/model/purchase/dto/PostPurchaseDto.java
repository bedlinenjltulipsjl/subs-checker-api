package dev.guarmo.jwttokenserver.model.purchase.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostPurchaseDto {
    private Double purchaseAmount;
    private String description;
}
