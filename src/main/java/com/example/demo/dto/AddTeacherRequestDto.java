package com.example.demo.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddTeacherRequestDto {

    @NotNull(message = "Subject cannot be null")
    private String subject;
    @NotNull(message = "Name cannot be null")
    private String name;


}
