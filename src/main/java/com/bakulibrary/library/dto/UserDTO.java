package com.bakulibrary.library.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    @NotBlank(message = "Name cannot be blank!")
    private String name;

    @NotBlank(message = "Surname cannot be blank!")
    private String surname;

    @NotBlank(message = "Email cannot be blank!")
    @Email(message = "Invalid email!")
    private String email;

    @Size(min = 5, message = "Password must be at least 5 symbol!")
    private String password;

}
