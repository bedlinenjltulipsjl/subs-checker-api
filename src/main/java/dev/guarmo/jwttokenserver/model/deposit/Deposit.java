package dev.guarmo.jwttokenserver.model.deposit;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Deposit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long transactionId;
    private Double amount;
    private String address;
    private String destTag;
    private String label;
    private String currency;
    private String status;
    private Integer blockchainConfirmations;
    private String fee;
    private String blockchainHash;
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;
}
