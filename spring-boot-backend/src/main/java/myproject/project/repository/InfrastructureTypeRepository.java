package myproject.project.repository;

import myproject.project.entity.InfrastructureType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InfrastructureTypeRepository extends JpaRepository<InfrastructureType, Long> {
    List<InfrastructureType> findAllByIsDeleted(boolean isDeleted);
}
