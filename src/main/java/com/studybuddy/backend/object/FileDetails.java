package com.studybuddy.backend.object;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FileDetails {
	private String fileName;
	private String fileUri;
	private String fileDownloadUri;
	private long fileSize;
}
