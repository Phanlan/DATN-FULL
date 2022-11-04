package myproject.project.repository;

import myproject.project.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    List<Invoice> findAllByIsDeleted(boolean isDeleted);
}
