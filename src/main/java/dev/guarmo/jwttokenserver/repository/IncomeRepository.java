package dev.guarmo.jwttokenserver.repository;

import dev.guarmo.jwttokenserver.model.income.Income;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncomeRepository extends JpaRepository<Income, Long> {
}
