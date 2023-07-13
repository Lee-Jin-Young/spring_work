package com.example.boot07.gallery.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.boot07.gallery.dto.GalleryDto;
import com.example.boot07.gallery.service.GalleryService;

@Controller
public class GalleryController {
    @Autowired
    private GalleryService service;
    
    // 갤러리 리스트 요청 처리
    @GetMapping("/gallery/list")
    public String list(HttpServletRequest request) {
        service.getList(request);
        
        return "gallery/list";
    }
    
    // 업로드 폼 요청 처리
    @GetMapping("/gallery/upload_form")
    public String uploadForm(GalleryDto dto, HttpServletRequest request) {
        return "gallery/upload_form";
    }
    // 업로드 폼2 요청 처리
    @GetMapping("/gallery/upload_form_drag")
    public String uploadFormDrag(GalleryDto dto, HttpServletRequest request) {
        return "gallery/upload_form_drag";
    }
    // 업로드 폼3 요청 처리
    @GetMapping("/gallery/upload_form_ajax")
    public String uploadFormAjax() {
        return "gallery/upload_form_ajax";
    }
    
    // ajax 업로드 요청 처리
    @ResponseBody
    @PostMapping("/gallery/ajax_upload")
    public Map<String, Object> imageUpload(GalleryDto dto, HttpServletRequest request) {
        service.saveImage(dto, request);
        
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("isSuccess", true);
        return map;
    }
    
    // 업로드 요청 처리
    @PostMapping("/gallery/upload")
    public String upload(GalleryDto dto, HttpServletRequest request) {
        service.saveImage(dto, request);
        return "gallery/upload";
    }
    
    // 자세히 보기 요청 처리
    @GetMapping("/gallery/detail")
    public String detail(Model model, int num) {
        service.getDetail(model, num);
        return "gallery/detail";
    }
}
