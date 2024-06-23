package dev.guarmo.jwttokenserver.model.purchase;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String purchaseAmount;
    private String description;
    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;
}
