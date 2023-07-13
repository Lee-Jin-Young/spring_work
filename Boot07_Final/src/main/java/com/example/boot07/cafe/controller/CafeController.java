package com.example.boot07.cafe.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.boot07.cafe.dto.CafeCommentDto;
import com.example.boot07.cafe.dto.CafeDto;
import com.example.boot07.cafe.service.CafeService;

@Controller
public class CafeController {
    @Autowired
    private CafeService service;
    
    // 글 목록 요청 처리
    @GetMapping("/cafe/list")
    public String list(Model model) {
        service.getList(model);
        
        return "cafe/list";
    }
    
    // 글 작성 폼 요청 처리
    @GetMapping("/cafe/insertform")
    public String insertform() {
        return "/cafe/insertform";
    }
    
    // 글 작성 요청 처리
    @PostMapping("/cafe/insert")
    public String insert(CafeDto dto, HttpSession session) {
        String writer = (String)session.getAttribute("id");
        dto.setWriter(writer);
        service.saveContent(dto);
        
        return "cafe/insert";
    }
    
    // 글 자세히 보기 요청 처리
    @GetMapping("/cafe/detail")
    public String detail(Model model) {
        service.getDetail(model);
        
        return "cafe/detail";
    }
    
    // 글 수정 폼 요청 처리
    @GetMapping("/cafe/updateform")
    public String updateform(Model model) {
        service.getData(model);
        
        return "/cafe/updateform";
    }
    
    // 글 수정 요청 처리
    @PostMapping("/cafe/update")
    public String update(CafeDto dto) {
        service.updateContent(dto);
        
        return "cafe/update";
    }
    
    // 글 삭제 요청 처리
    @GetMapping("/cafe/delete")
    public String delete(int num, HttpSession session) {
        service.deleteContent(num, session);
        
        return "redirect:/cafe/list";
    }
    
    /*
     * 댓글
     */
    // 새로운 댓글 저장 요청 처리
    @PostMapping("/cafe/comment_insert")
    public String commentInsert(Model model, int ref_group) {
        service.savecomment(model);
        return "redirect:/cafe/detail?num="+ref_group;
    }
    
    // 댓글 더보기 요청 처리
    @GetMapping("/cafe/ajax_comment_list")
    public String commentList(Model model) {
        //테스트 위해 시간 지연
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        service.moreCommentList(model);
        
        return "cafe/ajax_comment_list";
    }
    
    // 댓글 삭제 요청 처리
    @ResponseBody
    @PostMapping("/cafe/comment_delete")
    public Map<String, Object> commentDelete(Model model) {
        service.deleteComment(model);
        
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("isSuccess", true);
        
        return map;
        }
    
    // 댓글 수정 요청 처리(JSON응답)
    @ResponseBody
    @PostMapping("/cafe/comment_update")
    public Map<String, Object> commentUpdate(CafeCommentDto dto, Model model) {
        service.updateComment(dto);
        
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("isSuccess", true);
        
        return map;
    }
}
