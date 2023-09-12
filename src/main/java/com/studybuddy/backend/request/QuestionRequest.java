package com.studybuddy.backend.request;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class QuestionRequest {
	private String title;
	private int subjectId;
	private int tutorId;
	private String inputText;
	private MultipartFile[] files;
}
