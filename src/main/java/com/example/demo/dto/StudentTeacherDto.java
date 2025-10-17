package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public class StudentTeacherDto {
        private  Long Id;
        private  String name;
        private String email;
        private String teacherName;

    }


