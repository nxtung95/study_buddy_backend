package com.studybuddy.backend.service.impl;

import com.studybuddy.backend.entity.Answer;
import com.studybuddy.backend.repository.AnswerRepository;
import com.studybuddy.backend.service.AnswerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class AnswerServiceImpl implements AnswerService {
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private AnswerRepository answerRepository;

    @Override
    @Transactional
    public Answer create(int questionId, String content) {
        try {
            int currentUserid = authenticationService.extractUserIdLogin();
            Answer answer = Answer.builder()
                    .questionId(questionId)
                    .userId(currentUserid)
                    .content(content)
                    .build();
            Answer addAnswer = answerRepository.save(answer);
            return addAnswer;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }
}
