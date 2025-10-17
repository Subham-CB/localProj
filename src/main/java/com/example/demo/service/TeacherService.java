package com.example.demo.service;

import com.example.demo.dto.StudentDto;
import com.example.demo.dto.TeacherDto;
import com.example.demo.dto.TeacherWithStudentsDto;

import java.util.List;

public interface TeacherService {

    TeacherDto getTeachersbyId(Long id);

    List<TeacherDto> getAllTeachers();

    TeacherWithStudentsDto getAllStudentbyTeacherId(Long id);
}
