package com.example.boot06;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CafeController {
	@GetMapping("/cafe/insert_form")
	public String insertForm() {
		return "cafe/insert_form";
	}
}
