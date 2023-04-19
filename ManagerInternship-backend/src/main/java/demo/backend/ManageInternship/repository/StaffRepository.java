package demo.backend.ManageInternship.repository;

import demo.backend.ManageInternship.model.bean.StaffList;
import demo.backend.ManageInternship.model.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffRepository extends JpaRepository<Staff,Integer> {

    @Query(nativeQuery = true)
    List<StaffList> getStaffList(Integer staffId, String staffCode, String staffName, String staffLastName,String staffPosition);

    @Query("SELECT COUNT(s) FROM Staff s INNER JOIN Position p on s.posId = p.posId WHERE p.posCode = :positionCode")
    Integer countStaffPosition(@Param("positionCode") String positionCode);
}
