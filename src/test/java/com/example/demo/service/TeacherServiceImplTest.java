package com.example.demo.service;


import com.example.demo.dto.AddTeacherRequestDto;
import com.example.demo.dto.TeacherDto;

import com.example.demo.entity.Teacher;
import com.example.demo.repository.TeacherRepo;
import com.example.demo.service.impl.TeacherServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;
import org.assertj.core.api.Assertions.*;

public class TeacherServiceImplTest {

    @Mock
    private TeacherRepo teacherRepo;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private TeacherServiceImpl teacherService;

    @BeforeEach
    void setup(){
        org.mockito.MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllTeacherstest(){

        //Arrange
        Teacher teacher = new Teacher();
        teacher.setId(1L);
        teacher.setName("Lily");
        teacher.setSubject("Math");

        List<Teacher> teacherList = List.of(teacher);

        TeacherDto teacherDto = new TeacherDto();
        teacherDto.setName("Lily");

        when(teacherRepo.findAll()).thenReturn(teacherList);
        when(modelMapper.map(teacher,TeacherDto.class)).thenReturn(teacherDto);

        //Act
        List<TeacherDto> result = teacherService.getAllTeachers();

        //Assert
        assertEquals(1,result.size());
        assertEquals("Lily",result.get(0).getName());
        Mockito.verify(teacherRepo,times(1)).findAll();

    }

    @Test
    void createTeacherTest(){
        AddTeacherRequestDto addTeacherDto = new AddTeacherRequestDto();
        addTeacherDto.setSubject("Java");
        addTeacherDto.setName("John");

        Teacher teacher = new Teacher();
        teacher.setId(1L);
        teacher.setName("John");
        teacher.setSubject("Java");

        TeacherDto teacherDto = new TeacherDto();
        teacherDto.setName("John");

        when(modelMapper.map(addTeacherDto,Teacher.class)).thenReturn(teacher);
        when(teacherRepo.save(any(Teacher.class))).thenReturn(teacher);
        when(modelMapper.map(teacher,TeacherDto.class)).thenReturn(teacherDto);

        TeacherDto result = teacherService.createNewteacher(addTeacherDto);


        assertEquals("John",result.getName());
        Mockito.verify(teacherRepo,times(1)).save(any(Teacher.class));
    }
}
