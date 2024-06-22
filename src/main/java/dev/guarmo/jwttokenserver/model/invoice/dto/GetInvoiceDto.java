package dev.guarmo.jwttokenserver.model.invoice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetInvoiceDto {
    private String token;
    private String url;
    private String expireAt;
    private String currencyCode;
    private Double amount;
    private String address;
    private Long label;
    private String destTag;
    private String successUrl;
    private String error;
}
