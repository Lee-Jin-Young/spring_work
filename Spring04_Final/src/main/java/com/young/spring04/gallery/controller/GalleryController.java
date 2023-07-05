package com.young.spring04.gallery.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.young.spring04.gallery.dto.GalleryDto;
import com.young.spring04.gallery.service.GalleryService;

@Controller
public class GalleryController {
    @Autowired
    private GalleryService service;
    
    // 갤러리 리스트 요청 처리
    @RequestMapping("/gallery/list")
    public String list(HttpServletRequest request) {
        service.getList(request);
        
        return "gallery/list";
    }
    
    // 업로드 폼 요청 처리
    @RequestMapping("/gallery/upload_form")
    public String uploadForm(GalleryDto dto, HttpServletRequest request) {
        return "gallery/upload_form";
    }
    // 업로드 폼2 요청 처리
    @RequestMapping("/gallery/upload_form_drag")
    public String uploadFormDrag(GalleryDto dto, HttpServletRequest request) {
        return "gallery/upload_form_drag";
    }
    // 업로드 폼3 요청 처리
    @RequestMapping("/gallery/upload_form_ajax")
    public String uploadFormAjax() {
        return "gallery/upload_form_ajax";
    }
    
    // ajax 업로드 요청 처리
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/gallery/ajax_upload")
    public Map<String, Object> imageUpload(GalleryDto dto, HttpServletRequest request) {
        service.saveImage(dto, request);
        
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("isSuccess", true);
        return map;
    }
    
    @RequestMapping("gallery/upload")
    public String upload(GalleryDto dto, HttpServletRequest request) {
        service.saveImage(dto, request);
        return "gallery/upload";
    }
    
    // 자세히 보기 요청 처리
    @RequestMapping("/gallery/detail")
    public ModelAndView detail(ModelAndView mView, int num) {
        service.getDetail(mView, num);
        mView.setViewName("gallery/detail");
        return mView;
    }
}
