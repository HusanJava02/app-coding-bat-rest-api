package uz.pdp.appcodingbat.entity;

import com.sun.xml.internal.ws.addressing.ProblemAction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "email must not be empty")
    @Column(nullable = false,unique = true)
    private String email;

    @Size(min = 8)
    @Column(nullable = false)
    private String password;

    @ManyToMany
    private List<Problem> solvedProblems;
}
