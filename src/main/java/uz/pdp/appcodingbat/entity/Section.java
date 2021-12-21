package uz.pdp.appcodingbat.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    @NotBlank(message = "section name must not be null")
    private String name;

    @Column(columnDefinition = "TEXT")
    @NotBlank(message = "column must not be null")
    private String description;

    @ManyToOne(optional = false)
    @NotNull(message = "categoryId must not be null")
    private Category category;
}
