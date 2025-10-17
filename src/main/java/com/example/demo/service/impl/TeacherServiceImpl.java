package com.example.demo.service.impl;

import com.example.demo.config.MapperConfig;
import com.example.demo.dto.StudentDto;
import com.example.demo.dto.TeacherDto;
import com.example.demo.dto.TeacherWithStudentsDto;
import com.example.demo.entity.Teacher;
import com.example.demo.repository.TeacherRepo;
import com.example.demo.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepo teacherRepo;
    private final ModelMapper modelMapper;

    @Override
    public TeacherDto getTeachersbyId(Long id){
        Teacher teacher = teacherRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Enter Correct id"));
        return modelMapper.map(teacher,TeacherDto.class);
    }

    @Override
    public List<TeacherDto> getAllTeachers() {
        List<Teacher> teachers = teacherRepo.findAll();
        return teachers.stream().map(teacher -> modelMapper.map(teacher, TeacherDto.class)).toList();
    }

    @Override
    public TeacherWithStudentsDto getAllStudentbyTeacherId(Long id) {

        Teacher teacher = teacherRepo.findById(id).orElseThrow(()->new IllegalArgumentException("No teacher Found"));
        TeacherWithStudentsDto teacherWithStudentsDto = new TeacherWithStudentsDto();
        teacherWithStudentsDto.setId(teacher.getId());
        teacherWithStudentsDto.setName(teacher.getName());
        teacherWithStudentsDto.setSubject(teacher.getSubject());

        List<StudentDto> studentDtoList = teacher.getStudents().stream().map(student -> {
                                           StudentDto studentDto = new StudentDto();
                                           studentDto.setId(student.getId());
                                           studentDto.setName(student.getName());
                                           studentDto.setEmail(student.getEmail());
                                           return  studentDto;
                                           }).toList();
        teacherWithStudentsDto.setStudents(studentDtoList);

        return teacherWithStudentsDto;
    }
}
