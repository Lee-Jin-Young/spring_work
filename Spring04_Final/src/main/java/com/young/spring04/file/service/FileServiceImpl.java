package com.young.spring04.file.service;

import java.io.File;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.young.spring04.exception.NotDeleteException;
import com.young.spring04.file.dao.FileDao;
import com.young.spring04.file.dto.FileDto;

@Service
public class FileServiceImpl implements FileService {
	@Autowired
	private FileDao dao;

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
	public void saveFile(FileDto dto, ModelAndView mView, HttpServletRequest request) {
		MultipartFile myFile = dto.getMyFile();
		String orgFileName = myFile.getOriginalFilename();
		long fileSize = myFile.getSize();

		String realPath = request.getServletContext().getRealPath("/resources/upload");
		String filePath = realPath + File.separator;
		File upload = new File(filePath);

		if (!upload.exists()) {
			upload.mkdir();
		}

		String saveFileName = System.currentTimeMillis() + orgFileName;

		try {
			myFile.transferTo(new File(filePath + saveFileName));
			System.out.println(filePath + saveFileName);
		} catch (Exception e) {
			e.printStackTrace();
		}

		String id = (String) request.getSession().getAttribute("id");
		dto.setWriter(id);
		dto.setOrgFileName(orgFileName);
		dto.setSaveFileName(saveFileName);
		dto.setFileSize(fileSize);
		dao.insert(dto);
		mView.addObject("dto", dto);
	}

	@Override
	public void getFileData(int num, ModelAndView mView) {
		FileDto dto = dao.getData(num);
		mView.addObject("dto", dto);
	}

	@Override
	public void deleteFile(int num, HttpServletRequest request) {
		FileDto dto = dao.getData(num);
		
		String id = (String)request.getSession().getAttribute("id");
		if(!dto.getWriter().equals(id) ) {
			throw new NotDeleteException("타인의 파일은 지울 수 없습니다.");
		}
		
		// 파일 시스템에서 삭제
		String saveFileName = dto.getSaveFileName();
		String path = request.getServletContext().getRealPath("/resources/upload")
				+ File.separator
				+ saveFileName;
		new File(path).delete();
		
		//DB에서 정보 삭제
		dao.delete(num);
	}

}
