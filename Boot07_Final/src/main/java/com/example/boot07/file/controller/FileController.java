package com.example.boot07.file.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/file/download")
    public String download(int num, Model model) {
        service.getFileData(num, model);
        return "fileDownView";
    }

    @GetMapping("/file/delete")
    public String delete(int num, Model model, HttpServletRequest request) {
        service.deleteFile(num, request);
        return ("redirect:/file/list");
    }
}
