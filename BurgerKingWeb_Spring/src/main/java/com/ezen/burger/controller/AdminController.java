package com.ezen.burger.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ezen.burger.dto.AdminVO;
import com.ezen.burger.service.AdminService;

@Controller
public class AdminController {
	@Autowired
	AdminService as;
	
	@RequestMapping(value="/adminLogin", method=RequestMethod.POST)
	public String adminLogin(@ModelAttribute("dto") @Valid AdminVO adminvo,
			BindingResult result, HttpServletRequest request, Model model) {
		
		if(result.getFieldError("id")!=null) {
			model.addAttribute("message", result.getFieldError("id").getDefaultMessage());
			return "admin/adminLogin";
		}else if(result.getFieldError("pwd")!=null) {
			model.addAttribute("message", result.getFieldError("pwd").getDefaultMessage());
			return "admin/adminLogin";
		}
		
		AdminVO avo = as.adminCheck(adminvo.getId());
		
		return "admin/main";
	}
}
