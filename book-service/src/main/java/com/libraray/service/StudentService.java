package com.libraray.service;



import com.libraray.dto.BookDTO;
import com.libraray.dto.StudentDTO;
import com.libraray.entity.StudentEntity;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    StudentDTO addStudent(StudentDTO studentDTO);
    List<StudentDTO> getAllStudents();
    Optional<StudentDTO> getStudentById(Long id);
    StudentDTO updateStudent(Long id, StudentDTO studentDTO);

    List<StudentDTO> searchStudents(String name, String course);
    StudentDTO deleteStudent(Long id);
}
