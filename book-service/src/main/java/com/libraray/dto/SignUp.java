package com.libraray.dto;



import com.libraray.entity.Address;
import com.libraray.enums.Gender;
import com.libraray.utils.ValidPassword;
import jakarta.persistence.Embedded;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class SignUp {


    @NotNull @NotEmpty @NotBlank
    private String firstName;
    @NotNull @NotEmpty @NotBlank
    private String lastName;
    @NotNull @NotEmpty @NotBlank
    private String username;

    private String phone;
    @NotNull @NotEmpty @NotBlank @Email
    private String email;

    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Embedded
    private Address address;

//    @Embedded
//    private ContactDetails contactDetails;

    @NotNull @ValidPassword
    private String password;
    @Pattern(regexp = "^ROLE_[A-Z_]+$")
    private String role;
}
