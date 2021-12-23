package uz.pdp.appcodingbat.payload;

import lombok.Data;
import org.hibernate.annotations.Type;
import uz.pdp.appcodingbat.entity.Problem;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.List;

@Data
public class ProblemInputsDto {
    private Object[] input;

    private String output;

    private Integer problemId;
}
