package com.studybuddy.backend.service.impl;

import com.studybuddy.backend.service.UploadFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

@Slf4j
@Service
public class UploadFileServiceImpl implements UploadFileService {
	private final String UPLOAD_PATH = "/image/subject";

	@Override
	public void uploadFile(MultipartFile file, int subjectId) {
		try {
			Files.createDirectories(Paths.get(UPLOAD_PATH + "/" + subjectId));

			File saveFile = new File(UPLOAD_PATH + "/" + subjectId + "/" + file.getOriginalFilename());
			file.transferTo(saveFile);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

//	@Override
//	public Resource fetchFileAsResource(String fileName) throws FileNotFoundException {
//		return null;
//	}
//
//	@Override
//	public List<FileDetails> getAllFiles() {
//		return null;
//	}
}
