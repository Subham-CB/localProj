package com.example.demo.service.impl;

import com.example.demo.dto.AddStudentRequestDto;
import com.example.demo.dto.StudentDto;
import com.example.demo.dto.StudentTeacherDto;
import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepo;
import com.example.demo.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepo studentRepo;
    private final ModelMapper modelMapper;

    @Override
    public List<StudentDto> getAllStudents(){
        List<Student> students = studentRepo.findAll();
        List<StudentDto> studentDtoList = students.stream().map(student-> new StudentDto(student.getId(),student.getName(),student.getEmail())).toList();
        return studentDtoList;
    }


    public List<StudentTeacherDto> getAllStudentsTeacher(){
        List<Student> students =studentRepo.findAll();
        List<StudentTeacherDto> studentTeacherDtos = students.stream().map(student -> {
                                                     StudentTeacherDto studentTeacherDto=modelMapper.map(student, StudentTeacherDto.class);
                                                     if(student.getTeachers()!=null){
                                                         studentTeacherDto.setTeacherName(student.getTeachers().getName());
                                                     } else {
                                                         studentTeacherDto.setTeacherName("no teacher name found");
                                                     }
                                                     return studentTeacherDto;}).toList();
        return studentTeacherDtos;
    }

    @Override
    public  StudentDto getStudentsById(Long Id){
        Student students = studentRepo.findById(Id).orElseThrow(()-> new IllegalArgumentException("Student not found by Id"));
        StudentDto studentDto =  modelMapper.map(students, StudentDto.class);

        return studentDto;
    }

    @Override
    public StudentDto createNewStudent(AddStudentRequestDto addStudentRequestDto) {
        Student newStudent = modelMapper.map(addStudentRequestDto,Student.class);
        Student student = studentRepo.save(newStudent);
        return modelMapper.map(student,StudentDto.class);
    }

    @Override
    public void deleteStudentbyId(Long id) {
        if (studentRepo.existsById(id))
            studentRepo.deleteById(id);
        else
            throw new IllegalArgumentException("Student does not exist");
    }

    @Override
    public StudentDto updateStudentbyId(Long id, AddStudentRequestDto addStudentRequestDto) {
            Student student = studentRepo.findById(id).orElseThrow(()->new IllegalArgumentException("Student not found "));
            modelMapper.map(addStudentRequestDto,student);
            student = studentRepo.save(student);
            return modelMapper.map(student,StudentDto.class);
    }

    @Override
    public StudentDto updateStudentPartialbyId(Long id, Map<String, Object> updates) {
        Student student = studentRepo.findById(id).orElseThrow(()->new IllegalArgumentException("Student not found "));
        updates.forEach((field,value)->{
            switch (field) {
                case "name":
                    student.setName((String) value);
                    break;
                case "email":
                    student.setEmail((String) value);
                    break;
                default:
                    throw new IllegalArgumentException("field is not supported");
            }
        });
        Student savedStudent = studentRepo.save(student);
        return modelMapper.map(savedStudent,StudentDto.class);
    }
}
