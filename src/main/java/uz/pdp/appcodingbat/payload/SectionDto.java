package uz.pdp.appcodingbat.payload;

import lombok.Data;
import uz.pdp.appcodingbat.entity.Category;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class SectionDto {

    @NotBlank(message = "section name must not be null")
    private String name;

    @NotBlank(message = "column must not be null")
    private String description;

    @NotNull(message = "categoryId must not be null")
    private Integer categoryId;
}
