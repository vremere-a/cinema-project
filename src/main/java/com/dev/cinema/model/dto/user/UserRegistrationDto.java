package com.dev.cinema.model.dto.user;

import com.dev.cinema.validation.EmailValidation;
import com.dev.cinema.validation.FieldsValueMatch;
import lombok.Data;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@FieldsValueMatch.List({
        @FieldsValueMatch(
                field = "password",
                fieldMatch = "repeatPassword",
                message = "Passwords do not match!"
        )
})
@Data
public class UserRegistrationDto {
    @EmailValidation
    private String email;
    @NotNull
    @Size(min = 4)
    private String password;
    private String repeatPassword;
}
