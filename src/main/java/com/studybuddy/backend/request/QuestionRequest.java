package com.studybuddy.backend.request;

import com.studybuddy.backend.object.FileUpload;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionRequest {
	private String title;
	private int subjectId;
	private int tutorId;
	private String inputText;
	private List<FileUpload> files;
}
