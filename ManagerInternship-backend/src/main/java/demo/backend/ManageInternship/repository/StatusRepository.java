package demo.backend.ManageInternship.repository;

import demo.backend.ManageInternship.model.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<Status,Integer> {
    @Query("SELECT s FROM Status s WHERE s.statusCode = :code AND s.statusType = 'STATUS_INFO' ")
    Status findByStatusCode(@Param("code") String code);

    @Query("SELECT s FROM Status s WHERE s.statusCode = :code AND s.statusType = 'TEACHER_STATUS' ")
    Status findByStatusTeacherCode(@Param("code") String code);

    @Query("SELECT s FROM Status s WHERE s.statusCode = :code AND s.statusType = 'STUDENT_STATUS' ")
    Status findByStatusStudentCode(@Param("code") String code);
}
