package myproject.project.repository;

import myproject.project.entity.Service;
import myproject.project.entity.UsedService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UsedServiceRepository extends JpaRepository<UsedService, Long> {

    List<UsedService> findByIsDeletedAndCompanyId(boolean isDeleted, Long companyId);

    @Query(value = "SELECT * FROM used_service " +
            "WHERE 1 = 1 " +
            "AND MONTH(start_date) = :month " +
            "AND is_deleted = false " +
            "AND company_id = :companyId",nativeQuery = true)
    List<UsedService> findUsedServiceMonthByCompanyId(Long companyId, Long month);

    List<UsedService> findUsedServiceByIsDeleted(boolean isDeleted);

    List<UsedService> findByService(Service service);

    @Transactional
    @Modifying
    @Query(value = "UPDATE UsedService u SET u.isDeleted = true WHERE u.service = :service")
    void deleteUsedServiceByService(Service service);

    UsedService findByIsDeletedAndCompanyIdAndServiceId(Boolean isDeleted, Long companyId, Long serviceId);

//    @Query(value = "SELECT * FROM used_service u " +
//            "WHERE u.company_id = :companyId " +
//            "AND :month+1 = EXTRACT(MONTH FROM u.created_at)" +
//            "AND :year = EXTRACT(YEAR FROM u.created_at)", nativeQuery = true)
//    List<UsedService> findByCompanyIdAndDate(Long companyId, Integer month, Integer year);

    @Query(value = "SELECT m.* FROM month_used_service m INNER JOIN used_service us ON m.used_service_id = us.id\n" +
            "WHERE us.company_id = :companyId and :month+1 = EXTRACT(MONTH FROM m.from_date) AND :year = EXTRACT(YEAR FROM m.from_date)", nativeQuery = true)
    List<UsedService> findByCompanyIdAndDate(Long companyId, Integer month, Integer year);
}
