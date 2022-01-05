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
	
	// 고객센터 문의
	@RequestMapping(value="qnaForm")
	public ModelAndView qnaForm(Model model, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO) session.getAttribute("loginUser");
		if (mvo == null) mav.setViewName("ServiceCenter/qnaList");
		else {
		mav.addObject("qnaList", qs.listQna(mvo.getId()) );
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
	@RequestMapping(value="/passCheckForm" , method=RequestMethod.POST)
	 public ModelAndView passCheckForm(@RequestParam("qseq")int qseq) {
		ModelAndView mav=new ModelAndView();
		mav.addObject("QnaVO", qs.getpassChk(qseq));
	    mav.setViewName("ServiceCenter/passChk");   
	      return mav;
	   }
	
	
	// 고객센터 qna pass검사
	@RequestMapping(value="/passChk" , method=RequestMethod.POST)
	public ModelAndView passChk (HttpServletRequest request , Model model, 
			@RequestParam("qseq")int qseq, @RequestParam("pass")int pass) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();

		mav.addObject("QnaVO", qs.getpassChk(pass));
		mav.addObject("QnaVO", qs.getpassChk(qseq));
		mav.setViewName("ServiceCenter/qnaView");
 
		
		return mav;
	}
	
	
	
	
	// 고객센터 문의내용
	@RequestMapping(value="/qnaView")
	public ModelAndView qna_view(Model model, HttpServletRequest request,
			@RequestParam("qseq") int qseq) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("QnaVO", qs.getQna(qseq) );
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
	
	@RequestMapping(value="/deliveryMypageForm")
	public ModelAndView deliveryMypageForm(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		if(session.getAttribute("memberkind") != null) {
			int memberKind = (int)session.getAttribute("memberkind");
			if(memberKind == 1) {
				
				mav.setViewName("delivery/myPage");
			}else if(memberKind == 2){
				mav.setViewName("redirect:/deliveryForm");
			}else {
				mav.setViewName("redirect:/loginForm");
			}
		}else {
			mav.setViewName("redirect:/loginForm");
		}
		return mav;
	}
}


