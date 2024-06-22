package dev.guarmo.jwttokenserver.repository;

import dev.guarmo.jwttokenserver.model.transaction.PayTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<PayTransaction, Long> {
}
