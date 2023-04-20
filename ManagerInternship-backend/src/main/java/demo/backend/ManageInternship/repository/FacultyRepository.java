package demo.backend.ManageInternship.repository;

import demo.backend.ManageInternship.model.bean.FacultyData;
import demo.backend.ManageInternship.model.entity.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FacultyRepository extends JpaRepository<Faculty,Integer> {
   @Query(nativeQuery = true)
    List<FacultyData> getFacultyList(@Param("facultyCode") String facultyCode,@Param("facultyName") String facultyName);
}
