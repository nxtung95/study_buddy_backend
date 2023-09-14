package com.studybuddy.backend.controller;

import com.studybuddy.backend.entity.Question;
import com.studybuddy.backend.object.CustomMultipartFile;
import com.studybuddy.backend.request.QuestionRequest;
import com.studybuddy.backend.response.QuestionResponse;
import com.studybuddy.backend.response.ViewQuestionResponse;
import com.studybuddy.backend.service.QuestionService;
import com.studybuddy.backend.service.UploadFileService;
import com.studybuddy.backend.utils.ValidtionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/app/questions")
@Slf4j
public class QuestionController {
	@Autowired
	private UploadFileService uploadFileService;
	@Autowired
	private QuestionService questionService;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<QuestionResponse> add(@RequestBody QuestionRequest rq) {
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
			String filePath = rq.getFiles().stream().map(f -> f.getFileName()).collect(Collectors.joining(","));

			Question question = questionService.create(rq.getTutorId(), rq.getSubjectId(), filePath, rq.getTitle(), rq.getInputText());
			rq.getFiles().stream().forEach(fileUpload -> {
				CustomMultipartFile customMultipartFile = new CustomMultipartFile(fileUpload.getData().getBytes(StandardCharsets.UTF_8), fileUpload.getFileName());
				uploadFileService.uploadFile(customMultipartFile, rq.getSubjectId(), question.getId());
			});
			res.setCard(question);
			return new ResponseEntity<>(res, HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			res.setCode("99");
			res.setDesc("System error. Please try again");
		}
		return ResponseEntity.internalServerError().body(res);
	}

	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public ResponseEntity<ViewQuestionResponse> view(@RequestParam(name = "subjectId") int subjectId, @RequestParam(name = "questionId") int questionId) {
		ViewQuestionResponse res = ViewQuestionResponse.builder()
				.code("00")
				.desc("Success")
				.build();
		try {

			Question question = questionService.findById(questionId);
			if (question != null) {
				res.setInputText(question.getInputDetail());
				res.setImages(uploadFileService.getFile(subjectId, questionId));
				res.setAnswers(question.getAnswers().stream().toList());
				res.setVideoCall(question.isVideoCall());
				res.setChatMessage(question.isChatMessage());
				res.setVideoCall(question.isVideoCall());
				return new ResponseEntity<>(res, HttpStatus.OK);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			res.setCode("99");
			res.setDesc("System error. Please try again");
		}
		return ResponseEntity.internalServerError().body(res);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResponseEntity<QuestionResponse> update(@RequestBody QuestionRequest rq) {
		QuestionResponse res = QuestionResponse.builder()
				.code("00")
				.desc("Success")
				.build();
		try {
			if (ValidtionUtils.checkEmptyOrNull(rq.getInputText()) || rq.getFiles() == null
					|| rq.getFiles().isEmpty() || rq.getQuestionId() <= 0 || rq.getSubjectId() <= 0) {
				res.setCode("01");
				res.setDesc("Invalid question data");
				return ResponseEntity.badRequest().body(res);
			}
			String filePath = rq.getFiles().stream().map(f -> f.getFileName()).collect(Collectors.joining(","));
			if (questionService.update(rq.getQuestionId(), filePath, rq.getInputText())) {
				// Delete old file
				uploadFileService.deleteFile(rq.getSubjectId(), rq.getQuestionId());

				// Upload new file
				rq.getFiles().stream().forEach(fileUpload -> {
					CustomMultipartFile customMultipartFile = new CustomMultipartFile(fileUpload.getData().getBytes(StandardCharsets.UTF_8), fileUpload.getFileName());
					uploadFileService.uploadFile(customMultipartFile, rq.getSubjectId(), rq.getQuestionId());
				});

				return new ResponseEntity<>(res, HttpStatus.OK);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			res.setCode("99");
			res.setDesc("System error. Please try again");
		}
		return ResponseEntity.internalServerError().body(res);
	}
}
