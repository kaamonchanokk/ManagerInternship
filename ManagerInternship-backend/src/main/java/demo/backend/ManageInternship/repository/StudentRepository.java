package demo.backend.ManageInternship.repository;

import demo.backend.ManageInternship.model.bean.StudentData;
import demo.backend.ManageInternship.model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
    @Query(nativeQuery = true)
    List<StudentData> getStudentList(
            @Param("studentCode") String studentCode
            ,@Param("studentName") String studentName
            ,@Param("studentLastName") String studentLastName
            ,@Param("departmentName") String departmentName
            ,@Param("facultyName") String facultyName);
}
