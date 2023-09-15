package com.studybuddy.backend.controller;

import com.studybuddy.backend.entity.Question;
import com.studybuddy.backend.entity.User;
import com.studybuddy.backend.object.AnswerViewObj;
import com.studybuddy.backend.object.CustomMultipartFile;
import com.studybuddy.backend.request.QuestionContactRequest;
import com.studybuddy.backend.request.QuestionRequest;
import com.studybuddy.backend.response.QuestionResponse;
import com.studybuddy.backend.response.ViewQuestionResponse;
import com.studybuddy.backend.service.QuestionService;
import com.studybuddy.backend.service.UploadFileService;
import com.studybuddy.backend.service.UserService;
import com.studybuddy.backend.utils.FormatUtils;
import com.studybuddy.backend.utils.ValidtionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/app/questions")
@Slf4j
public class QuestionController {
	@Autowired
	private UploadFileService uploadFileService;
	@Autowired
	private QuestionService questionService;
	@Autowired
	private UserService userService;

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
				List<User> userList = userService.findTutors();
				User user = userList.stream().filter(u -> u.getId() == question.getTutorId()).findFirst().orElse(null);
				String tutorName = user == null ? "" : user.getFirstName() + " " + user.getLastName();
				res.setTutorName(tutorName);
				res.setStatus(question.getStatus());
				res.setAnswerDate(question.getAnswerDate() != null  ? FormatUtils.formatDate(question.getAnswerDate(), "yyyy-MM-dd HH:mm:ss") : "");
				List<AnswerViewObj> answers = question.getAnswers().stream()
						.map(a -> AnswerViewObj.builder()
								.id(a.getId())
								.content(a.getContent())
								.updatedDate(FormatUtils.formatDate(a.getUpdatedDate(), "yyyy-MM-dd HH:mm:ss"))
								.tutorName(tutorName)
								.build())
						.collect(Collectors.toList());
				res.setAnswers(answers);
				res.setVideoCall(question.getIsVideoCall() == 1 ? true : false);
				res.setChatMessage(question.getIsChatMessage() == 1 ? true : false);
				res.setVoiceCall(question.getIsVoiceCall() == 1 ? true : false);
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
			if (ValidtionUtils.checkEmptyOrNull(rq.getInputText()) || rq.getQuestionId() <= 0 || rq.getSubjectId() <= 0) {
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

	@RequestMapping(value = "/updateStatus", method = RequestMethod.POST)
	public ResponseEntity<QuestionResponse> updateSatus(@RequestBody QuestionContactRequest rq) {
		QuestionResponse res = QuestionResponse.builder()
				.code("00")
				.desc("Success")
				.build();
		try {
			if (rq.getQuestionId() <= 0) {
				res.setCode("01");
				res.setDesc("Invalid question data");
				return ResponseEntity.badRequest().body(res);
			}
			Question question = questionService.findById(rq.getQuestionId());
			question.setAnswerDate(new Timestamp(System.currentTimeMillis()));
			question.setStatus(rq.getStatus());
			questionService.update(question);
			return ResponseEntity.ok().body(res);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			res.setCode("99");
			res.setDesc("System error. Please try again");
		}
		return ResponseEntity.internalServerError().body(res);
	}

	@RequestMapping(value = "/updateContact", method = RequestMethod.POST)
	public ResponseEntity<QuestionResponse> update(@RequestBody QuestionContactRequest rq) {
		QuestionResponse res = QuestionResponse.builder()
				.code("00")
				.desc("Success")
				.build();
		try {
			if (rq.getQuestionId() <= 0) {
				res.setCode("01");
				res.setDesc("Invalid question data");
				return ResponseEntity.badRequest().body(res);
			}
			Question question = questionService.findById(rq.getQuestionId());
			question.setIsChatMessage(rq.getIsAllowChat());
			question.setIsVideoCall(rq.getIsAllowVideoCall());
			question.setIsVoiceCall(rq.getIsAllowVoiceCall());
			questionService.update(question);
			return ResponseEntity.ok().body(res);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			res.setCode("99");
			res.setDesc("System error. Please try again");
		}
		return ResponseEntity.internalServerError().body(res);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ResponseEntity<QuestionResponse> delete(@RequestBody QuestionRequest rq) {
		QuestionResponse res = QuestionResponse.builder()
				.code("00")
				.desc("Success")
				.build();
		try {
			if (rq.getQuestionId() <= 0 || rq.getSubjectId() <= 0) {
				res.setCode("01");
				res.setDesc("Invalid question data");
				return ResponseEntity.badRequest().body(res);
			}
			// Delete old file
			uploadFileService.deleteFile(rq.getSubjectId(), rq.getQuestionId());

			questionService.delete(rq.getQuestionId());

			res.setCard(Question.builder().subjectId(rq.getSubjectId()).id(rq.getQuestionId()).build());
			return new ResponseEntity<>(res, HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			res.setCode("99");
			res.setDesc("System error. Please try again");
		}
		return ResponseEntity.internalServerError().body(res);
	}
}
