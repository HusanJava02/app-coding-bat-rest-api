package uz.pdp.appcodingbat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appcodingbat.entity.Problem;
import uz.pdp.appcodingbat.entity.Section;

import java.util.List;

public interface SectionRepository extends JpaRepository<Section,Integer> {
}
