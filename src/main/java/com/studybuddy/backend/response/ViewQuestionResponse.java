package com.studybuddy.backend.response;

import com.studybuddy.backend.object.AnswerViewObj;
import com.studybuddy.backend.object.FileUpload;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
public class ViewQuestionResponse extends BaseResponse {
	private String inputText;
	private List<FileUpload> images;
	private List<AnswerViewObj> answers;
	private FileUpload solution;
	private boolean isVoiceCall;
	private boolean isChatMessage;
	private boolean isVideoCall;
}
