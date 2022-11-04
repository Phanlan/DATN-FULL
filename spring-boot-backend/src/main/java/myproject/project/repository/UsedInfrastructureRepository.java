package myproject.project.repository;

import myproject.project.entity.UsedInfrastructure;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsedInfrastructureRepository extends JpaRepository<UsedInfrastructure, Long> {
    List<UsedInfrastructure> findByIsDeletedAndCompanyId(boolean isDeleted, Long companyId);
    List<UsedInfrastructure> findByIsDeletedAndInfrastructureId(boolean isDeleted, Long infrastructureId);
    UsedInfrastructure findByIsDeletedAndCompanyIdAndInfrastructureId(Boolean isDeleted, Long companyId, Long infrastructureId);
}
