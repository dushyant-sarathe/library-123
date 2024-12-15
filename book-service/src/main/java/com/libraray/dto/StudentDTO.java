package com.libraray.dto;

import lombok.Data;

@Data
public class StudentDTO {
    private Long id;

    private String name;
    private String email;
    private String course;
    private String phoneNumber;
    private String enrollmentDate;
    private String dateOfBirth;
}
