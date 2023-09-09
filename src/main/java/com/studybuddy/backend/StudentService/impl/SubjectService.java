package com.studybuddy.backend.StudentService.impl;

import com.studybuddy.backend.entity.Student;
import com.studybuddy.backend.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class SubjectService {

    private final StudentRepository studentRepository;

    @Autowired
    public SubjectService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public boolean addSubject(int studentId, String subject) {
        Student student = studentRepository.findById(studentId).orElse(null);

        if (student != null) {
//            studentSubject.addSubject(student);
//            studentSubject.addStudent(subject);
//            studentSubjectRepository.addSubject(subject);
        }
        return false;
    }
}
