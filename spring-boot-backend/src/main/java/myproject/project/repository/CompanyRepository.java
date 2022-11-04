package myproject.project.repository;

import myproject.project.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface CompanyRepository extends JpaRepository<Company,Long> {

    @Transactional
    @Modifying
    @Query(value = "update Company company set " +
            "company.name = :name, " +
            "company.area = :area, " +
            "company.authorizedCapital = :authorizedCapital, " +
            "company.fieldOfActivity = :fieldOfActivity, " +
            "company.floor = :floor, " +
            "company.hotline = :hotline, " +
            "company.taxCode = :taxCode " +
            "where  company.id = :id"
    )
    void updateById(Long id, String taxCode, Float authorizedCapital, String fieldOfActivity, String floor, String hotline, String name, Float area);

    List<Company> findAllByIsDeleted(boolean isDeleted);

    List<Company> findCompanyByIsDeletedAndNameLike(boolean isDeleted, String name);

    @Query(value = "SELECT * FROM company c " +
            "WHERE c.id = :companyId ", nativeQuery = true)
    List<Company> findByCompanyId(Long companyId);


}
