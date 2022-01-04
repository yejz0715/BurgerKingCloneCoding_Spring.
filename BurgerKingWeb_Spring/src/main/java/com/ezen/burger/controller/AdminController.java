package com.ezen.burger.controller;

import java.util.ArrayList;

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
import org.springframework.web.bind.annotation.RequestParam;

import com.ezen.burger.dto.AdminVO;
import com.ezen.burger.dto.EventVO;
import com.ezen.burger.dto.MemberVO;
import com.ezen.burger.dto.Paging;
import com.ezen.burger.service.AdminService;
import com.ezen.burger.service.MemberService;

@Controller
public class AdminController {
	@Autowired
	AdminService as;
	
	@Autowired
	MemberService ms;
	
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
	
	@RequestMapping("adminMemberList")
	public String adminMemberList(HttpServletRequest request, Model model) {
		HttpSession session= request.getSession();
		if( session.getAttribute("loginAdmin") == null) {
			return "admin/adminLogin";
		}
		else {						
			int page = 1;
			if(request.getParameter("page") != null){ 
				page = Integer.parseInt(request.getParameter("page")); 
				session.setAttribute("page", page); 
			}else if(session.getAttribute("page") != null) { 
				page = (int)session.getAttribute("page"); 
			}else { 
				page = 1;
				session.removeAttribute("page"); 
			}
			
			String key = "";
			if(request.getParameter("key") != null) { 
				key = request.getParameter("key"); session.setAttribute("key", key); 
			}else if(session.getAttribute("key") != null) {
				key = (String)session.getAttribute("key");
			}else { 
				session.removeAttribute("key");
				key = "";
			}
			
			
			Paging paging = new Paging();
			paging.setPage(page);
			
			int count = as.getAllCount("member", "name", key);
			paging.setTotalCount(count);
			paging.paging();
			
			ArrayList<MemberVO> memberList = as.listMember(paging, key);
			
			model.addAttribute("memberList",memberList);
			model.addAttribute("paging",paging);
			model.addAttribute("key",key);
		}
		return "admin/member/memberList";
	}
	

	
	@RequestMapping("/adminEventList")
	public String adminEventList(HttpServletRequest request, Model model) {
		HttpSession session= request.getSession();
		if( session.getAttribute("loginAdmin") == null) {
			return "admin/adminLogin";
		}
		else {		
			int page = 1;
			if(request.getParameter("page") != null){ 
				page = Integer.parseInt(request.getParameter("page")); 
				session.setAttribute("page", page); 
			}else if(session.getAttribute("page") != null) { 
				page = (int)session.getAttribute("page"); 
			}else { 
				page = 1;
				session.removeAttribute("page"); 
			}
			
			String key = "";
			if(request.getParameter("key") != null) { 
				key = request.getParameter("key"); session.setAttribute("key", key); 
			}else if(session.getAttribute("key") != null) {
				key = (String)session.getAttribute("key");
			}else { 
				session.removeAttribute("key");
				key = "";
			}	
			
			Paging paging = new Paging();
			paging.setPage(page);			
			int count = as.getAllCount("event", "subject", key);
			paging.setTotalCount(count);	
		paging.paging();
		
		ArrayList<EventVO> list = as.listEvent(paging, key);
		
		model.addAttribute("eventList",list);
		model.addAttribute("paging",paging);
		model.addAttribute("key",key);
	return "admin/event/eventList";
	 }	
}
	


	@RequestMapping(value="/adminMemberDelete", method=RequestMethod.POST)

	public String adminMemberDelete(@RequestParam("mseq") int [] mseqArr) {
		for(int mseq:mseqArr)
			ms.adminMemberDelete(mseq);
		return "redirect:/memberList";

	}
}
