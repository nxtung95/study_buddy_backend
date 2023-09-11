package com.studybuddy.backend.service.impl;

import com.studybuddy.backend.entity.Subject;
import com.studybuddy.backend.repository.SubjectRepository;
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

    @Override
    @Transactional
    public Subject create(String title) {
        try {
            int currentUserid = authenticationService.extractUserIdLogin();
            Subject subject = Subject.builder()
                    .title(title)
                    .userId(currentUserid)
                    .build();
            Subject addSubject = subjectRepository.save(subject);
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
    public void remove(int subjectId) {
        try {
            Subject subject = subjectRepository.findById(subjectId).orElse(null);
            if (subject == null) {
                return;
            }
            subjectRepository.deleteById(subject.getId());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
