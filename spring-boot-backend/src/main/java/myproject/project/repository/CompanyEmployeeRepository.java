package myproject.project.repository;

import myproject.project.entity.CompanyEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CompanyEmployeeRepository extends JpaRepository<CompanyEmployee, Long> {

    List<CompanyEmployee> findCompanyEmployeeByIsDeletedAndCompanyId(boolean isDeleted, Long company_id);

    List<CompanyEmployee> findAllByIsDeleted(boolean isDeleted);

    List<CompanyEmployee> findCompanyEmployeeByCompanyIdAndIsDeletedAndNameLike(Long company_id, boolean isDeleted, String name);

    @Query(value = "SELECT Max(b.id) FROM CompanyEmployee b")
    Optional<Integer> findLastId();
}
