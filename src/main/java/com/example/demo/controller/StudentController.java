package com.example.demo.controller;

import com.example.demo.dto.AddStudentRequestDto;
import com.example.demo.dto.StudentDto;
import com.example.demo.dto.StudentWithTeacherDto;
import com.example.demo.service.StudentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
@Tag(name = "Student API")
public class StudentController {

    private final StudentService studentServ;


    @GetMapping
    public ResponseEntity<List<StudentDto>> getAllStudent(){
 //       return ResponseEntity.status(HttpStatus.OK).body(studentServ.getAllStudents());
        return  ResponseEntity.ok(studentServ.getAllStudents());
    }

    @GetMapping("/wt")
    public ResponseEntity<List<StudentWithTeacherDto>> getAllStudentTeacher(){
        return  ResponseEntity.ok(studentServ.getAllStudentsTeacher());
    }

    @GetMapping("/{id}")
    public StudentDto getStudentbyEmail(@PathVariable String email){
        return studentServ.getStudentsByEmail(email);
    }


    @PostMapping
    public ResponseEntity<StudentDto> createNewStudent(@RequestBody  AddStudentRequestDto addStudentRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(studentServ.createNewStudent(addStudentRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudentbyId(@PathVariable Long id){
        studentServ.deleteStudentbyId(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDto> updateStudentbyId(@PathVariable Long id, @RequestBody @Valid AddStudentRequestDto addStudentRequestDto){
        return ResponseEntity.ok(studentServ.updateStudentbyId(id,addStudentRequestDto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<StudentDto> updateStudentPartialbyId(@PathVariable Long id, @RequestBody Map<String,Object> updates){
        return ResponseEntity.ok(studentServ.updateStudentPartialbyId(id,updates));
    }

    @PatchMapping("/{studentId}/st/{teacherId}")
    public ResponseEntity<Void> assignTeachertoStudent(@PathVariable Long studentId, Long teacherId){
        studentServ.assignTeachertoStudent(studentId,teacherId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{studentId}/del_st/{teacherId}")
    public ResponseEntity<Void> removeTeacherFromStudent(@PathVariable Long studentId, Long teacherId){
        studentServ.removeTeacherFromStudent(studentId,teacherId);
        return ResponseEntity.noContent().build();
    }




}
