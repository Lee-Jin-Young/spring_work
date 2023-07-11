package com.example.boot03;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {
	@PostMapping("/member/insert")
	public Map<String, Object> insert(String name, String addr) {
		System.out.println(name+" | "+addr);
		Map<String, Object> map = new HashMap<>();
		map.put("name", name);
		map.put("addr", addr);
		return map;
	}
}
