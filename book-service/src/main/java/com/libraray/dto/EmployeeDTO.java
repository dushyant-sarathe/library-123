package com.libraray.dto;

import lombok.Data;

@Data
public class EmployeeDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String department;
    private String position;
    private String hireDate;

    // Getters and Setters
}
