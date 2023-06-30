package com.young.spring04.cafe.dao;

import java.util.List;

import com.young.spring04.cafe.dto.CafeCommentDto;

public interface CafeCommentDao {
	public List<CafeCommentDto> getList(CafeCommentDto dto);
	public void delete(int num);
	public void insert(CafeCommentDto dto);
	public int getSequence();
	public void update(CafeCommentDto dto);
	public CafeCommentDto getData(int num);
	public int getCount(int ref_group);
}
