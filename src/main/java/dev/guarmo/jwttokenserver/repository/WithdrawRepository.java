package dev.guarmo.jwttokenserver.repository;

import dev.guarmo.jwttokenserver.model.withdraw.MoneyWithdraw;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WithdrawRepository extends JpaRepository<MoneyWithdraw, Long> {
}
