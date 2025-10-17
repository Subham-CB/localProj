package com.example.demo.entity;

import com.example.demo.dto.StudentDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "teachers")
@Getter
@Setter
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String subject;

    @OneToMany(mappedBy = "teachers")
    private List<Student> students;
}
