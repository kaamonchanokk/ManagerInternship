package demo.backend.ManageInternship.repository;

import demo.backend.ManageInternship.model.bean.CompanyData;
import demo.backend.ManageInternship.model.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company,Integer> {
    @Query(nativeQuery = true)
    List<CompanyData> getCompanyList(@Param("companyName") String companyName);
}
