package com.young.spring04.gallery.service;

import java.io.File;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.young.spring04.gallery.dao.GalleryDao;
import com.young.spring04.gallery.dto.GalleryDto;

@Service
public class GalleryServiceImpl implements GalleryService {
    @Autowired
    private GalleryDao dao;

    @Override
    public void getList(HttpServletRequest request) {
        final int PAGE_ROW_COUNT = 8;
        final int PAGE_DISPLAY_COUNT = 5;

        int pageNum = 1;

        String strPageNum = request.getParameter("pageNum");
        if (strPageNum != null) {
            pageNum = Integer.parseInt(strPageNum);
        }

        int startRowNum = 1 + (pageNum - 1) * PAGE_ROW_COUNT;
        int endRowNum = pageNum * PAGE_ROW_COUNT;
        
        GalleryDto dto = new GalleryDto();
        dto.setStartRowNum(startRowNum);
        dto.setEndRowNum(endRowNum);

        List<GalleryDto> list = dao.getList(dto);

        int totalRow = dao.getCount();
        int startPageNum = 1 + ((pageNum - 1) / PAGE_DISPLAY_COUNT) * PAGE_DISPLAY_COUNT;
        int endPageNum = startPageNum + PAGE_DISPLAY_COUNT - 1;

        int totalPageCount = (int) Math.ceil(totalRow / (double) PAGE_ROW_COUNT);
        if (endPageNum > totalPageCount) {
            endPageNum = totalPageCount;
        }

        // 응답에 필요한 데이터를 view page 에 전달하기 위해 request scope 에 담는다
        request.setAttribute("list", list);
        request.setAttribute("pageNum", pageNum);
        request.setAttribute("startPageNum", startPageNum);
        request.setAttribute("endPageNum", endPageNum);
        request.setAttribute("totalPageCount", totalPageCount);
    }

    // 이미지 업로드 & db저장
    @Override
    public void saveImage(GalleryDto dto, HttpServletRequest request) {
        MultipartFile image = dto.getImage();
        
        String orgFileName=image.getOriginalFilename();
        long fileSize = image.getSize();
        
        String realPath=request.getServletContext().getRealPath("/resources/upload");
        String filePath = realPath + File.separator;
        
        String saveFileName = System.currentTimeMillis() + orgFileName;
        
        // upload 폴더가 없을 경우
        File upload=new File(filePath);
        if(!upload.exists()) {
           upload.mkdir();
        }
        
        try {
           image.transferTo(new File(filePath+saveFileName));
        } catch(Exception e) {
           e.printStackTrace();
        }
        
        String id = (String)request.getSession().getAttribute("id");
        dto.setWriter(id);
        dto.setImagePath("/resources/upload/"+saveFileName);
        
        dao.insert(dto);
    }
    
    //이미지 ajax업로드
    @Override
    public Map<String, Object> uploadAjaxImage(GalleryDto dto, HttpServletRequest request) {
        MultipartFile image = dto.getImage();
        
        String orgFileName=image.getOriginalFilename();
        long fileSize = image.getSize();
        
        String realPath=request.getServletContext().getRealPath("/resources/upload");
        String filePath = realPath + File.separator;
        
        String saveFileName = System.currentTimeMillis() + orgFileName;
        
        // upload 폴더가 없을 경우
        File upload=new File(filePath);
        if(!upload.exists()) {
           upload.mkdir();
        }
        
        try {
           image.transferTo(new File(filePath+saveFileName));
        }catch(Exception e) {
           e.printStackTrace();
        }
        
        Map<String, Object> map=new HashMap<String, Object>();
        map.put("imagePath", "/resources/upload/"+saveFileName);
        
        return map;
    }

    @Override
    public void insert(GalleryDto dto, HttpServletRequest request) {
        dto.setWriter((String) request.getSession().getAttribute("id"));

        dao.insert(dto);
    }

    @Override
    public void getDetail(ModelAndView mView, int num) {
        GalleryDto dto = dao.getData(num);

        mView.addObject("dto", dto);
    }

}
