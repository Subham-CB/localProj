package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class TeacherWithStudentsDto {
        private Long id;
        private String name;
        private String subject;
        private  String email;

        private List<StudentDto> students;
    }

