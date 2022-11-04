package myproject.project.repository;

import myproject.project.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    List<Vehicle> findAllByIsDeleted(boolean isDeleted);

    List<Vehicle> findByIsDeletedAndCompanyId(boolean isDeleted, Long companyId);
}
