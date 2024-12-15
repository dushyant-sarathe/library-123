package com.libraray.entity;

import com.libraray.enums.Gender;
import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"email"})
})
public abstract class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String phone;

//    @Embedded
//    private ContactDetails contactDetails;

    @Column(unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Embedded
   private Address address;



   // @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
   // @Transient
   // private List<Reservation> reservations;

}

