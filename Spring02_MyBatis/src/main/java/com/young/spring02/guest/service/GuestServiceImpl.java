package com.young.spring02.guest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.young.spring02.guest.dao.GuestDao;
import com.young.spring02.guest.dto.GuestDto;

//component scan이 가능하도록 @를 붙임
@Service
public class GuestServiceImpl implements GuestService{
	@Autowired
	private GuestDao dao;

	@Override
	public void addGuest(GuestDto dto) {
		dao.insert(dto);
	}

	@Override
	public void updateGuest(GuestDto dto) {
		dao.update(dto);
	}

	@Override
	public void deleteGuest(GuestDto dto) {
		dao.delete(dto);
	}

	@Override
	public void getGuestInfo(ModelAndView mView, int num) {
		GuestDto dto = dao.getData(num);
		mView.addObject("dto", dto);
	}

	@Override
	public void getGuestList(ModelAndView mView) {
		List<GuestDto> list = dao.getList();
		mView.addObject("list", list);
	}
	
}
