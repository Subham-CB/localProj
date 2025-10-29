package com.example.demo.service;

import com.example.demo.dto.AddStudentRequestDto;
import com.example.demo.dto.StudentDto;
import com.example.demo.dto.StudentWithTeacherDto;
import jakarta.persistence.Id;

import java.util.List;
import java.util.Map;

public interface StudentService {

    List<StudentDto> getAllStudents();
    StudentDto getStudentsByEmail(String email);
    StudentDto createNewStudent(AddStudentRequestDto addStudentRequestDto);

    void deleteStudentbyId(Long id);

    StudentDto updateStudentbyId(Long id, AddStudentRequestDto addStudentRequestDto);

    StudentDto updateStudentPartialbyId(Long id, Map<String, Object> updates);

    List<StudentWithTeacherDto> getAllStudentsTeacher();

    void assignTeachertoStudent(Long studentId, Long teacherId);

    void removeTeacherFromStudent(Long studentId, Long teacherId);
}
