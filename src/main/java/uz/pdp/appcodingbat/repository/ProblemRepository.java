package uz.pdp.appcodingbat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appcodingbat.entity.Problem;

import java.util.List;

public interface ProblemRepository extends JpaRepository<Problem,Integer> {
    List<Problem> findAllBySectionId(Integer section_id);
}
