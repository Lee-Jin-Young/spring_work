package com.young.spring04.cafe.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.young.spring04.cafe.dto.CafeCommentDto;
import com.young.spring04.cafe.dto.CafeDto;
import com.young.spring04.cafe.service.CafeService;

@Controller
public class CafeController {
    @Autowired
    private CafeService service;
    
    // 글 목록 요청 처리
    @RequestMapping("/cafe/list")
    public String list(HttpServletRequest request) {
        service.getList(request);
        
        return "cafe/list";
    }
    
    // 글 작성 폼 요청 처리
    @RequestMapping("/cafe/insertform")
    public String insertform() {
        return "/cafe/insertform";
    }
    
    // 글 작성 요청 처리
    @RequestMapping("/cafe/insert")
    public String insert(CafeDto dto, HttpSession session) {
        String writer = (String)session.getAttribute("id");
        dto.setWriter(writer);
        service.saveContent(dto);
        
        return "cafe/insert";
    }
    
    // 글 자세히 보기 요청 처리
    @RequestMapping("/cafe/detail")
    public ModelAndView detail(HttpServletRequest request, ModelAndView mView) {
        service.getDetail(request);
        
        mView.setViewName("cafe/detail");
        return mView;
    }
    
    // 글 수정 폼 요청 처리
    @RequestMapping("/cafe/updateform")
    public String updateform(HttpServletRequest request) {
        service.getData(request);
        
        return "/cafe/updateform";
    }
    
    // 글 수정 요청 처리
    @RequestMapping("/cafe/update")
    public String update(CafeDto dto) {
        service.updateContent(dto);
        
        return "cafe/update";
    }
    
    // 글 삭제 요청 처리
    @RequestMapping("/cafe/delete")
    public String delete(int num, HttpServletRequest request) {
        service.deleteContent(num, request);
        
        return "redirect:/cafe/list";
    }
    
    /*
     * 댓글
     */
    // 새로운 댓글 저장 요청 처리
    @RequestMapping("/cafe/comment_insert")
    public String commentInsert(HttpServletRequest request, int ref_group) {
        service.savecomment(request);
        return "redirect:/cafe/detail?num="+ref_group;
    }
    
    // 댓글 더보기 요청 처리
    @RequestMapping("/cafe/ajax_comment_list")
    public String commentList(HttpServletRequest request) {
        //테스트 위해 시간 지연
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        service.moreCommentList(request);
        
        return "cafe/ajax_comment_list";
    }
    
    // 댓글 삭제 요청 처리
    @ResponseBody
    @RequestMapping("/cafe/comment_list")
    public Map<String, Object> commentDelete(HttpServletRequest request) {
        service.deleteComment(request);
        
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("isSuccess", true);
        
        return map;
        }
    
    // 댓글 수정 요청 처리(JSON응답)
    @ResponseBody
    @RequestMapping("/cafe/comment_update")
    public Map<String, Object> commentUpdate(CafeCommentDto dto, HttpServletRequest request) {
        service.updateComment(dto);
        
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("isSuccess", true);
        
        return map;
    }
}
