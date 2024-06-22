package dev.guarmo.jwttokenserver.model.invoice.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GenerateInvoiceDto {
    private String login;
    private String email;
    private String currencyCode;
    private Double amount;
}
