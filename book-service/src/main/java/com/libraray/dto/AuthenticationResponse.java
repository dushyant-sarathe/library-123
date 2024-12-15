package com.libraray.dto;


import com.libraray.entity.Address;
import com.libraray.enums.Gender;
import com.libraray.enums.Role;
import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String username;
    private String phone;
    @Embedded
    private Gender gender;
    private String password;

//    @Embedded
//    private ContactDetails contactDetails;
    @Embedded
    private Role role;
    @Embedded
    private Address address;
    private String token;

}
