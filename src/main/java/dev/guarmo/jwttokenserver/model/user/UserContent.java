package dev.guarmo.jwttokenserver.model.user;

import dev.guarmo.jwttokenserver.model.transaction.PayTransaction;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class UserContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String username;
    private double balanceAmount;
    @OneToMany
    private List<PayTransaction> transactions;
    @ManyToOne
    private UserCredentials upperReferral;
    @OneToMany
    private List<UserCredentials> bottomReferrals;
}
