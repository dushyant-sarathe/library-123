package com.libraray.service.impl;

import com.libraray.dto.StudentDTO;
import com.libraray.entity.StudentEntity;
import com.libraray.exception.ResourceNotFoundException;
import com.libraray.repository.StudentRepository;
import com.libraray.service.StudentService;



import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public StudentDTO addStudent(StudentDTO studentDTO) {
        StudentEntity studentEntity = modelMapper.map(studentDTO, StudentEntity.class);
        StudentEntity savedStudent = studentRepository.save(studentEntity);
        return modelMapper.map(savedStudent, StudentDTO.class);
    }

    @Override
    public Optional<StudentDTO> getStudentById(Long studentId) {
        StudentEntity studentEntity = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + studentId));

        StudentDTO studentDTO = modelMapper.map(studentEntity, StudentDTO.class);
        return Optional.of(studentDTO);
    }


    @Override
    public StudentDTO updateStudent(Long id, StudentDTO studentDTO) {
        StudentEntity existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));

        // Check and update each field if present in the DTO
        if (studentDTO.getName() != null) {
            existingStudent.setName(studentDTO.getName());
        }
        if (studentDTO.getEmail() != null) {
            existingStudent.setEmail(studentDTO.getEmail());
        }
        if (studentDTO.getEnrollmentDate() != null) {
            existingStudent.setEnrollmentDate(studentDTO.getEnrollmentDate());
        }
        if (studentDTO.getCourse() != null) {
            existingStudent.setCourse(studentDTO.getCourse());
        }

        StudentEntity updatedStudent = studentRepository.save(existingStudent);
        return modelMapper.map(updatedStudent, StudentDTO.class);
    }

    @Override
    public StudentDTO deleteStudent(Long studentId) {
        StudentEntity studentEntity = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + studentId));

        studentRepository.delete(studentEntity);
        return modelMapper.map(studentEntity, StudentDTO.class);
    }

    @Override
    public List<StudentDTO> getAllStudents() {
        List<StudentEntity> students = studentRepository.findAll();
        return students.stream()
                .map(student -> modelMapper.map(student, StudentDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentDTO> searchStudents(String name, String course) {
        List<StudentEntity> students = studentRepository.findByNameAndCourse(name, course);

        if (students.isEmpty()) {
            throw new ResourceNotFoundException("No students found matching the search criteria.");
        }

        return students.stream()
                .map(student -> modelMapper.map(student, StudentDTO.class))
                .collect(Collectors.toList());
    }
}
