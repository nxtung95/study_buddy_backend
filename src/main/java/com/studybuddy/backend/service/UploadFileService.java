package com.studybuddy.backend.service;

import com.studybuddy.backend.object.FileUpload;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.util.List;

public interface UploadFileService {
	void uploadFile(MultipartFile file, int subjectId, int questionId);

	List<FileUpload> getFile(int subjectId, int questionId);

	void deleteFile(int subjectId, int questionId);

//	Resource fetchFileAsResource(String fileName) throws FileNotFoundException;
//
//	List<FileDetails> getAllFiles();
}
