package com.studybuddy.backend.service;

import com.studybuddy.backend.entity.Question;

public interface QuestionService {
	Question create(int tutorId, int subjectId, String filePath, String title, String inputDetail);

    Question findById(int questionId);

    boolean update(int questionId, String filePath, String inputText);
}
