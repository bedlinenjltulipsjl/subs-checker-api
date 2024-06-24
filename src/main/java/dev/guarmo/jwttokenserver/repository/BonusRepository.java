package dev.guarmo.jwttokenserver.repository;

import dev.guarmo.jwttokenserver.model.bonus.MoneyBonus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BonusRepository extends JpaRepository<MoneyBonus, Long> {
}
