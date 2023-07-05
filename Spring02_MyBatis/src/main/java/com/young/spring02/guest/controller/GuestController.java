package com.young.spring02.guest.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.young.spring02.guest.dto.GuestDto;
import com.young.spring02.guest.service.GuestService;

@Controller
public class GuestController {
    @Autowired
    private GuestService service;

    // 글 추가 요청 처리
    //public void insert(GuestDto dto);
    @RequestMapping("/guest/insert")
    public String insert(GuestDto dto) {
        service.addGuest(dto);
        return "guest/insert";
    }

    // 글 추가 폼 요청 처리
    @RequestMapping("/guest/insertform")
    public String insertform() {
        return "guest/insertform";
    }
    
    // 글 수정 요청 처리
    //public void update(GuestDto dto);
    @RequestMapping(method = RequestMethod.POST, value = "/guest/update")
    public String update(GuestDto dto) {
        service.updateGuest(dto);
        return "guest/update";
    }
    
    // 글 수정 폼 요청 처리
    @RequestMapping("/guest/updateform")
    public ModelAndView updateform(ModelAndView mView, int num) {
        service.getGuestInfo(mView, num);
        mView.setViewName("guest/updateform");
        return mView;
    }
    
    // 글 삭제 요청 처리
    // public void delete(int num);
    @RequestMapping("/guest/delete")
    public String delete(GuestDto dto) {
        service.deleteGuest(dto);
        return "redirect:/guest/list";
    }
     
    // 글 정보 보기 요청 처리
    //public GuestDto getData(int num);
    @RequestMapping("/guest/data")
    public String data(int num) {
        ModelAndView mView = null;
        service.getGuestInfo(mView, num);
        return "dto";
    }
    
    //public List<GuestDto> getList();
    // 글 목록 보기 요청 처리
    @RequestMapping("/guest/list")
    public String list(HttpServletRequest request) {
        ModelAndView mView = null;
        service.getGuestList(mView);
        return "guest/list";
    }
}
