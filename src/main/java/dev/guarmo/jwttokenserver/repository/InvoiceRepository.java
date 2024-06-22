package dev.guarmo.jwttokenserver.repository;

import dev.guarmo.jwttokenserver.model.invoice.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
}
