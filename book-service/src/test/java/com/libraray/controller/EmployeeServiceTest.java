package com.libraray.controller;

import com.libraray.dto.EmployeeDTO;
import com.libraray.entity.EmployeeEntity;
import com.libraray.repository.EmployeeRepository;
import com.libraray.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private ModelMapper modelMapper;

    @Test
    public void testAddEmployee() {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        // Populate employeeDTO with test data
        EmployeeEntity employeeEntity = new EmployeeEntity();
        // Populate employeeEntity with corresponding test data

        when(modelMapper.map(employeeDTO, EmployeeEntity.class)).thenReturn(employeeEntity);
        when(employeeRepository.save(employeeEntity)).thenReturn(employeeEntity);
        when(modelMapper.map(employeeEntity, EmployeeDTO.class)).thenReturn(employeeDTO);

        EmployeeDTO result = employeeService.addEmployee(employeeDTO);

        assertEquals(employeeDTO, result);
    }

    // Additional test cases for other methods
}

