package com.studybuddy.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "student")
@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String role;

//    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
//    private List<Subject> subjects;
}
