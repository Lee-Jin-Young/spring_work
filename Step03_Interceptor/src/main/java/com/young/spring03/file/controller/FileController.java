package com.young.spring03.file.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.young.spring03.file.dto.FileDto;

/*
 *   [ spring mvc 파일 업로드 처리 ]
 *   
 *   1. pom.xml 에  commons-io, commons-fileupload 가 dependency 에 명시 되어 있어야 한다.
 *   2. servlet-context.xml 에  MultipartResolver bean 설정이 있어야한다.
 *   3. MultipartFile 객체를 컨트롤러에서 받아서 사용하면 된다.
 *       
 */

@Controller
public class FileController {
	@RequestMapping(method = RequestMethod.POST, value = "file/upload")
	public String upload(String title, MultipartFile myFile, HttpServletRequest request) {
		/*
		 * 입력한 제목은 title에 업로드 된 파일에 대한 정보는 myFile객체에 들어있다.
		 * myFile객체의 메소드를 활용하여 업로드처리에 필요한 정보를 얻을 수 있다.
		 * 
		 * 일반적인 웹서버 application은 클라이언트가 파일 업로드를 할때 운영체제의 임시폴더에 임시파일명으로 저장한다.
		 * 일정 시간 이후에 저장된 임시 파일은 자동으로 삭제되기 때문에 해당 파일을 사용하기 위해서는 원하는 위치로 이동해야한다.
		 */

		String orgFileName = myFile.getOriginalFilename();
		long fileSize = myFile.getSize();

		String realPath = request.getServletContext().getRealPath("/resources/upload");
		String filePath = realPath + File.separator; // 저장할 파일의 상세 경로
		
		File upload = new File(filePath);
		
		if (!upload.exists()) {
			upload.mkdir();
		}
		
		String saveFileName = System.currentTimeMillis() + orgFileName;
		
		try {
			myFile.transferTo(new File(filePath + saveFileName));
			System.out.println(filePath + saveFileName);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "file/upload";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/file/upload2")
	public String upload(FileDto dto, HttpServletRequest request) {
		MultipartFile myFile = dto.getMyFile();
		String orgFileName = myFile.getOriginalFilename();
		long fileSize = myFile.getSize();

		String realPath = request.getServletContext().getRealPath("/resources/upload");
		String filePath = realPath + File.separator; // 저장할 파일의 상세 경로
		
		File upload = new File(filePath);
		
		if (!upload.exists()) {
			upload.mkdir();
		}
		
		String saveFileName = System.currentTimeMillis() + orgFileName;
		
		try {
			myFile.transferTo(new File(filePath + saveFileName));
			System.out.println(filePath + saveFileName);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "file/upload";
	}
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/image/upload")
	public Map<String, Object> upload(MultipartFile image, HttpServletRequest request) {
		String orgFileName = image.getOriginalFilename();
		
		String realPath = request.getServletContext().getRealPath("/resources/upload");
		String filePath = realPath + File.separator;
		
		File upload=new File(filePath);
		if(!upload.exists()) {
			upload.mkdir();
		}

		String saveFileName = System.currentTimeMillis() + orgFileName;
		
		try {
			image.transferTo(new File(filePath + saveFileName));
			System.out.println(filePath + saveFileName);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Map<String, Object> map = new HashMap<>();
        map.put("imageUrl", "/resources/upload/" + saveFileName);
		
		return map;
	}

	@RequestMapping("/file/insertform")
	public String insertform() {
		return "file/insertform";
	}
}
