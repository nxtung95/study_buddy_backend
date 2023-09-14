package com.studybuddy.backend.response;

import com.studybuddy.backend.entity.Answer;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
public class AnswerResponse extends BaseResponse {
	private Answer answer;
}
