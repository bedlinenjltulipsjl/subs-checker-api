package dev.guarmo.jwttokenserver.repository;

import dev.guarmo.jwttokenserver.model.deposit.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepositRepository extends JpaRepository<Deposit, Long> {
}
