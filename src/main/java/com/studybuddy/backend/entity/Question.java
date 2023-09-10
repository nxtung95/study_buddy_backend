package com.studybuddy.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.sql.Timestamp;

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

    @Column(name = "subject_id")
    private int subjectId;

    @Column(name = "tutor_id")
    private int tutorId;

    private String title;

    private int status;

    @Column(name = "input_detail")
    private String inputDetail;

    @Column(name = "image_detail_url")
    private String imageDetailUrl;

    @Column(name = "created_date")
    private Timestamp createdDate = new Timestamp(System.currentTimeMillis());

    @Column(name = "updated_date")
    private Timestamp updatedDate = new Timestamp(System.currentTimeMillis());
}
