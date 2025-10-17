package com.example.demo.controller;


import com.example.demo.dto.TeacherDto;
import com.example.demo.dto.TeacherWithStudentsDto;
import com.example.demo.service.TeacherService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/teacher")
@RestController
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @GetMapping
    public ResponseEntity<List<TeacherDto>> getAllTeacher() {
        return ResponseEntity.ok(teacherService.getAllTeachers());

    }

    @GetMapping("/alls/{id}")
    public ResponseEntity<TeacherWithStudentsDto> getAllStudentsbyTeacherId(@PathVariable Long id){
        return ResponseEntity.ok(teacherService.getAllStudentbyTeacherId(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherDto> getTeacherbyId(@PathVariable Long id){
    return ResponseEntity.ok(teacherService.getTeachersbyId(id));
}

}
