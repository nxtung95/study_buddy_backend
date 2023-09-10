package com.studybuddy.backend.service;

import com.studybuddy.backend.entity.Subject;

public interface SubjectService {
	Subject create(String title);

	Subject edit(int id, String title);

	Subject remove(int subjectId);
}
