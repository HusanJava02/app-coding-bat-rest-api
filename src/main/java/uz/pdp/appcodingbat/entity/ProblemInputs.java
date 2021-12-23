package uz.pdp.appcodingbat.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
public class ProblemInputs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "varchar[]")
    @Type(type = "uz.pdp.customtypes.GenericStringArrayType")
    private Object[] input;

    @Column(nullable = false)
    private String output;

    @ManyToOne(optional = false)
    private Problem problem;
}
