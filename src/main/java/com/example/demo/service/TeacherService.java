package com.example.demo.service;

import com.example.demo.dto.AddTeacherRequestDto;
import com.example.demo.dto.StudentDto;
import com.example.demo.dto.TeacherDto;
import com.example.demo.dto.TeacherWithStudentsDto;

import java.util.List;
import java.util.Map;

public interface TeacherService {

    TeacherDto getTeachersbyEmail(String email);

    List<TeacherDto> getAllTeachers();

    TeacherWithStudentsDto getAllStudentsbyEmail(String email);

    TeacherDto createNewteacher(AddTeacherRequestDto addTeacherRequestDto);

    void deleteTeacherbyId(Long id);

    TeacherDto updateTeacher(Long id, AddTeacherRequestDto addTeacherRequestDto);

    TeacherDto updateTeacherPartial(Long id, Map<String, Object> updates);
}
