package demo.backend.ManageInternship.repository;

import demo.backend.ManageInternship.model.entity.Year;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface YearRepository extends JpaRepository<Year,Integer> {

    @Query("SELECT y FROM Year y  WHERE y.yearNumber= :studentYear ")
    Year findYearByYear(@Param("studentYear") Integer studentYear);
}
