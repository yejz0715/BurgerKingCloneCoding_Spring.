package com.ezen.burger.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
	
	@RequestMapping(value="faqListForm")
	public ModelAndView  faqListForm(Model model, @RequestParam("fnum") String fnum , HttpServletRequest request) {
		ModelAndView mav = new ModelAndView ();
		HttpSession session = request.getSession();
		mav.setViewName("ServiceCenter/faqList" + fnum);
		return mav;
	}
	
	@RequestMapping(value="/brandStroyForm")
	public String brandStroyForm() {
		return "brand/brandStory";
	}

}


