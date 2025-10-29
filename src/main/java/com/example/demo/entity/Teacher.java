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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "teacher_seq")
    @SequenceGenerator(
            name = "teacher_seq",
            sequenceName = "teacher_id_seq",
            allocationSize = 1
    )
    private Long id;

    private String name;
    private String subject;

    @Column(unique = true)
    private String email;

    @ManyToMany(mappedBy = "teachers")
    private List<Student> students;
}
