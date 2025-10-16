package com.example.demo.dto;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
public class AddStudentRequestDto {

        @Size(min = 3 ,max = 30,message = "Enter input 3-30 chars")
        @NotBlank(message = "name is required")
        private  String name;

        @Email
        @NotBlank(message = "Email is required")
        private String email;

}
