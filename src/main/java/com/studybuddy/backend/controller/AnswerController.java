package com.studybuddy.backend.controller;

import com.studybuddy.backend.entity.Answer;
import com.studybuddy.backend.request.AnswerRequest;
import com.studybuddy.backend.response.AnswerResponse;
import com.studybuddy.backend.service.AnswerService;
import com.studybuddy.backend.utils.ValidtionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app/answers")
@Slf4j
public class AnswerController {
    @Autowired
    private AnswerService answerService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<AnswerResponse> add(@RequestBody AnswerRequest rq) {
        AnswerResponse res = AnswerResponse.builder()
                .code("00")
                .desc("Success")
                .build();
        try {
            if (ValidtionUtils.checkEmptyOrNull(rq.getContent()) || rq.getQuestionId() <= 0) {
                res.setCode("01");
                res.setDesc("Invalid answer data");
                return ResponseEntity.badRequest().body(res);
            }
            Answer answer = answerService.create(rq.getQuestionId(), rq.getContent());
            res.setAnswer(answer);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            res.setCode("99");
            res.setDesc("System error. Please try again");
        }
        return ResponseEntity.internalServerError().body(res);
    }
}
