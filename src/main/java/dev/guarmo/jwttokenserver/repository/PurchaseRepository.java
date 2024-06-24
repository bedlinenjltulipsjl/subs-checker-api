package dev.guarmo.jwttokenserver.repository;

import dev.guarmo.jwttokenserver.model.purchase.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
}
