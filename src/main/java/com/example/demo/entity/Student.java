package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Table(name = "student")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_seq")
    @SequenceGenerator(
            name = "student_seq",
            sequenceName = "student_id_seq",
            allocationSize = 1
    )
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    @ManyToMany
   @JoinTable(name = "student_teacher",
           joinColumns = @JoinColumn(name = "student_id"),
         inverseJoinColumns = @JoinColumn(name = "teacher_id"))
  private List<Teacher> teachers;


}
