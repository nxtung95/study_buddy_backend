package com.studybuddy.backend.service.impl;

import com.studybuddy.backend.object.FileDetails;
import com.studybuddy.backend.service.UploadFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class UploadFileServiceImpl implements UploadFileService {
	private final Path UPLOAD_PATH =
			Paths.get(new ClassPathResource("").getFile().getAbsolutePath() + File.separator + "static"  + File.separator + "image");

	public UploadFileServiceImpl() throws IOException {
	}

	@Override
	public FileDetails uploadFile(MultipartFile file) {
		try {
			if (!Files.exists(UPLOAD_PATH)) {
				Files.createDirectories(UPLOAD_PATH);
			}

			// file format validation
			if (!file.getContentType().equals("image/jpeg") && !file.getContentType().equals("image/png")) {
				throw new Exception("only .jpeg and .png images are " + "supported");
			}

			String timeStampedFileName = new SimpleDateFormat("ssmmHHddMMyyyy")
					.format(new Date()) + "_" + file.getOriginalFilename();

			Path filePath = UPLOAD_PATH.resolve(timeStampedFileName);
			Files.copy(file.getInputStream(), filePath);

			String fileUri = ServletUriComponentsBuilder.fromCurrentContextPath()
					.path("/image/").path(timeStampedFileName).toUriString();

			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
					.path("/file/download/").path(timeStampedFileName).toUriString();

			FileDetails fileDetails = new FileDetails(file.getOriginalFilename(), fileUri, fileDownloadUri, file.getSize());
			return fileDetails;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return null;
		}
	}

	@Override
	public Resource fetchFileAsResource(String fileName) throws FileNotFoundException {
		return null;
	}

	@Override
	public List<FileDetails> getAllFiles() {
		return null;
	}
}
