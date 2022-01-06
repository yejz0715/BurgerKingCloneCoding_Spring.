package com.ezen.burger.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.burger.service.OtherService;
import com.ezen.burger.service.QnaService;

@Controller
public class OtherController {
	@Autowired
	OtherService os;
	
	@Autowired
	QnaService qs;	
	
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
		return "admin/adminLogin";
	}
	
	@RequestMapping(value="/faqList1")
	public String faqList1() {
		return "ServiceCenter/faqList1";
	}
	
	
	// 고객센터 FAQ
	@RequestMapping(value="faqListForm")
	public ModelAndView  faqListForm(Model model, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView ();
		String fnum = request.getParameter("fnum");
		mav.setViewName("ServiceCenter/faqList" + fnum);
		return mav;
	}
	
	
	// 고객센터 앱이용안내
	@RequestMapping(value="appGuideForm")
	public String appGuideForm(Model model, HttpServletRequest request) {
		return "ServiceCenter/appGuide";
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
	
	@RequestMapping(value="/deliveryUseForm")
	public String deliveryUseForm() {
		return "ServiceCenter/deliveryuse";
	} 
}


