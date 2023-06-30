package com.young.spring04.cafe.service;

import javax.servlet.http.HttpServletRequest;

import com.young.spring04.cafe.dto.CafeCommentDto;
import com.young.spring04.cafe.dto.CafeDto;

public interface CafeService {
	public void getList(HttpServletRequest request);
	public void getDetail(HttpServletRequest request);
	public void saveContent(CafeDto dto);
	public void updateContent(CafeDto dto);
	public void deleteContent(int num, HttpServletRequest request);
	public void getData(HttpServletRequest request);
	
	public void savecomment(HttpServletRequest request); //댓글 저장
	public void deleteComment(HttpServletRequest request); //댓글 삭제
	public void updateComment(CafeCommentDto dto); //댓글 수정
	public void moreCommentList(HttpServletRequest request); //댓글 더보기 기능
}
