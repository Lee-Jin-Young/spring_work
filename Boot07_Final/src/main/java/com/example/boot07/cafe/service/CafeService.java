package com.example.boot07.cafe.service;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.example.boot07.cafe.dto.CafeCommentDto;
import com.example.boot07.cafe.dto.CafeDto;

public interface CafeService {
    public void getList(Model model);
    public void getDetail(Model model);
    public void saveContent(CafeDto dto);
    public void updateContent(CafeDto dto);
    public void deleteContent(int num, HttpSession session);
    public void getData(Model model);
    
    public void savecomment(Model model); //댓글 저장
    public void deleteComment(Model model); //댓글 삭제
    public void updateComment(CafeCommentDto dto); //댓글 수정
    public void moreCommentList(Model model); //댓글 더보기 기능
}
