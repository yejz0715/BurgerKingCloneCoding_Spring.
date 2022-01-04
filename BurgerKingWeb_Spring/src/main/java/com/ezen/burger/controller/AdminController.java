package com.ezen.burger.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
		
		if(avo == null) {
			model.addAttribute("message", "id가 없습니다.");
			return "admin/adminLogin";
		}else if(avo.getPwd() == null) {
			model.addAttribute("message", "관리자에게 문의하세요");
			return "admin/adminLogin";
		}else if(!avo.getPwd().equals(adminvo.getPwd())) {
			model.addAttribute("message", "비밀번호가 맞지 않습니다.");
			return "admin/adminLogin";
		}else if(avo.getPwd().equals(adminvo.getPwd())) {
			HttpSession session = request.getSession();
			session.setAttribute("loginAdmin", avo);
			return "admin/main";
		}else {
			model.addAttribute("message", "원인미상의 오류로 로그인 불가");
			return "admin/adminLogin";
		}
	}
	@RequestMapping("/adminLogout")
	public String logout(HttpServletRequest request) {
		HttpSession session= request.getSession();
		session.removeAttribute("loginAdmin");
		return "redirect:/admin";
	}
}
