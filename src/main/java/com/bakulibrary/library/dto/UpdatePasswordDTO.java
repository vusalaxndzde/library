package com.bakulibrary.library.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePasswordDTO {

    private String currentPassword;

    @Size(min = 5, message = "Password must be at least 5 symbol!")
    private String newPassword;

    private String confirmNewPassword;

}
