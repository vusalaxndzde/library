package com.bakulibrary.library.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private int id;

    @NotBlank(message = "name cannot be blank")
    private String name;

    @NotBlank(message = "name cannot be blank")
    private String surname;

    @Email(message = "invalid email")
    private String email;

    @Min(value = 5, message = "password must be at least 5 symbol")
    private String password;

}
