package com.example.demo.service;

import com.example.demo.dto.StudentDto;
import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepo;
import com.example.demo.service.impl.StudentServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.Assertions.*;



import static org.mockito.Mockito.*;

public class StudentServiceImpltest {

    @Mock
    StudentRepo studentRepo;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    StudentServiceImpl studentService;

    @BeforeEach
    void setUp(){
        org.mockito.MockitoAnnotations.openMocks(this);
    }



    @Test
    void getAllStudents(){

        //Arrange
        Student student = new Student();
        student.setId(1L);
        student.setName("John");

        List<Student> studentList = List.of(student);

        StudentDto studentDto = new StudentDto();
        studentDto.setName("John");


        when(studentRepo.findAll()).thenReturn(studentList);
        when(modelMapper.map(student,StudentDto.class)).thenReturn(studentDto);

        //Act
        List<StudentDto> result = studentService.getAllStudents();

        //Assert
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getName()).isEqualTo("John");
        verify(studentRepo,times(1)).findAll();
    }

}
