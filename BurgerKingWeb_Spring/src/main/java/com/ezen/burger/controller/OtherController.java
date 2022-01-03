package com.ezen.burger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ezen.burger.service.OtherService;

@Controller
public class OtherController {
	@Autowired
	OtherService os;
	
	@RequestMapping(value="/")

	public String index() {
		return "redirect:/main";
	}
	
	@RequestMapping(value="/main")
	public String main() {
		return "main/main";
	}
	
	@RequestMapping(value="/admin")
	public String admin() {
		return "admin/admin";
	}
	
}


