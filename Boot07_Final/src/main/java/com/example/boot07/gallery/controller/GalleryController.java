package com.example.boot07.gallery.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.boot07.gallery.dto.GalleryDto;
import com.example.boot07.gallery.service.GalleryService;

@Controller
public class GalleryController {
    @Autowired
    private GalleryService service;
    
    @Value("${file.location}")
    private String fileLocation;
    
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
    
    // jpg, png, gif 이미지 데이터를 응답할수 있도록 produces 에 배열로 전달한다.
 	@GetMapping(value = "/gallery/images/{imageName}", produces = { MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_GIF_VALUE })
 	@ResponseBody
 	public byte[] getImage(@PathVariable("imageName") String imageName) throws IOException {
 		// imageName 에는 응답해줄 이미지의 이름이 들어 있다.

 		// 읽어들일 파일의 경로
 		// C:/folder/imageName.png 형식의 경로
 		String absolutePath = fileLocation + File.separator + imageName;
 		// 파일에서 읽어들일 InputStream
 		InputStream is = new FileInputStream(absolutePath);
 		// fileLocation 필드에는 파일이 저장되어 있는 서버의 파일 시스템상에서의 위치가 들어 있다.
 		return IOUtils.toByteArray(is);
 	}
}
