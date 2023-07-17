package com.example.boot07.file.controller;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.boot07.file.dto.FileDto;
import com.example.boot07.file.service.FileService;

@Controller
public class FileController {
    @Autowired
    private FileService service;
    
    @GetMapping("/file/list")
    public String list(HttpServletRequest request) {
        service.getList(request);
        return "file/list";
    } 

    @GetMapping("/file/upload_form")
    public String uploadForm() {

        return "file/upload_form";
    }

    //파일 업로드 요청처리 
    @PostMapping("/file/upload")
    public String upload(FileDto dto, Model model, HttpServletRequest request) {
        service.saveFile(dto, model, request);
        return "file/upload";
    }

    // 다운로드할 파일의 번호가 요청파라미터로 전달
    @GetMapping("/file/download")
    public ResponseEntity<InputStreamResource> download(int num) throws UnsupportedEncodingException, FileNotFoundException {
        return service.getFileDate(num);
    }

    @GetMapping("/file/delete")
    public String delete(int num, Model model, HttpServletRequest request) {
        service.deleteFile(num, request);
        return ("redirect:/file/list");
    }
}
