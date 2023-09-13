package com.studybuddy.backend.service.impl;

import com.studybuddy.backend.entity.Question;
import com.studybuddy.backend.repository.QuestionRepository;
import com.studybuddy.backend.service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    @Override
    @Transactional
    public Question create(int tutorId, int subjectId, String filePath, String title, String inputDetail) {
        try {
            Question question = Question.builder()
                    .title(title)
                    .subjectId(subjectId)
                    .tutorId(tutorId)
                    .inputDetail(inputDetail)
                    .imageDetailUrl(filePath)
                    .build();
            return questionRepository.save(question);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }
//
//    @Override
//    @Transactional
//    public Question edit(int id, String title) {
//        try {
//            Subject subject = subjectRepository.findById(id).orElse(null);
//            if (subject == null) {
//                return null;
//            }
//            subject.setTitle(title);
//            Subject addSubject = subjectRepository.save(subject);
//            return addSubject;
//        } catch (Exception e) {
//            log.error(e.getMessage(), e);
//        }
//        return null;
//    }
//
//    @Override
//    @Transactional
//    public void remove(int subjectId) {
//        try {
//            subjectRepository.deleteById(subjectId);
//        } catch (Exception e) {
//            log.error(e.getMessage(), e);
//        }
//    }
//
//    @Override
//    public Question findById(int id) {
//        try {
//            Subject subject = subjectRepository.findById(id).orElse(null);
//            return subject;
//        } catch (Exception e) {
//            log.error(e.getMessage(), e);
//        }
//        return null;
//    }
}