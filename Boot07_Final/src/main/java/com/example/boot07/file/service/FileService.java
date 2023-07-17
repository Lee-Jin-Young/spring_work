package com.example.boot07.file.service;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;

import com.example.boot07.file.dto.FileDto;

public interface FileService {
    public void getList(HttpServletRequest request);
    public void saveFile(FileDto dto, Model model, HttpServletRequest request);
    public ResponseEntity<InputStreamResource> getFileDate(int num) throws UnsupportedEncodingException, FileNotFoundException;
    public void deleteFile(int num, HttpServletRequest request);
}
