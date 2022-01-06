package com.ezen.burger.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.ServletContext;
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
import com.ezen.burger.dto.ProductVO;
import com.ezen.burger.dto.QnaVO;
import com.ezen.burger.service.AdminService;
import com.ezen.burger.service.EventService;
import com.ezen.burger.service.MemberService;
import com.ezen.burger.service.QnaService;
import com.oreilly.servlet.MultipartRequest;
//github.com/Ezen-MVC-TeamProject/BurgerKingWeb_Spring
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@Controller
public class AdminController {
	@Autowired
	AdminService as;

	@Autowired
	MemberService ms;

	@Autowired
	QnaService qs;

	@Autowired
	EventService es;

	@Autowired
	ServletContext context;

	@RequestMapping(value = "/adminLogin", method = RequestMethod.POST)
	public String adminLogin(@ModelAttribute("dto") @Valid AdminVO adminvo, BindingResult result,
			HttpServletRequest request, Model model) {
		if (result.getFieldError("id") != null) {
			model.addAttribute("message", result.getFieldError("id").getDefaultMessage());
			return "admin/adminLogin";
		} else if (result.getFieldError("pwd") != null) {
			model.addAttribute("message", result.getFieldError("pwd").getDefaultMessage());
			return "admin/adminLogin";
		}

		AdminVO avo = as.adminCheck(adminvo.getId());

		if (avo == null) {
			model.addAttribute("message", "id가 없습니다.");
			return "admin/adminLogin";
		} else if (avo.getPwd() == null) {
			model.addAttribute("message", "관리자에게 문의하세요");
			return "admin/adminLogin";
		} else if (!avo.getPwd().equals(adminvo.getPwd())) {
			model.addAttribute("message", "비밀번호가 맞지 않습니다.");
			return "admin/adminLogin";
		} else if (avo.getPwd().equals(adminvo.getPwd())) {
			HttpSession session = request.getSession();
			session.setAttribute("loginAdmin", avo);
			return "admin/main";
		} else {
			model.addAttribute("message", "원인미상의 오류로 로그인 불가");
			return "admin/adminLogin";
		}
	}

	@RequestMapping("/adminLogout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("loginAdmin");
		return "redirect:/admin";
	}

	@RequestMapping("adminMemberList")
	public String adminMemberList(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		if (session.getAttribute("loginAdmin") == null) {
			return "admin/adminLogin";
		} else {
			int page = 1;
			if (request.getParameter("page") != null) {
				page = Integer.parseInt(request.getParameter("page"));
				session.setAttribute("page", page);
			} else if (session.getAttribute("page") != null) {
				page = (int) session.getAttribute("page");
			} else {
				page = 1;
				session.removeAttribute("page");
			}

			String key = "";
			if (request.getParameter("key") != null) {
				key = request.getParameter("key");
				session.setAttribute("key", key);
			} else if (session.getAttribute("key") != null) {
				key = (String) session.getAttribute("key");
			} else {
				session.removeAttribute("key");
				key = "";
			}

			Paging paging = new Paging();
			paging.setPage(page);

			int count = as.getAllCount("member", "name", key);
			paging.setTotalCount(count);
			paging.paging();

			ArrayList<MemberVO> memberList = as.listMember(paging, key);

			model.addAttribute("memberList", memberList);
			model.addAttribute("paging", paging);
			model.addAttribute("key", key);
		}
		return "admin/member/memberList";
	}

	@RequestMapping(value = "/adminMemberDelete", method = RequestMethod.POST)
	public String adminMemberDelete(@RequestParam("mseq") int[] mseqArr) {
		for (int mseq : mseqArr)
			as.deleteMember(mseq);
		return "redirect:/adminMemberList";
	}

//event
	@RequestMapping("/adminEventList")
	public String adminEventList(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		if (session.getAttribute("loginAdmin") == null) {
			return "admin/adminLogin";
		} else {
			int page = 1;
			if (request.getParameter("page") != null) {
				page = Integer.parseInt(request.getParameter("page"));
				session.setAttribute("page", page);
			} else if (session.getAttribute("page") != null) {
				page = (int) session.getAttribute("page");
			} else {
				page = 1;
				session.removeAttribute("page");
			}

			String key = "";
			if (request.getParameter("key") != null) {
				key = request.getParameter("key");
				session.setAttribute("key", key);
			} else if (session.getAttribute("key") != null) {
				key = (String) session.getAttribute("key");
			} else {
				session.removeAttribute("key");
				key = "";
			}

			Paging paging = new Paging();
			paging.setPage(page);
			int count = as.getAllCount("event", "subject", key);
			paging.setTotalCount(count);
			paging.paging();

			ArrayList<EventVO> list = as.listEvent(paging, key);

			model.addAttribute("eventList", list);
			model.addAttribute("paging", paging);
			model.addAttribute("key", key);
			return "admin/event/eventList";
		}
	}

	@RequestMapping("/adminEventDetail")
	public String adminEventDetail(HttpServletRequest request, Model model, @RequestParam("eseq") int eseq) {
		HttpSession session = request.getSession();
		if (session.getAttribute("loginAdmin") == null) {
			return "admin/adminLogin";
		} else {
			EventVO evo = es.getEvent(eseq);
			model.addAttribute("eventVO", evo);
			return "admin/event/eventDetail";
		}
	}

	@RequestMapping("/adminEventWriteForm")
	public String adminEventWriteForm(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		if (session.getAttribute("loginAdmin") == null)
			return "admin/adminLogin";

		return "admin/event/eventWrite";
	}

	@RequestMapping(value = "/adminEventWrite", method = RequestMethod.POST) //// 질문1
	public String adminEventWrite(Model model, HttpServletRequest request) {
		String savePath = context.getRealPath("image/main/event/eventDetail");
		System.out.println(savePath);

		try {
			MultipartRequest multi = new MultipartRequest(request, savePath, 5 * 1024 * 1024, "UTF-8",
					new DefaultFileRenamePolicy());
			EventVO evo = new EventVO();

			evo.setSubject(multi.getParameter("subject"));
			evo.setContent(multi.getParameter("content"));
			evo.setEnddate(Timestamp.valueOf(multi.getParameter("enddate")));
			evo.setImage(multi.getFilesystemName("image"));

			if (multi.getParameter("subject") == null) {
				System.out.println("이벤트명을 입력하세요");
				model.addAttribute("evo", evo);
				return "admin/event/eventWrite.jsp";
			}
			as.insertEvent(evo);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "redirect:/adminEventList";

	}

	@RequestMapping(value = "/adminEventDelete")
	public String adminEventDelete(@RequestParam("delete") int[] eseqArr, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute("loginAdmin") == null) {
			return "admin/adminLogin";
		} else {
			for (int eseq : eseqArr)
				es.deleteEvent(eseq);
			return "redirect:/adminEventList";
		}
	}

	@RequestMapping(value = "/adminEventUpdateForm", method = RequestMethod.POST) /// 2
	public String adminEventUpdateForm(@RequestParam("eseq") int eseq, HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		if (session.getAttribute("loginAdmin") == null) {
			return "admin/adminLogin";
		} else {
			EventVO evo = es.getEvent(eseq);
			model.addAttribute("eventVO", evo);

			return "admin/event/evnetUpdate";
		}
	}

//	  public String adminEventUpdate(@ModelAttribute("EventVO") @Valid EventVO evo,
//	  BindingResult result, HttpServletRequest request, Model model) {
	@RequestMapping(value="/adminEventUpdate" , method=RequestMethod.POST) //3
	  public String adminEventUpdate( Model model, HttpServletRequest request) { 
	  String savePath=context.getRealPath("image/main/event/eventDetail");
		System.out.println(savePath);
		
		try {
			MultipartRequest multi=new MultipartRequest(request, savePath,
					5*1024*1024, "UTF-8", new DefaultFileRenamePolicy());
			EventVO evo=new EventVO();
			evo.setEseq(Integer.parseInt(multi.getParameter("eseq")));
			evo.setSubject(multi.getParameter("subject"));
			evo.setContent(multi.getParameter("content"));
			evo.setEnddate(Timestamp.valueOf(multi.getParameter("enddate")));
			evo.setImage(multi.getFilesystemName("image"));
		
			if(multi.getFilesystemName("image") == null)
				evo.setImage(multi.getParameter("oldImage"));
			else
				evo.setImage(multi.getFilesystemName("image"));
			
			as.updateEvent(evo);
		} catch (IOException e) {		e.printStackTrace();	}
		return "redirect:/adminEventList";
	  
	  }

	@RequestMapping(value = "/adminMemberUpdateForm")
	public String adminMemberUpdateForm(@RequestParam("mseq") int mseq, HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		if (session.getAttribute("loginAdmin") == null) {
			return "admin/adminLogin";
		} else {
			MemberVO mvo = ms.getMember_mseq(mseq);
			model.addAttribute("memberVO", mvo);

			return "admin/member/memberUpdate";
		}
	}

	@RequestMapping(value = "/adminMemberUpdate", method = RequestMethod.POST)
	public String adminMemberUpdateForm(@ModelAttribute("memberVO") @Valid MemberVO mvo, BindingResult result,
			HttpServletRequest request, Model model,
			@RequestParam(value = "pwd_chk", required = false) String pwd_chk) {
		if (result.getFieldError("pwd") != null) {
			model.addAttribute("message", "암호를 입력하세요");
			return "admin/member/memberUpdate";
		} else if (result.getFieldError("name") != null) {
			model.addAttribute("message", "이름을 입력하세요");
			return "admin/member/memberUpdate";
		} else if (pwd_chk == null || (pwd_chk != null && !pwd_chk.equals(mvo.getPwd()))) {
			model.addAttribute("message", "비밀번호 확인이 일치하지 않습니다.");
			return "admin/member/memberUpdate";
		}
		if (result.getFieldError("phone") != null) {
			model.addAttribute("message", "전화번호를 입력하세요");
			return "admin/member/memberUpdate";
		} else {
			ms.updateMember(mvo);
			return "redirect:/adminMemberList";
		}
	}

	@RequestMapping(value = "/adminQnaList")
	public String adminQnaList(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		if (session.getAttribute("loginAdmin") == null) {
			return "admin/adminLogin";
		} else {
			int page = 1;
			if (request.getParameter("page") != null) {
				page = Integer.parseInt(request.getParameter("page"));
				session.setAttribute("page", page);
			} else if (session.getAttribute("page") != null) {
				page = (int) session.getAttribute("page");
			} else {
				page = 1;
				session.removeAttribute("page");
			}

			String key = "";
			if (request.getParameter("key") != null) {
				key = request.getParameter("key");
				session.setAttribute("key", key);
			} else if (session.getAttribute("key") != null) {
				key = (String) session.getAttribute("key");
			} else {
				session.removeAttribute("key");
				key = "";
			}

			Paging paging = new Paging();
			paging.setPage(page);

			int count = as.getAllCount("qna", "id", key);
			paging.setTotalCount(count);
			paging.paging();

			ArrayList<QnaVO> qnaList = as.listQna(paging, key);

			model.addAttribute("qnaList", qnaList);
			model.addAttribute("paging", paging);
			model.addAttribute("key", key);
		}
		return "admin/qna/qnaList";
	}

	@RequestMapping(value = "/adminQnaDelete", method = RequestMethod.POST)
	public String adminQnaDelete(@RequestParam("delete") int[] qseqArr) {
		for (int qseq : qseqArr)
			as.deleteQna(qseq);
		return "redirect:/adminQnaList";
	}

	@RequestMapping("/adminQnaDetail")
	public String adminQnaDetail(@RequestParam("qseq") int qseq, HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		if (session.getAttribute("loginAdmin") == null) {
			return "admin/adminLogin";
		} else {
			QnaVO qvo = qs.getQna(qseq);
			model.addAttribute("qnaVO", qvo);
			return "admin/qna/qnaDetail";
		}

	}

	@RequestMapping("/adminQnaRepsave")
	public String adminQnaRepsave(HttpServletRequest request, Model model, @RequestParam("qseq") int qseq,
			@RequestParam("reply") String reply) {
		HttpSession session = request.getSession();
		if (session.getAttribute("loginAdmin") == null) {
			return "admin/adminLogin";
		} else {
			qs.updateQna(qseq, reply);
			return "redirect:/adminQnaDetail?qseq=" + qseq;
		}

	}

	@RequestMapping("adminShortProductList")
	public String adminShortProductList(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		if (session.getAttribute("loginAdmin") == null) {
			return "admin/adminLogin";
		} else {
			int page = 1;
			if (request.getParameter("page") != null) {
				page = Integer.parseInt(request.getParameter("page"));
				session.setAttribute("page", page);
			} else if (session.getAttribute("page") != null) {
				page = (int) session.getAttribute("page");
			} else {
				page = 1;
				session.removeAttribute("page");
			}

			String key = "";
			if (request.getParameter("key") != null) {
				key = request.getParameter("key");
				session.setAttribute("key", key);
			} else if (session.getAttribute("key") != null) {
				key = (String) session.getAttribute("key");
			} else {
				session.removeAttribute("key");
				key = "";
			}

			Paging paging = new Paging();
			paging.setPage(page);

			int count = as.getAllCount("product", "pname", key);
			paging.setTotalCount(count);
			paging.paging();

			ArrayList<ProductVO> shortproductList = as.listShortProduct(paging, key);

			model.addAttribute("shortproductList", shortproductList);
			model.addAttribute("paging", paging);
			model.addAttribute("key", key);
		}
		return "admin/product/shortproductList";
	}

	@RequestMapping("adminProductList")
	public String adminProductList(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		if (session.getAttribute("loginAdmin") == null) {
			return "admin/adminLogin";
		} else {
			int page = 1;
			if (request.getParameter("page") != null) {
				page = Integer.parseInt(request.getParameter("page"));
				session.setAttribute("page", page);
			} else if (session.getAttribute("page") != null) {
				page = (int) session.getAttribute("page");
			} else {
				page = 1;
				session.removeAttribute("page");
			}

			String key = "";
			if (request.getParameter("key") != null) {
				key = request.getParameter("key");
				session.setAttribute("key", key);
			} else if (session.getAttribute("key") != null) {
				key = (String) session.getAttribute("key");
			} else {
				session.removeAttribute("key");
				key = "";
			}

			Paging paging = new Paging();
			paging.setPage(page);

			int count = as.getAllCount("product", "pname", key);
			paging.setTotalCount(count);
			paging.paging();

			ArrayList<ProductVO> productList = as.listProduct(paging, key);

			model.addAttribute("productList", productList);
			model.addAttribute("paging", paging);
			model.addAttribute("key", key);
		}
		return "admin/product/productList";
	}

	@RequestMapping(value = "/adminProductDelete", method = RequestMethod.POST)
	public String adminProductDelete(@RequestParam("delete") int[] pseqArr) {
		for (int pseq : pseqArr)
			as.deleteProduct(pseq);
		return "redirect:/adminShortProductList";
	}

	@RequestMapping("/adminProductWriteForm")
	public String adminProductWriteForm(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		if (session.getAttribute("loginAdmin") == null) {
			return "admin/adminLogin";
		} else {
			String kindList[] = { "스페셜&할인팩", "프리미엄", "와퍼", "주니어&버거", "올데이킹&치킨버거", "사이드", "음료&디저트", "독퍼" };
			model.addAttribute("kindList", kindList);
			return "admin/product/productWrite";
		}
	}

	@RequestMapping("/adminShortProductWriteForm")
	public String adminShortProductWriteForm(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		if (session.getAttribute("loginAdmin") == null) {
			return "admin/adminLogin";
		} else {
			String kindList[] = { "스페셜&할인팩", "프리미엄", "와퍼", "주니어&버거", "올데이킹&치킨버거", "사이드", "음료&디저트", "독퍼" };
			model.addAttribute("kindList", kindList);
			return "admin/product/shortproductWrite";
		}
	}

	@RequestMapping(value = "adminProductWrite", method = RequestMethod.POST)
	public String adminProductWrite(Model model, HttpServletRequest request) {
		String savePath = context.getRealPath("/images");
		System.out.println(savePath);

		try {
			MultipartRequest multi = new MultipartRequest(request, savePath, 5 * 1024 * 1024, "UTF-8",
					new DefaultFileRenamePolicy());
			ProductVO pvo = new ProductVO();

			pvo.setKind1(multi.getParameter("kind1"));
			pvo.setKind2(multi.getParameter("kind2"));
			pvo.setKind3(multi.getParameter("kind3"));
			
		    pvo.setPname(multi.getParameter("pname"));
		    pvo.setPrice1(Integer.parseInt(multi.getParameter("price1")));
		    pvo.setPrice2(Integer.parseInt("0"));
		    pvo.setPrice3(Integer.parseInt("0"));
		    pvo.setContent(multi.getParameter("content"));
		    pvo.setImage(multi.getFilesystemName("image"));
		    pvo.setUseyn(multi.getParameter("useyn"));
		    
		    if( multi.getParameter("pname") == null ) {
		    	System.out.println("이름을 입력하세요");
		    	model.addAttribute("pvo", pvo);
		    	return "admin/product/productWrite.jsp";
		    }
		    as.insertProduct(pvo);
		} catch (IOException e) {e.printStackTrace();	}
		return "redirect:/adminProductList";
	}

}
