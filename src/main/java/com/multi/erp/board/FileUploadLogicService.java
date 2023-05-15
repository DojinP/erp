package com.multi.erp.board;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadLogicService {
	// 파일 업로드를 수행하는 메소드 - 업로드된 파일의 정보를 BoardFileDTO로 변환해서 리턴
	// 여러 개인 경우 BoardFileDTO를 List에 담아서 리턴
	public List<BoardFileDTO> uploadFiles(List<MultipartFile> multipartFiles, String path) throws IllegalStateException, IOException {
		List<BoardFileDTO> filedtolist = new ArrayList<BoardFileDTO>();
		int count = 1;
		for (MultipartFile multipartFile : multipartFiles) {
			// 업로드를 하는 경우 원본파일명과 서버에서 식별할 수 있는 실제 서버에 저장되는 파일명 두 개를 관리
			// 클라이언트가 업로드한 원본파일명
			if (!multipartFile.isEmpty()) {
				String originalFileName = multipartFile.getOriginalFilename();
				// 서버에서 식별할 수 있도록 파일명을 변경
				String storeFileName = createStoreFileName(originalFileName);
				// 파일명과path를 이용해서 실제 File 객체를 만든 후 업로드 하기
				// XXXXX/WEB-INF/upload + / + 파일명
				multipartFile.transferTo(new File(path + File.separator + storeFileName));
				System.out.println("원본파일명: " + originalFileName);
				System.out.println("저장파일명: " + storeFileName);
				filedtolist.add(new BoardFileDTO(null, originalFileName, storeFileName, count+""));
				count++;
			}
		}
		return filedtolist;
	}

	// UUID를 이용해서 파일명을 변경해서 리턴하는 메소드
	private String createStoreFileName(String originalFileName) {
		int pos = originalFileName.lastIndexOf("."); // 확장자의 위치를 저장
		String ext = originalFileName.substring(pos + 1); // . 위치 다음부터 잘라내기 위해 + 1 (시작index만 지정하면 시작index부터 끝까지 추출)
		String uuid = UUID.randomUUID().toString();

		return uuid + "." + ext;
	}
}
