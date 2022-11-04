package myproject.project.repository;

import myproject.project.entity.UsedElectricWater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsedElectricWaterRepository extends JpaRepository<UsedElectricWater, Long> {
    List<UsedElectricWater> findAllByIsDeleted(boolean isDeleted);
    List<UsedElectricWater> findUsedElectricWaterByIsDeletedAndCompanyId(boolean isDeleted, Long company_id);

    @Query(value = "SELECT * FROM used_electric_water u " +
            "WHERE u.company_id = :companyId " +
            "AND :month = EXTRACT(MONTH FROM u.month)" +
            "AND :year = EXTRACT(YEAR FROM u.month)", nativeQuery = true)
    List<UsedElectricWater> findByCompanyIdAndDate(Long companyId, Integer month, Integer year);
}
