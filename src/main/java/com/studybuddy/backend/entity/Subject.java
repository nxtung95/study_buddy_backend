package com.studybuddy.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "subject")
@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;

    // Define the relationship with the Student entity
//    @ManyToOne
//    @JoinColumn(name = "student_id")
//    private Student student;
}
