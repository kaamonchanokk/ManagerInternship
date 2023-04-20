package demo.backend.ManageInternship.repository;

import demo.backend.ManageInternship.model.bean.PositionData;
import demo.backend.ManageInternship.model.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PositionRepository extends JpaRepository<Position,Integer> {
    @Query("SELECT p FROM Position p WHERE p.posCode = :positionCode")
    Position findByPositionCode(@Param("positionCode") String positionCode);
    @Query(nativeQuery = true)
    List<PositionData> getPositionList(@Param("positionCode") String positionCode,@Param("positionName") String positionName);
}
