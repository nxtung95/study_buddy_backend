package com.studybuddy.backend.service;

import com.studybuddy.backend.entity.Subject;

public interface SubjectService {
	Subject create(String title);

	Subject edit(int id, String title);

	void remove(int subjectId);

	boolean checkExistSubject(String title);

	Subject findById(int id);
}
