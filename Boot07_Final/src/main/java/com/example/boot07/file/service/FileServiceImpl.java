package com.example.boot07.file.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.example.boot07.exception.NotDeleteException;
import com.example.boot07.file.dao.FileDao;
import com.example.boot07.file.dto.FileDto;

@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private FileDao dao;
    
    // 다운로드 | 업로드 파일이 저장된 위치 얻어내기
    @Value("${file.location}")
    private String fileLocation;

    @Override
    public void getList(HttpServletRequest request) {
        final int PAGE_ROW_COUNT = 5;
        final int PAGE_DISPLAY_COUNT = 5;

        int pageNum = 1;

        String strPageNum = request.getParameter("pageNum");
        if (strPageNum != null) {
            pageNum = Integer.parseInt(strPageNum);
        }

        int startRowNum = 1 + (pageNum - 1) * PAGE_ROW_COUNT;
        int endRowNum = pageNum * PAGE_ROW_COUNT;

        // 검색 키워드 처리
        String keyword = request.getParameter("keyword");
        String condition = request.getParameter("condition");

        if (keyword == null) {
            // 클라이언트에 출력할때 "null" 을 출력되지 않게 하기 위해 빈 문자열을 넣어준다.
            keyword = "";
            condition = "";
        }

        String encodedK = URLEncoder.encode(keyword);

        FileDto dto = new FileDto();
        dto.setStartRowNum(startRowNum);
        dto.setEndRowNum(endRowNum);

        if (!keyword.equals("")) {
            // 제목 + 파일명 검색인 경우
            if (condition.equals("title_filename")) {
                dto.setTitle(keyword);
                dto.setOrgFileName(keyword);
                // 제목 검색인 경우
            } else if (condition.equals("title")) {
                dto.setTitle(keyword);
                // 작성자 검색인 경우
            } else if (condition.equals("writer")) {
                dto.setWriter(keyword);
            }
        }

        List<FileDto> list = dao.getList(dto);

        int totalRow = dao.getCount(dto);
        int startPageNum = 1 + ((pageNum - 1) / PAGE_DISPLAY_COUNT) * PAGE_DISPLAY_COUNT;
        int endPageNum = startPageNum + PAGE_DISPLAY_COUNT - 1;

        int totalPageCount = (int) Math.ceil(totalRow / (double) PAGE_ROW_COUNT);
        if (endPageNum > totalPageCount) {
            endPageNum = totalPageCount;
        }

        // 응답에 필요한 데이터를 view page 에 전달하기 위해 request scope 에 담는다
        request.setAttribute("list", list);
        request.setAttribute("pageNum", pageNum);
        request.setAttribute("startPageNum", startPageNum);
        request.setAttribute("endPageNum", endPageNum);
        request.setAttribute("totalPageCount", totalPageCount);
        request.setAttribute("keyword", keyword);
        request.setAttribute("encodedK", encodedK);
        request.setAttribute("totalRow", totalRow);
        request.setAttribute("condition", condition);
    }

    @Override
    public void saveFile(FileDto dto, Model model, HttpServletRequest request) {
        MultipartFile myFile = dto.getMyFile();
        String orgFileName = myFile.getOriginalFilename();
        long fileSize = myFile.getSize();

        String saveFileName = UUID.randomUUID().toString();
        String filePath = fileLocation + File.separator+saveFileName;
        File upload = new File(filePath);

        if (!upload.exists()) {
            upload.mkdir();
        }

        try {
            myFile.transferTo(new File(filePath));
            System.out.println("FileService filePath : "+filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String id = (String) request.getSession().getAttribute("id");
        dto.setWriter(id);
        dto.setOrgFileName(orgFileName);
        dto.setSaveFileName(saveFileName);
        dto.setFileSize(fileSize);
        dao.insert(dto);
        model.addAttribute("dto", dto);
    }

    @Override
    public ResponseEntity<InputStreamResource> getFileDate(int num) throws UnsupportedEncodingException, FileNotFoundException {
    	FileDto dto = dao.getData(num);
    	
    	String encodedName = URLEncoder.encode(dto.getOrgFileName(),"utf-8");
    	encodedName = encodedName.replaceAll("\\+"," ");
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_TYPE, "application/octet-stream"); 
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename="+encodedName);
		headers.setContentLength(dto.getFileSize());

		String filePath = fileLocation + File.separator + dto.getSaveFileName();
		InputStream is=new FileInputStream(filePath);
		InputStreamResource isr=new InputStreamResource(is);

		//ResponseEntity 객체의 참조값 얻어내기 
		ResponseEntity<InputStreamResource> resEn=ResponseEntity.ok()
			.headers(headers)
			.body(isr);

		return resEn;
    }

    @Override
    public void deleteFile(int num, HttpServletRequest request) {
        FileDto dto = dao.getData(num);

        String id = (String) request.getSession().getAttribute("id");
        if (!dto.getWriter().equals(id)) {
            throw new NotDeleteException("타인의 파일은 지울 수 없습니다.");
        }

        // 파일 시스템에서 삭제
        String saveFileName = dto.getSaveFileName();
        String path = request.getServletContext().getRealPath("/resources/upload") + File.separator + saveFileName;
        new File(path).delete();

        // DB에서 정보 삭제
        dao.delete(num);
    }
}
