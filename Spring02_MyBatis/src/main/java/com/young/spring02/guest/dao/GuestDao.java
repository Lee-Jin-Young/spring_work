package com.young.spring02.guest.dao;

import java.util.List;

import com.young.spring02.guest.dto.GuestDto;

public interface GuestDao {
	public void insert(GuestDto dto);
	public void update(GuestDto dto);
	public void delete(GuestDto dto);
	public GuestDto getData(int num);
	public List<GuestDto> getList();
}
