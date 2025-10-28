package com.example.demo.controller;


import com.example.demo.dto.AddTeacherRequestDto;
import com.example.demo.dto.StudentDto;
import com.example.demo.dto.TeacherDto;
import com.example.demo.dto.TeacherWithStudentsDto;
import com.example.demo.service.TeacherService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Tag(name = "Teacher API")
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

    @PostMapping
    public ResponseEntity<TeacherDto> createNewTeacher(@RequestBody @Valid AddTeacherRequestDto addTeacherRequestDto){

        return ResponseEntity.status(HttpStatus.CREATED).body(teacherService.createNewteacher(addTeacherRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Long id){
        teacherService.deleteTeacherbyId(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeacherDto> updateTeacher(@PathVariable Long id,@RequestBody @Valid AddTeacherRequestDto addTeacherRequestDto){
        return ResponseEntity.status(HttpStatus.OK).body(teacherService.updateTeacher(id,addTeacherRequestDto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TeacherDto> updateTeacherPartial(@PathVariable Long id, @RequestBody Map<String,Object> updates){
        return ResponseEntity.ok(teacherService.updateTeacherPartial(id,updates));
    }

}
