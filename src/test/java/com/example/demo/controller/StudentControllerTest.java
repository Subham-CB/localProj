package com.example.demo.controller;


import com.example.demo.dto.StudentDto;


import com.example.demo.service.StudentService;
import com.example.demo.service.impl.StudentServiceImpl;
import com.example.demo.service.impl.TeacherServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;

import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(StudentController.class)
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private StudentService studentService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllStudent() throws Exception{
        List<StudentDto> studentList = Arrays.asList(new StudentDto(1L,"John","ad@gmail.com"),new StudentDto(2L,"Jane","as@gmail.com"));

        Mockito.when(studentService.getAllStudents()).thenReturn(studentList);

        mockMvc.perform(get("/student"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].name").value("John"))
                .andExpect(jsonPath("$[1].name").value("Jane"));
    }
}
