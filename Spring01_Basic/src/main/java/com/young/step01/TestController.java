package com.young.step01;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.young.step01.member.MemberDto;

@Controller
public class TestController {
	@ResponseBody //return값의 변형을 막음
	@RequestMapping("/test/json1")
	public String json1() {
		return "{\"num\":1, \"name\":\"hello\", \"addr\":\"seoul\"}";
	}
	
	@ResponseBody
	@RequestMapping("/test/json2")
	public MemberDto json2() {
		MemberDto dto = new MemberDto();
		dto.setNum(2);
		dto.setName("world");
		dto.setAddr("busan");
		
		return dto;
	}
	
	@ResponseBody
	@RequestMapping("/test/json3")
	public Map<String, Object> json3() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("num",3);
		map.put("name", "hi");
		map.put("addr", "a");
		
		return map;
	}
	
	@ResponseBody
	@RequestMapping("/test/json4")
	public List<String> json4() {
		List<String> names = new ArrayList<String>();
		names.add("가");
		names.add("나");
		names.add("다");
		
		return names;
	}
	
	@ResponseBody
	@RequestMapping("/test/json5")
	public List<MemberDto> json5() {
		List<MemberDto> list = new ArrayList<MemberDto>();
		list.add(new MemberDto(1,"가","a"));
		list.add(new MemberDto(2,"나","b"));
		list.add(new MemberDto(3,"다","c"));
		
		return list;
	}
	
	@ResponseBody
	@RequestMapping("/test/json6")
	public List<Map<String, Object>> json6() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("num",3);
		map1.put("name", "hi");
		map1.put("addr", "a");
		
		list.add(map1);
		list.add(map1);
		list.add(map1);
		
		return list;
	}
}
