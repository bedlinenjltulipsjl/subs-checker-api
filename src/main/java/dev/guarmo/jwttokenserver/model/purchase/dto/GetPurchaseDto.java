package dev.guarmo.jwttokenserver.model.purchase.dto;

import jakarta.persistence.Column;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

public class GetPurchaseDto {
    private Long id;
    private String purchaseAmount;
    private String description;
    private LocalDateTime createdAt;
}
