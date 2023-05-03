package demo.backend.ManageInternship.repository;

import demo.backend.ManageInternship.model.bean.TeacherData;
import demo.backend.ManageInternship.model.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher,Integer> {
    @Query(nativeQuery = true)
    List<TeacherData> getTeacherList(@Param("teacherCode") String teacherCode,@Param("teacherName") String teacherName,@Param("teacherLastName") String teacherLastName,@Param("departmentName") String departmentName,@Param("facultyName") String facultyName);
    @Query("SELECT COUNT(t) FROM Teacher t inner join Department d on t.depId = d.depId WHERE d.depCode = :departmentCode ")
    Integer countDepartment(@Param("departmentCode") String departmentCode);
    @Query("SELECT t FROM Teacher t  WHERE t.teacherCode= :advisorCode ")
    Teacher findByCode(@Param("advisorCode")  String advisorCode);
}
