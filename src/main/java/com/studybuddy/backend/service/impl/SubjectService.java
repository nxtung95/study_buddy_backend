package com.studybuddy.backend.service.impl;

import com.studybuddy.backend.entity.User;
import com.studybuddy.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class SubjectService {

    private final UserRepository userRepository;

    @Autowired
    public SubjectService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean addSubject(int studentId, String subject) {
        User user = userRepository.findById(studentId).orElse(null);

        if (user != null) {
//            studentSubject.addSubject(student);
//            studentSubject.addStudent(subject);
//            studentSubjectRepository.addSubject(subject);
        }
        return false;
    }
}
