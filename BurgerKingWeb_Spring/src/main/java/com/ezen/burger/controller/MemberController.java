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
import org.springframework.web.servlet.ModelAndView;

import com.ezen.burger.dto.CartVO;
import com.ezen.burger.dto.GuestVO;
import com.ezen.burger.dto.MemberVO;
import com.ezen.burger.dto.MyAddressVO;
import com.ezen.burger.dto.ProductVO;
import com.ezen.burger.dto.orderVO;
import com.ezen.burger.service.AddressService;
import com.ezen.burger.service.CartService;
import com.ezen.burger.service.MemberService;
import com.ezen.burger.service.OrderService;
import com.ezen.burger.service.ProductService;

@Controller
public class MemberController {
	@Autowired
	MemberService ms;
	@Autowired
	ProductService ps;
	@Autowired
	OrderService os;
	@Autowired
	CartService cs;
	@Autowired
	AddressService as;
	
	
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
			session.setAttribute("memberkind", mvo.getMemberkind());
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
		session.setAttribute("memberkind", gvo.getMemberkind());
		mav.setViewName("redirect:/");
		return mav;
	}
	
	@RequestMapping(value="/deliveryForm")
	public ModelAndView deliveryForm(HttpServletRequest request,
			@RequestParam("kind1") String kind1) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		if(session.getAttribute("memberkind") != null) {
			int memberKind = (int)session.getAttribute("memberkind");
			// 회원 종류 검사 (1:회원, 2:비회원)
			if(memberKind == 1) {
				MemberVO mvo = (MemberVO)session.getAttribute("loginUser");
				if(mvo == null) {
					mav.setViewName("redirect:/loginForm");
				}else {
					MyAddressVO avo = as.getMyAddress(mvo.getMseq());
					if(avo == null) {
						ArrayList<orderVO> list1 = os.getOrderList(mvo.getId());
						ArrayList<CartVO> list2 = cs.selectCart( mvo.getId() );	
						
						mav.addObject("ovo", list1);
						mav.addObject("cvo", list2);
						mav.setViewName("delivery/addressSet");
					}else {
						ArrayList<ProductVO> list = ps.getProductList(kind1);
						ArrayList<orderVO> list1 = os.getOrderList(mvo.getId());
						ArrayList<CartVO> list2 = cs.selectCart( mvo.getId() );	
						
						mav.addObject("ovo", list1);
						mav.addObject("cvo", list2);
						mav.addObject("productList", list);
						mav.addObject("kind1", kind1);
						mav.setViewName("delivery/delivery");
					}
				}
			}else if(memberKind == 2){
				GuestVO gvo = (GuestVO)session.getAttribute("loginUser");
				if(gvo == null) {
					mav.setViewName("redirect:/loginForm");
				}else {
					if(gvo.getAddress() == null) {
						mav.setViewName("delivery/addressSet");
					}else {
						ArrayList<ProductVO> list = ps.getProductList(kind1);
						mav.addObject("productList", list);
						mav.addObject("kind1", kind1);
						mav.setViewName("delivery/delivery");
					}
				}
			}else {
				mav.setViewName("redirect:/loginForm");
			}
		}else {
			mav.setViewName("redirect:/loginForm");
		}
		return mav;
	}
	
	@RequestMapping(value="/memberUpdateForm")
	public ModelAndView memberUpdateForm(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		if(session.getAttribute("memberkind") != null) {
			int memberKind = (int)session.getAttribute("memberkind");
			if(memberKind == 1) {
				MemberVO mvo = (MemberVO)session.getAttribute("loginUser");
				ArrayList<orderVO> list1 = os.getOrderList(mvo.getId());
				ArrayList<CartVO> list2 = cs.selectCart( mvo.getId() );	
				
				mav.addObject("ovo", list1);
				mav.addObject("cvo", list2);
				mav.addObject("MemberVO", mvo);
				mav.setViewName("member/updateForm");
			}else if(memberKind == 2){
				mav.addObject("kind1", 1);
				mav.setViewName("redirect:/deliveryForm");
			}else {
				mav.setViewName("redirect:/loginForm");
			}
		}else {
			mav.setViewName("redirect:/loginForm");
		}
		return mav;
	}
	
	@RequestMapping(value="/updateMember")
	public ModelAndView updateMember(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		if(session.getAttribute("memberkind") != null) {
			int memberKind = (int)session.getAttribute("memberkind");
			if(memberKind == 1) {
				MemberVO mvo = (MemberVO)session.getAttribute("loginUser");
				mvo.setId(request.getParameter("id"));
				mvo.setPwd(request.getParameter("pwd"));
				mvo.setName(request.getParameter("name"));
				mvo.setPhone(request.getParameter("phone"));
				ms.updateMember(mvo);
				
				session.setAttribute("loginUser", mvo);
				session.setAttribute("memberkind", mvo.getMemberkind());
				mav.setViewName("redirect:/deliveryMypageForm");
			}else if(memberKind == 2){
				mav.addObject("kind1", 1);
				mav.setViewName("redirect:/deliveryForm");
			}else {
				mav.setViewName("redirect:/loginForm");
			}
		}else {
			mav.setViewName("redirect:/loginForm");
		}
		return mav;
	}
	
	@RequestMapping(value="/memberDelete")
	public ModelAndView memberDelete(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		if(session.getAttribute("memberkind") != null) {
			int memberKind = (int)session.getAttribute("memberkind");
			if(memberKind == 1) {
				int mseq = Integer.parseInt(request.getParameter("mseq"));
				ms.deleteMember(mseq);
				session.invalidate();
				mav.setViewName("redirect:/loginForm");
			}else if(memberKind == 2){
				mav.addObject("kind1", 1);
				mav.setViewName("redirect:/deliveryForm");
			}else {
				mav.setViewName("redirect:/loginForm");
			}
		}else {
			mav.setViewName("redirect:/loginForm");
		}
		return mav;
	}
	
	
	
	@RequestMapping(value="/joinForm")
	public String join_form(Model model, HttpServletRequest request) {
		return "member/join";
	}
	
	
	@RequestMapping(value="/contract")
	public String contractform(Model model, HttpServletRequest request) {
		return "member/contract";
	}
	
	@RequestMapping(value="/popup1")
	public String popup1(Model model, HttpServletRequest request) {
		return "member/popup1";
	}
	
	@RequestMapping(value="/popup2")
	public String popup2(Model model, HttpServletRequest request) {
		return "member/popup2";
	}
	
	
	@RequestMapping(value="/joinpageForm")
	public ModelAndView firstjoinpage(Model model, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("member/joinpage");
		return mav;
	}
	
	
	
	
	@RequestMapping(value="/joinpage", method=RequestMethod.POST)
	public ModelAndView joinpage( @ModelAttribute("dto") @Valid MemberVO membervo,
			BindingResult result, @RequestParam(value="reid" , required = false) String reid, 
			@RequestParam(value="pwdCheck" , required = false) String pwdCheck, Model model) {
		ModelAndView mav = new ModelAndView();
		
		
		if( result.getFieldError("id") != null ) { 
				mav.addObject("message", result.getFieldError("id").getDefaultMessage() ); 
				mav.addObject("reid",reid); 
				mav.setViewName("member/joinpage"); 
		 } else if( !membervo.getId().equals(reid)){
				mav.addObject("message","아이디 중복체크가 되지 않았습니다");
				mav.setViewName("member/joinpage");
		
		 } else if( result.getFieldError("name") != null ) {
				mav.addObject("message", result.getFieldError("name").getDefaultMessage() );
				mav.addObject("reid", reid);
				mav.setViewName("member/joinpage");		
		 } else if( result.getFieldError("phone") != null ) {
				mav.addObject("message", result.getFieldError("phone").getDefaultMessage() );
				mav.addObject("reid", reid);
				mav.setViewName("member/joinpage");
		 } else	if( result.getFieldError("pwd") != null ) {
			mav.addObject("message", result.getFieldError("pwd").getDefaultMessage() );
			mav.addObject("reid", reid);
			mav.setViewName("member/joinpage");
		
		} else if( !membervo.getPwd().equals(pwdCheck)) {
			mav.addObject("message","비밀번호 확인이 일치하지 않습니다.");
			mav.addObject("reid", reid);
			mav.setViewName("member/joinpage");
		}else { 
			ms.insertMember(membervo);
			mav.addObject("message", "회원가입이 완료되었습니다. 로그인 하세요");
			mav.setViewName("member/complet");
		}		
		return mav;
	}
	
	
	@RequestMapping("/idCheckForm")
	public ModelAndView idcheck( @RequestParam("id") String id ) {
		ModelAndView mav = new ModelAndView();
		
		MemberVO mvo = ms.getMember(id);
		if( mvo==null ) mav.addObject("result" , -1);
		else mav.addObject("result" , 1);
		
		mav.addObject("id" , id);
		mav.setViewName("member/idcheck");		
		return mav;
	}
	
	@RequestMapping(value="/complet")
	public String complet(Model model, HttpServletRequest request) {
		return "member/complet";
	}
	
}	