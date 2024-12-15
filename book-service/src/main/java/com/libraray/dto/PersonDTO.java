package com.libraray.dto;



import com.libraray.entity.Address;
import com.libraray.enums.Gender;
import jakarta.persistence.Embedded;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {
    private Long id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;

//    @Embedded
//    private ContactDetails contactDetails;

    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Embedded
    private Address address;
}
