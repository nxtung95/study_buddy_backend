package com.studybuddy.backend.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.util.List;

public interface UploadFileService {
	void uploadFile(MultipartFile file, int subjectId);

//	Resource fetchFileAsResource(String fileName) throws FileNotFoundException;
//
//	List<FileDetails> getAllFiles();
}
