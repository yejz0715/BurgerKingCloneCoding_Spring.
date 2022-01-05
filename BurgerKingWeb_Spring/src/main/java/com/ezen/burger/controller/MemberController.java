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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.burger.dto.GuestVO;
import com.ezen.burger.dto.MemberVO;
import com.ezen.burger.service.MemberService;

@Controller
public class MemberController {
	@Autowired
	MemberService ms;
	
	// 로그인 페이지로 이동
	@RequestMapping(value="/loginForm")
	public String loginForm() {
		return "member/loginForm";
	}
	
	// 로그인
	@RequestMapping(value="login", method = RequestMethod.POST)
	public String login(@ModelAttribute("dto") @Valid MemberVO membervo, 
			BindingResult result, Model model, HttpServletRequest request) {
		
		// 로그인시 에러가 있을 때
		if(result.hasErrors()) { 
			// 해당 에러가 id와 pw 관련이라면 로그인 창으로 돌아간다.
			if(result.getFieldError("id") != null) {
				model.addAttribute("message", result.getFieldError("id").getDefaultMessage());
				return "member/loginForm";
			}else if(result.getFieldError("pwd") != null) {
				model.addAttribute("message", result.getFieldError("pwd").getDefaultMessage());
				return "member/loginForm";
			}
		}	
		
		// 사용자가 입력한 아이디 값을 검색
		MemberVO mvo = ms.getMember(membervo.getId());
		if(mvo == null) { // 해당 ID를 가진 회원이 없을경우
			model.addAttribute("message", "ID가 없습니다.");
			return "member/loginForm";
		}else if(mvo.getPwd() == null) { // 회원은 있지만 비밀번호에 문제가 있을 경우
			model.addAttribute("message", "관리자에게 문의하세요.");
			return "member/loginForm";
		}else if(!mvo.getPwd().equals(membervo.getPwd())) { // 입력한 패스워드가 일치하지 않을 경우
			model.addAttribute("message", "비밀번호가 맞지 않습니다..");
			return "member/loginForm";
		}else if(mvo.getPwd().equals(membervo.getPwd())) { // 정상 로그인
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", mvo);
			return "redirect:/";
		}else { // 기타 원인을 알 수 없는 오류
			model.addAttribute("message", "알수없는 이유로 로그인 실패.");
			return "member/loginForm";
		}
	}
	
	// 로그아웃
	@RequestMapping(value="/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		return "redirect:/";
	}
	
	// 아이디 찾기 페이지 이동
	@RequestMapping(value="/findIdForm")
	public String findIdForm() {
		return "member/findIdForm";
	}
	
	// 아이디 찾기
	@RequestMapping(value="/findId")
	public ModelAndView findId(@ModelAttribute("dto") @Valid MemberVO membervo, 
			BindingResult result, Model model, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		
		if(result.hasErrors()) { 
			if(result.getFieldError("name") != null) {
				mav.addObject("message", result.getFieldError("name").getDefaultMessage());
				mav.setViewName("member/findIdForm");
			}else if(result.getFieldError("phone") != null) {
				mav.addObject("message", result.getFieldError("phone").getDefaultMessage());
				mav.setViewName("member/findIdForm");
			}
		}	

		MemberVO mvo = ms.findMember(membervo.getName(), membervo.getPhone());
		if(mvo == null) {
			mav.addObject("message", "해당 정보를 가진 회원이 없습니다.");
			mav.setViewName("member/findIdForm");
		}else{
			mav.addObject("memberVO", mvo);
			mav.setViewName("member/showIdForm");
		}
		return mav;
	}
	
	// 비밀번호 찾기 페이지로 이동
	@RequestMapping(value="/findPwdForm")
	public String findPwdForm(@RequestParam(value="id", required = false) String id,
			@RequestParam(value="name", required = false) String name,
			HttpServletRequest request) {
		request.setAttribute("name", name);
		request.setAttribute("id", id);
		return "member/findPwdForm";
	}
	
	// 비밀번호 찾기
	@RequestMapping(value="/findPwd")
	public ModelAndView findPwd(@ModelAttribute("dto") @Valid MemberVO membervo, 
			BindingResult result, Model model, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		
		if(result.hasErrors()) { 
			if(result.getFieldError("name") != null) {
				mav.addObject("message", result.getFieldError("name").getDefaultMessage());
				mav.setViewName("member/findPwdForm");
			}else if(result.getFieldError("id") != null) {
				mav.addObject("message", result.getFieldError("id").getDefaultMessage());
				mav.setViewName("member/findPwdForm");
			}
		}	

		MemberVO mvo = ms.findPwd(membervo.getName(), membervo.getId());
		if(mvo == null) {
			mav.addObject("message", "해당 정보를 가진 회원이 없습니다.");
			mav.setViewName("member/findIdForm");
		}else{
			mav.addObject("memberVO", mvo);
			mav.setViewName("member/sendPwdForm");
		}
		return mav;
	}
	
	@RequestMapping(value="updatePwd")
	public ModelAndView updatePwd(@RequestParam("pwd") String pwd,
			@RequestParam("mseq") String mseq) {
		ModelAndView mav = new ModelAndView();
		ms.updatePwd(mseq, pwd);
		mav.setViewName("redirect:/loginForm");
		return mav;
	}
	
	@RequestMapping(value="/guestLoginForm")
	public String guestLoginForm() {
		return "member/guestLoginForm";
	}
	
	@RequestMapping(value="/guestLogin")
	public ModelAndView guestLogin(HttpServletRequest request,
			@RequestParam("name") String name, @RequestParam("phone") String phone,
			@RequestParam("pwd") String pwd) {
		ModelAndView mav = new ModelAndView();
		GuestVO gvo = ms.guestSessionLogin(name, phone, pwd);
		
		HttpSession session = request.getSession();
		session.setAttribute("loginUser", gvo);
		
		mav.setViewName("redirect:/");
		return mav;
	}
}	