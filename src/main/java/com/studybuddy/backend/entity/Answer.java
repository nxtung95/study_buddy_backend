package com.studybuddy.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "answer")
@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

//    @ManyToOne
//    @JoinColumn(name = "student_id")
//    private Student student;
//
//    @ManyToOne
//    @JoinColumn(name = "question_id")
//    private Question question;
}
