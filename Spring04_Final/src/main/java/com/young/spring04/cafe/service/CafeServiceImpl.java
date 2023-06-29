package com.young.spring04.cafe.service;

import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.young.spring04.cafe.dao.CafeDao;
import com.young.spring04.cafe.dto.CafeDto;
import com.young.spring04.exception.NotDeleteException;

@Service
public class CafeServiceImpl implements CafeService {
	@Autowired
	private CafeDao cafedao;

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

		CafeDto dto = new CafeDto();
		dto.setStartRowNum(startRowNum);
		dto.setEndRowNum(endRowNum);

		if (!keyword.equals("")) {
			// 제목 + 내용 검색인 경우
			if(condition.equals("title_content")) {
				dto.setTitle(keyword);
				dto.setContent(keyword);
				// 제목 검색인 경우
			} else if (condition.equals("title")) {
				dto.setTitle(keyword);
				// 작성자 검색인 경우
			} else if (condition.equals("writer")) {
				dto.setWriter(keyword);
			}
		}

		List<CafeDto> list = cafedao.getList(dto);

		int totalRow = cafedao.getCount(dto);
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
	public void getDetail(HttpServletRequest request) {
		int num = Integer.parseInt(request.getParameter("num"));
		
		String keyword=request.getParameter("keyword");
		String condition=request.getParameter("condition");
		if(keyword==null){
			keyword="";
			condition=""; 
		}
		CafeDto dto=new CafeDto();
		dto.setNum(num);
		if(!keyword.equals("")){
			if(condition.equals("title_content")){
				dto.setTitle(keyword);
				dto.setContent(keyword);			
			}else if(condition.equals("title")){
				dto.setTitle(keyword);	
			}else if(condition.equals("writer")){
				dto.setWriter(keyword);	
			}
		}
		
		String encodedK=URLEncoder.encode(keyword);
		
		CafeDto resultDto = cafedao.getData(dto);
		
		cafedao.addViewCount(num);
		
		request.setAttribute("dto", resultDto);
		request.setAttribute("condition", condition);
		request.setAttribute("keyword", keyword);
		request.setAttribute("encodedK", encodedK);
	}

	@Override
	public void saveContent(CafeDto dto) {
		cafedao.insert(dto);
	}

	@Override
	public void updateContent(CafeDto dto) {
		cafedao.update(dto);
	}

	@Override
	public void deleteContent(int num, HttpServletRequest request) {
		String id = (String)request.getSession().getAttribute("id");
		CafeDto dto = cafedao.getData(num);
		if(!id.equals(dto.getWriter())) {
			throw new NotDeleteException("다른 사람의 게시물을 삭제할 수 없습니다.");
		}
		cafedao.delete(num);
	}

	@Override
	public void getData(HttpServletRequest request) {
		int num = Integer.parseInt(request.getParameter("num"));

		CafeDto dto = cafedao.getData(num);
		
		request.setAttribute("dto", dto);
	}

}
