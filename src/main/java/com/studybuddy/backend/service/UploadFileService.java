package com.studybuddy.backend.service;

import com.studybuddy.backend.object.FileDetails;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface UploadFileService {
	FileDetails uploadFile(MultipartFile file);

	Resource fetchFileAsResource(String fileName) throws FileNotFoundException;

	List<FileDetails> getAllFiles();
}
