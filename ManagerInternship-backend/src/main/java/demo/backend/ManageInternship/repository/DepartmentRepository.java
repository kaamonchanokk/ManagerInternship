package demo.backend.ManageInternship.repository;

import demo.backend.ManageInternship.model.bean.DepartmentData;
import demo.backend.ManageInternship.model.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Integer> {
    @Query(nativeQuery = true)
    List<DepartmentData> getDepartmentList(String depCode, String depName, String facultyName);

    @Query("SELECT COUNT(d) FROM Department d INNER JOIN Faculty f on d.facultyId = f.facultyId WHERE f.facultyCode = :facultyCode")
    Integer countDepByFaculty(@Param("facultyCode") String facultyCode);

    @Query("SELECT d FROM Department d WHERE d.depCode = :departmentCode")
    Department getDepartmentByCode(String departmentCode);
}
