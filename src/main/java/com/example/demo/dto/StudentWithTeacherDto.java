package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@AllArgsConstructor
    @NoArgsConstructor
    @Data
    public class StudentWithTeacherDto {
        private  Long Id;
        private  String name;
        private String email;
        private List<String> teacherName;

    }


