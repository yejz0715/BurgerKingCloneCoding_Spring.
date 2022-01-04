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
	
	@RequestMapping(value="/faqList1")
	public String faqList1() {
		return "ServiceCenter/faqList1";

	}
	
	@RequestMapping(value="/brandStroyForm")
	public String brandStroyForm() {
		return "brand/brandStory";
	}

	@RequestMapping(value="/terms")
	public String terms() {
		return "footer/terms";
	}
	
	@RequestMapping(value="/privacy")
	public String privacy() {
		return "footer/privacy";
	}
	
	@RequestMapping(value="/videoPolicy")
	public String videoPolicy() {
		return "footer/videoPolicy";
	}
	
	@RequestMapping(value="/legal")
	public String legal() {
		return "footer/legal";
	}
}


