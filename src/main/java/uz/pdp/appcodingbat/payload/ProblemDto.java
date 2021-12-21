package uz.pdp.appcodingbat.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ProblemDto {
    @NotNull(message = "section must not be null")
    private Integer sectionId;

    @NotBlank(message = "name must not be null and empty")
    private String name;

    @NotBlank(message = "description must not be null")
    private String description;

    @NotBlank(message = "template code must not be blank")
    private String templateCode;
}
