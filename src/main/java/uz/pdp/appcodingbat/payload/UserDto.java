package uz.pdp.appcodingbat.payload;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class UserDto {
    @NotBlank(message = "email must not be empty")
    private String email;

    @NotBlank(message = "password must not be empty")
    @Length(min = 8)
    private String password;

}
