package com.studybuddy.backend.service;

import com.studybuddy.backend.entity.Answer;

public interface AnswerService {
	Answer create(int questionId, String content);
}
