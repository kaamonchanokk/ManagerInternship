package demo.backend.ManageInternship.repository;

import demo.backend.ManageInternship.model.entity.Admin;
import demo.backend.ManageInternship.model.bean.AdminList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Integer> {

    @Query(nativeQuery = true)
    List<AdminList> getAdminList(Integer userId, String userCode, String userName, String userLastName);
}
