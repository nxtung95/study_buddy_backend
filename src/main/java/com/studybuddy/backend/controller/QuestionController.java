package com.studybuddy.backend.controller;

import com.google.gson.Gson;
import com.studybuddy.backend.entity.Question;
import com.studybuddy.backend.object.CustomMultipartFile;
import com.studybuddy.backend.request.QuestionRequest;
import com.studybuddy.backend.response.QuestionResponse;
import com.studybuddy.backend.service.QuestionService;
import com.studybuddy.backend.service.UploadFileService;
import com.studybuddy.backend.utils.ValidtionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/app/questions")
@Slf4j
public class QuestionController {
	@Autowired
	private UploadFileService uploadFileService;
	@Autowired
	private QuestionService questionService;
	@Autowired
	private Gson gson;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<QuestionResponse> uploadFiles(@RequestBody QuestionRequest rq) {
		QuestionResponse res = QuestionResponse.builder()
				.code("00")
				.desc("Success")
				.build();
		try {
			if (ValidtionUtils.checkEmptyOrNull(rq.getTitle(), rq.getInputText())) {
				res.setCode("01");
				res.setDesc("Invalid question data");
				return ResponseEntity.badRequest().body(res);
			}
			StringBuilder filePath = new StringBuilder();

			rq.getFiles().stream().forEach(fileUpload -> {
				CustomMultipartFile customMultipartFile = new CustomMultipartFile(fileUpload.getData().getBytes(StandardCharsets.UTF_8), fileUpload.getFileName());
				uploadFileService.uploadFile(customMultipartFile, rq.getSubjectId());
				filePath.append(customMultipartFile.getOriginalFilename() + ",");
			});
			Question question = questionService.create(rq.getTutorId(), rq.getSubjectId(), filePath.toString(), rq.getTitle(), rq.getInputText());
			res.setCard(question);
			return new ResponseEntity<>(res, HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			res.setCode("99");
			res.setDesc("System error. Please try again");
		}
		return ResponseEntity.internalServerError().body(res);
	}
}
