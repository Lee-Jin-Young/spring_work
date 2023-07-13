package com.example.boot07.file.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.example.boot07.file.dto.FileDto;

public interface FileService {
    public void getList(HttpServletRequest request);
    public void saveFile(FileDto dto, Model model, HttpServletRequest request);
    public void getFileData(int num, Model model);
    public void deleteFile(int num, HttpServletRequest request);
}
