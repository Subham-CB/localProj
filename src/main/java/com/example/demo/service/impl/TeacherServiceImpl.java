package com.example.demo.service.impl;

import com.example.demo.config.MapperConfig;
import com.example.demo.dto.AddTeacherRequestDto;
import com.example.demo.dto.StudentDto;
import com.example.demo.dto.TeacherDto;
import com.example.demo.dto.TeacherWithStudentsDto;
import com.example.demo.entity.Student;
import com.example.demo.entity.Teacher;
import com.example.demo.repository.TeacherRepo;
import com.example.demo.service.TeacherService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.security.Key;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepo teacherRepo;
    private final ModelMapper modelMapper;

    @Override
    public TeacherDto getTeachersbyEmail(String email){
        Teacher teacher = teacherRepo.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("Enter Correct id"));
        return modelMapper.map(teacher,TeacherDto.class);
    }

    @Override
    public List<TeacherDto> getAllTeachers() {
        List<Teacher> teachers = teacherRepo.findAll();
        return teachers.stream().map(teacher -> modelMapper.map(teacher, TeacherDto.class)).toList();
    }

    @Override
    public TeacherWithStudentsDto getAllStudentsbyEmail(String email) {

        Teacher teacher = teacherRepo.findByEmail(email).orElseThrow(()->new IllegalArgumentException("No teacher Found"));
        TeacherWithStudentsDto teacherWithStudentsDto = new TeacherWithStudentsDto();
        teacherWithStudentsDto.setId(teacher.getId());
        teacherWithStudentsDto.setName(teacher.getName());
        teacherWithStudentsDto.setSubject(teacher.getSubject());
        teacherWithStudentsDto.setEmail(teacher.getEmail());

        List<StudentDto> studentDtoList = teacher.getStudents().stream().map(student -> {
                                           StudentDto studentDto = new StudentDto();
                                           studentDto.setId(student.getId());
                                           studentDto.setName(student.getName());
                                           studentDto.setEmail(student.getEmail());
                                           return  studentDto;
                                           }).toList();
        teacherWithStudentsDto.setStudents(studentDtoList);

        return teacherWithStudentsDto;
    }

    @Override
    public TeacherDto createNewteacher(AddTeacherRequestDto addTeacherRequestDto) {
        Teacher teacher = modelMapper.map(addTeacherRequestDto,Teacher.class);
        teacherRepo.save(teacher);
        return modelMapper.map(teacher,TeacherDto.class);
    }

    @Override
    public void deleteTeacherbyId(Long id) {
        if (teacherRepo.existsById(id)){
            teacherRepo.deleteById(id);
        }
        else
            throw new IllegalArgumentException("No Teacher found");
    }

    @Override
    public TeacherDto updateTeacher(Long id, AddTeacherRequestDto addTeacherRequestDto) {
        Teacher teacher = teacherRepo.findById(id).orElseThrow(()->new IllegalArgumentException("Teacher not Found"));
        modelMapper.map(addTeacherRequestDto,teacher);
        teacher = teacherRepo.save(teacher);
        return modelMapper.map(teacher,TeacherDto.class);
    }

    @Override
    public TeacherDto updateTeacherPartial(Long id, Map<String, Object> updates) {
        Teacher teacher =  teacherRepo.findById(id).orElseThrow(()->new IllegalArgumentException("ID not found"));
        updates.forEach((field, value)-> {
            switch (field) {
                case "name":
                    teacher.setName((String) value);
                    break;
                case "subject":
                    teacher.setSubject((String) value);
                    break;
                case "email":
                    teacher.setEmail((String) value);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid input");
            }
        });
        teacherRepo.save(teacher);

        return modelMapper.map(teacher,TeacherDto.class);
    }
}
