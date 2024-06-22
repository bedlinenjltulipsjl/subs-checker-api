package dev.guarmo.jwttokenserver.model.invoice;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonProperty("token")
    private String token;

    @JsonProperty("url")
    private String url;

    @JsonProperty("expire_at")
    private String expireAt;

    @JsonProperty("currency_code")
    private String currencyCode;

    @JsonProperty("amount")
    private Double amount;

    @JsonProperty("address")
    private String address;

    @JsonProperty("dest_tag")
    private String destTag;

    @JsonIgnore
    private String label;

    @JsonIgnore
    private String email;

    @JsonProperty("success_url")
    private String successUrl;

    @JsonProperty("error")
    private String error;
}
