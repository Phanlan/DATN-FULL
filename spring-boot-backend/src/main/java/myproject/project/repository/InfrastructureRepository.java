package myproject.project.repository;

import myproject.project.entity.Infrastructure;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InfrastructureRepository extends JpaRepository<Infrastructure, Long> {
    List<Infrastructure> findAllByIsDeleted(boolean isDeleted);

}
