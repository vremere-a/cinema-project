package com.dev.cinema.model.dto.user;

import com.dev.cinema.validation.EmailValidation;
import com.dev.cinema.validation.FieldsValueMatch;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@FieldsValueMatch(
        field = "password",
        fieldMatch = "repeatPassword",
        message = "Passwords do not match!"
)
@Data
public class UserRegistrationDto {
    @EmailValidation
    private String email;
    @NotNull(message = "password can't be null")
    @Size(min = 8, message = "password should have at least 8 characters")
    private String password;
    @NotNull(message = "repeat password can't be null")
    @Size(min = 8, message = "password should have at least 8 characters")
    private String repeatPassword;
}
