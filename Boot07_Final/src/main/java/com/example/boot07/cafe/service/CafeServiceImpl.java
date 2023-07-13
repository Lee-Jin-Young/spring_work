package com.example.boot07.cafe.service;

import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.example.boot07.cafe.dao.CafeCommentDao;
import com.example.boot07.cafe.dao.CafeDao;
import com.example.boot07.cafe.dto.CafeCommentDto;
import com.example.boot07.cafe.dto.CafeDto;
import com.example.boot07.exception.NotDeleteException;

@Service
public class CafeServiceImpl implements CafeService {
    @Autowired
    private CafeDao cafeDao;

    @Autowired
    private CafeCommentDao cafeCommentDao;

    @Override
    public void getList(Model model) {
    	HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
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
            if (condition.equals("title_content")) {
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

        List<CafeDto> list = cafeDao.getList(dto);

        int totalRow = cafeDao.getCount(dto);
        int startPageNum = 1 + ((pageNum - 1) / PAGE_DISPLAY_COUNT) * PAGE_DISPLAY_COUNT;
        int endPageNum = startPageNum + PAGE_DISPLAY_COUNT - 1;

        int totalPageCount = (int) Math.ceil(totalRow / (double) PAGE_ROW_COUNT);
        if (endPageNum > totalPageCount) {
            endPageNum = totalPageCount;
        }

        model.addAttribute("list", list);
        model.addAttribute("pageNum", pageNum);
        model.addAttribute("startPageNum", startPageNum);
        model.addAttribute("endPageNum", endPageNum);
        model.addAttribute("totalPageCount", totalPageCount);
        model.addAttribute("keyword", keyword);
        model.addAttribute("encodedK", encodedK);
        model.addAttribute("totalRow", totalRow);
        model.addAttribute("condition", condition);
    }

    @Override
    public void getDetail(Model model) {
    	HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        int num = Integer.parseInt(request.getParameter("num"));
        
        cafeDao.addViewCount(num);

        String keyword = request.getParameter("keyword");
        String condition = request.getParameter("condition");
        
        // 검색 키워드가 없을 때
        if (keyword == null) {
            keyword = "";
            condition = "";
        }
        
        CafeDto dto = new CafeDto();
        dto.setNum(num);
        
        // 검색 키워드가 있을 때
        if (!keyword.equals("")) {
            if (condition.equals("title_content")) {
                dto.setTitle(keyword);
                dto.setContent(keyword);
            } else if (condition.equals("title")) {
                dto.setTitle(keyword);
            } else if (condition.equals("writer")) {
                dto.setWriter(keyword);
            }
        }
        CafeDto resultDto = cafeDao.getData(dto);

        String encodedK = URLEncoder.encode(keyword);

        /*
         * [ 댓글 페이징 처리에 관련된 로직 ]
         */
        
        // 한 페이지에 몇개씩 표시할 것인지
        final int PAGE_ROW_COUNT = 10;

        // detail.jsp 페이지에서는 항상 1페이지의 댓글 내용만 출력한다.
        int pageNum = 1;

        // 보여줄 페이지의 시작 ROWNUM
        int startRowNum = 1 + (pageNum - 1) * PAGE_ROW_COUNT;
        // 보여줄 페이지의 끝 ROWNUM
        int endRowNum = pageNum * PAGE_ROW_COUNT;

        // 원글의 글번호를 이용해서 해당글에 달린 댓글 목록을 얻어온다.
        CafeCommentDto commentDto = new CafeCommentDto();
        commentDto.setRef_group(num);
        // 1페이지에 해당하는 startRowNum 과 endRowNum 을 dto 에 담아서
        commentDto.setStartRowNum(startRowNum);
        commentDto.setEndRowNum(endRowNum);

        // 1페이지에 해당하는 댓글 목록만 select 되도록 한다.
        List<CafeCommentDto> commentList = cafeCommentDao.getList(commentDto);

        // 원글의 글번호를 이용해서 댓글 전체의 갯수를 얻어낸다.
        int totalRow = cafeCommentDao.getCount(num);
        // 댓글 전체 페이지의 갯수
        int totalPageCount = (int) Math.ceil(totalRow / (double) PAGE_ROW_COUNT);

        model.addAttribute("dto", resultDto);
        model.addAttribute("condition", condition);
        model.addAttribute("keyword", keyword);
        model.addAttribute("encodedK", encodedK);
        model.addAttribute("totalRow", totalRow);
        model.addAttribute("commentList", commentList);
        model.addAttribute("totalPageCount", totalPageCount);
    }

    @Override
    public void saveContent(CafeDto dto) {
        cafeDao.insert(dto);
    }

    @Override
    public void updateContent(CafeDto dto) {
        cafeDao.update(dto);
    }

    @Override
    public void deleteContent(int num, HttpSession session) {
        String id = (String)session.getAttribute("id");
        
        CafeDto dto = cafeDao.getData(num);
        
        if (!id.equals(dto.getWriter())) {
            throw new NotDeleteException("다른 사람의 게시물을 삭제할 수 없습니다.");
        }
        
        cafeDao.delete(num);
    }

    @Override
    public void getData(Model model) {
    	HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        int num = Integer.parseInt(request.getParameter("num"));

        CafeDto dto = cafeDao.getData(num);

        model.addAttribute("dto", dto);
    }

    /*
     * [댓글 기능]
     */

    @Override
    public void savecomment(Model model) {
    	HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        int ref_group = Integer.parseInt(request.getParameter("ref_group"));
        String target_id = request.getParameter("target_id");
        String content = request.getParameter("content");

        // 원글의 댓글인지 대댓글인지 판단 (null값일 경우 원댓글)
        String comment_group = request.getParameter("comment_group");

        String writer = (String) request.getSession().getAttribute("id");
        int seq = cafeCommentDao.getSequence();

        CafeCommentDto dto = new CafeCommentDto();
        dto.setNum(seq);
        dto.setWriter(writer);
        dto.setTarget_id(target_id);
        dto.setContent(content);
        dto.setRef_group(ref_group);

        if (comment_group == null) {
            dto.setComment_group(seq);
        } else {
            dto.setComment_group(Integer.parseInt(comment_group));
        }

        cafeCommentDao.insert(dto);
    }

    @Override
    public void deleteComment(Model model) {
    	HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        int num = Integer.parseInt(request.getParameter("num"));
        CafeCommentDto dto = cafeCommentDao.getData(num);
        
        String id = (String)request.getSession().getAttribute("id");
        if(!dto.getWriter().equals(id)) {
            throw new NotDeleteException("타인의 댓글을 지울 수 없습니다.");
        }
        
        cafeCommentDao.delete(num);
    }

    @Override
    public void updateComment(CafeCommentDto dto) {
        cafeCommentDao.update(dto);
    }

    @Override
    public void moreCommentList(Model model) {
    	HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String id = (String)request.getSession().getAttribute("id");
        int pageNum = Integer.parseInt(request.getParameter("pageNum")); //댓글의 페이지번호
        int num = Integer.parseInt(request.getParameter("num")); //원글의 글번호
        
        final int PAGE_ROW_COUNT = 10;
        
        int startRowNum = 1+(pageNum-1)*PAGE_ROW_COUNT;
        int endRowNum = pageNum*PAGE_ROW_COUNT;
        
        CafeCommentDto commentDto = new CafeCommentDto();
        commentDto.setRef_group(num);
        commentDto.setStartRowNum(startRowNum);
        commentDto.setEndRowNum(endRowNum);
        
        List<CafeCommentDto> commentList = cafeCommentDao.getList(commentDto);
        int totalRow = cafeCommentDao.getCount(num);
        int totalPageCount = (int)Math.ceil(totalRow/(double)PAGE_ROW_COUNT);
        
        model.addAttribute("commentList", commentList);
        model.addAttribute("num", num);
        model.addAttribute("pageNum", pageNum);
    }

}
