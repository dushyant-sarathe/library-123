package com.libraray.dto;

import com.libraray.utils.ValidPassword;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePasswordRequest {

    @NotNull @ValidPassword
    private String newPassword;

    @NotNull
    private String currentPassword;

}
