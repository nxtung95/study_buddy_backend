package com.studybuddy.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "question")
@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

//    @ManyToOne
//    @JoinColumn(name = "student_subject_id")
//    private StudentSubject studentSubject;
}
