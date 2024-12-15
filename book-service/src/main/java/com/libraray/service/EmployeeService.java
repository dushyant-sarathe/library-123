package com.libraray.service;


import com.libraray.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {
    EmployeeDTO addEmployee(EmployeeDTO employeeDTO);
    EmployeeDTO updateEmployee(Long employeeId, EmployeeDTO employeeDTO);
    EmployeeDTO getEmployeeById(Long employeeId);
    List<EmployeeDTO> getAllEmployees();
    void deleteEmployee(Long employeeId);
    List<EmployeeDTO> getEmployeesByDepartment(String department);
}

