package com.example.demo.controller;
import  java.util.*;

import com.example.demo.dto.AddTeacherRequestDto;

import com.example.demo.dto.TeacherDto;
import com.example.demo.service.TeacherService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TeacherController.class)
public class TeacherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TeacherService teacherService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void getAllTeacherTest() throws Exception{
        List<TeacherDto> teacherDtoList = Arrays.asList(new TeacherDto(1L,"Emily","Java"),new TeacherDto(2L,"MnM","Python"));
        Mockito.when(teacherService.getAllTeachers()).thenReturn(teacherDtoList);

        mockMvc.perform(get("/teacher"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].name").value("Emily"))
                .andExpect(jsonPath("$[1].name").value("MnM"));

    }

            @Test
            void createTeachers() throws Exception{
                AddTeacherRequestDto teacherDto = new AddTeacherRequestDto("JAVA","John");

                TeacherDto saved = new TeacherDto();
                saved.setId(1L);
                saved.setName("John");
                saved.setSubject("JAVA");

                Mockito.when(teacherService.createNewteacher(any(AddTeacherRequestDto.class))).thenReturn(saved);

                mockMvc.perform(post("/teacher")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(teacherDto)))
                        .andExpect(status().isCreated())
                        .andExpect(jsonPath("$.id").value(1))
                        .andExpect(jsonPath("$.name").value("John"))
                        .andExpect(jsonPath("$.subject").value("JAVA"));
            }
}
