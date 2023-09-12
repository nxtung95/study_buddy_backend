package com.studybuddy.backend.controller;

import com.studybuddy.backend.entity.Question;
import com.studybuddy.backend.object.FileDetails;
import com.studybuddy.backend.request.QuestionRequest;
import com.studybuddy.backend.response.QuestionResponse;
import com.studybuddy.backend.service.QuestionService;
import com.studybuddy.backend.service.UploadFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/app/questions")
@Slf4j
public class QuestionController {
	@Autowired
	private UploadFileService uploadFileService;
	@Autowired
	private QuestionService questionService;

	@PostMapping(value = "/add")
	public ResponseEntity<QuestionResponse> uploadFiles(@RequestBody QuestionRequest rq) {
		QuestionResponse res = QuestionResponse.builder()
				.code("00")
				.desc("Success")
				.build();
		try {
			StringBuilder filePath = new StringBuilder();
			Arrays.stream(rq.getFiles()).forEach(file -> {
				FileDetails fileDetails = uploadFileService.uploadFile(file);
				if (fileDetails != null) {
					filePath.append(fileDetails.getFileUri() + ",");
				}
			});
			Question question = questionService.create(rq.getTutorId(), rq.getSubjectId(), filePath.toString(), rq.getTitle(), rq.getInputText());
			res.setQuestion(question);
			return new ResponseEntity<>(res, HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			res.setCode("99");
			res.setDesc("System error. Please try again");
		}
		return ResponseEntity.ok(res);
	}
}
