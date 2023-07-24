package com.example.boot07.android;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.boot07.cafe.dao.CafeDao;
import com.example.boot07.cafe.dto.CafeDto;

@RestController
public class AndroidController {   
   @GetMapping("/android/tweet")
   public String tweet(String msg) {
      System.out.println("안드로이드에서 전송된 문자열:"+msg);
      return "success!";
   }
   
   @GetMapping("/android/tweet2")
   public Map<String, Object> tweet2(String msg){
      System.out.println("안드로이드에서 전송된 문자열:"+msg);
      Map<String, Object> map=new HashMap<>();
      map.put("isSuccess", true);
      return map;
   }
   
   @GetMapping("/android/tweet3")
   public List<String> tweet3(String msg){
      System.out.println("안드로이드에서 전송된 문자열:"+msg);
      
      List<String> names=new ArrayList<String>();
      names.add("김구라");
      names.add("해골");
      names.add("원숭이");
      
      return names;
   }
   
   //테스트를 위해 CafeDao 주입받기
   @Autowired
   private CafeDao dao;
   
   @GetMapping("/android/list")
   public List<CafeDto> list() {
	   //샘플 데이터
	   CafeDto dto = new CafeDto();
	   dto.setStartRowNum(1);
	   dto.setEndRowNum(10);
	   
	   List<CafeDto> list = dao.getList(dto);
	   
	   return list;
   }
}






