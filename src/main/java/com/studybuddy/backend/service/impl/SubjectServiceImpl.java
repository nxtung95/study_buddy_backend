package com.studybuddy.backend.service.impl;

import com.studybuddy.backend.entity.Subject;
import com.studybuddy.backend.entity.UserSubject;
import com.studybuddy.backend.entity.UserSubjectKey;
import com.studybuddy.backend.repository.SubjectRepository;
import com.studybuddy.backend.repository.UserSubjectRepository;
import com.studybuddy.backend.service.AuthenticationService;
import com.studybuddy.backend.service.SubjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class SubjectServiceImpl implements SubjectService {
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private UserSubjectRepository userSubjectRepository;

    @Override
    @Transactional
    public Subject create(String title) {
        try {
            Subject subject = Subject.builder()
                    .title(title)
                    .build();
            Subject addSubject = subjectRepository.save(subject);
            int currentUserId = authenticationService.extractUserIdLogin();
            UserSubjectKey userSubjectKey = new UserSubjectKey();
            userSubjectKey.setUserId(currentUserId);
            userSubjectKey.setSubjectId(addSubject.getId());
            userSubjectRepository.save(UserSubject.builder()
                    .id(userSubjectKey)
                    .build());
            return addSubject;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    @Transactional
    public Subject edit(int id, String title) {
        try {
            Subject subject = subjectRepository.findById(id).orElse(null);
            if (subject == null) {
                return null;
            }
            subject.setTitle(title);
            Subject addSubject = subjectRepository.save(subject);
            return addSubject;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    @Transactional
    public Subject remove(int subjectId) {
        try {
            Subject subject = subjectRepository.findById(subjectId).orElse(null);
            if (subject == null) {
                return null;
            }
            int currentUserId = authenticationService.extractUserIdLogin();
            UserSubjectKey userSubjectKey = new UserSubjectKey();
            userSubjectKey.setUserId(currentUserId);
            userSubjectKey.setSubjectId(subject.getId());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }
}
