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

import com.ezen.burger.dto.MemberVO;
import com.ezen.burger.dto.QnaVO;
import com.ezen.burger.service.QnaService;

@Controller
public class QnaController {
	@Autowired
	QnaService qs;
	
	
		// 고객센터 문의
		@RequestMapping(value="qnaForm")
		public ModelAndView qnaForm(Model model, HttpServletRequest request , 
				@RequestParam(value="kind1" , required = false) String kind1) {
			ModelAndView mav = new ModelAndView();
			HttpSession session = request.getSession();	
			if(session.getAttribute("memberkind") != null) {
				int memberKind = (int)session.getAttribute("memberkind");
				// 회원 종류 검사 (1:회원, 2:비회원)
				if(memberKind == 1) {
					MemberVO mvo = (MemberVO) session.getAttribute("loginUser");
					if(mvo != null ) {
						mav.addObject("qnaList", qs.listQna(mvo.getId()) );
						mav.setViewName("ServiceCenter/qnaList");	
					}else {
						mav.setViewName("redirect:/loginForm");
					}
				}else if(memberKind == 2 ) {
					mav.addObject("message", "Qna문의를 하려면 로그인을 하셔야합니다.");
					mav.setViewName("member/loginForm");	
				}else {
					mav.setViewName("redirect:/loginForm");	
				}
			}else {
				mav.setViewName("ServiceCenter/qnaList");
			}
			return mav;
		}
		
		
		// 고객센터 문의작성
		@RequestMapping(value="qnaWriteForm")
		public String qnaWriteForm(Model model, HttpServletRequest request) {
			return "ServiceCenter/qnaWrite";
		}
		
		
		// 고객센터 문의내용전송
		@RequestMapping(value="qnaWrite" , method=RequestMethod.POST)
		public ModelAndView qna_write( @ModelAttribute("dto") @Valid QnaVO qnavo,
				BindingResult result, Model model, HttpServletRequest request){
			ModelAndView mav = new ModelAndView();
			if(result.getFieldError("subject") !=null) {
				mav.addObject("message", "제목을 입력하세요"); 
				mav.setViewName("ServiceCenter/qnaWrite");
				return mav;
			}else if(result.getFieldError("content") !=null) {
				mav.addObject("message", "내용을 입력하세요");
				mav.setViewName("ServiceCenter/qnaWrite");
				return mav;
			}else if(result.getFieldError("pass") !=null) {
				mav.addObject("message", "비밀번호를 입력하세요");
				mav.setViewName("ServiceCenter/qnaWrite");
				return mav;
			}  
			HttpSession session = request.getSession();
			MemberVO mvo = (MemberVO) session.getAttribute("loginUser");
			  if (mvo == null) mav.setViewName("member/loginform");
			    else {
			    	qnavo.setId(mvo.getId());
			    	qs.insertQna(qnavo);
			    }   
			    mav.setViewName("redirect:/qnaForm");
				return mav;
			}
		
		
		// 고객센터 qna passform
		@RequestMapping(value="/passCheckForm")
		 public ModelAndView passCheckForm(@RequestParam("qseq")int qseq,
				 @RequestParam(value="message", required = false)String message,
				 HttpServletRequest request) {
			ModelAndView mav=new ModelAndView();
			HttpSession session = request.getSession();
			
			if(session.getAttribute("loginUser") == null) {
				mav.setViewName("redirect:/loginForm");
			}else {
				if(message != null) {
					mav.addObject("message", message);
				}
				mav.addObject("qseq", qseq);
			    mav.setViewName("ServiceCenter/passChk");
			}
		    return mav;
		 }
		

		// 고객센터 qna pass검사
			@RequestMapping(value="/passChk", method=RequestMethod.POST)
			public ModelAndView passChk (HttpServletRequest request , Model model) {
				ModelAndView mav = new ModelAndView();
				HttpSession session = request.getSession();
				if(session.getAttribute("loginUser") == null) {
					mav.setViewName("redirect:/loginForm");
				}else {
					String pass = request.getParameter("pass");
					int qseq = Integer.parseInt(request.getParameter("qseq"));
					QnaVO qvo = qs.getpassChk(qseq); 
					if(!qvo.getPass().equals(pass)) {
						mav.addObject("message", "비밀번호가 일치하지 않습니다"); 
						mav.setViewName("redirect:/passCheckForm?qseq=" + qseq);
					}else {
						mav.addObject("qseq", qseq);
						mav.setViewName("redirect:/qnaView");
					}
				}
				return mav;
			}
			
		
		// 고객센터 문의내용
		@RequestMapping(value="/qnaView")
		public ModelAndView qna_view(Model model, HttpServletRequest request) {
			ModelAndView mav = new ModelAndView();
			int qseq = Integer.parseInt(request.getParameter("qseq"));
			mav.addObject("qnaVO", qs.getQna(qseq));
			mav.setViewName("ServiceCenter/qnaView");
			return mav;
		}
		 
		
		// 고객센터 qna 삭제
		@RequestMapping(value="qnaDelete" )
		public String qnaDelete( @RequestParam("delete") int [] qseqArr ) {
			for( int qseq : qseqArr)
				qs.deleteQna(qseq);
			return "redirect:/qnaForm";
		}
	
	
	
}
