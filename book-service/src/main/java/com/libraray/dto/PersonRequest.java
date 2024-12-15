package com.libraray.dto;



import com.libraray.entity.Address;
import com.libraray.enums.Gender;
import jakarta.persistence.Embedded;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonRequest {
    private Long id;

    @Email
    private String email;
    private String firstName;
    private String lastName;
    @Size(min = 9)
    private String phone;

//    @Embedded
//    private ContactDetails contactDetails;
    @Embedded
    private Address address;
    @Enumerated(EnumType.STRING)
    private Gender gender;
}
