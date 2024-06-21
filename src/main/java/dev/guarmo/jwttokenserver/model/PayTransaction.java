package dev.guarmo.jwttokenserver.model;

import dev.guarmo.jwttokenserver.model.user.UserContent;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class PayTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;
}
