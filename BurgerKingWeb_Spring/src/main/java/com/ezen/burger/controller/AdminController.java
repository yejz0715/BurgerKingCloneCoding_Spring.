package com.ezen.burger.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import com.ezen.burger.service.ProductService;
import com.ezen.burger.service.QnaService;
import com.oreilly.servlet.MultipartRequest;
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
	ProductService ps;

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

	@RequestMapping(value = "/adminEventWrite", method = RequestMethod.POST) 
	public String adminEventWrite(Model model, HttpServletRequest request) {
		String savePath = context.getRealPath("upload/main/event/eventDetail");
		System.out.println(savePath);

		try {
			MultipartRequest multi = new MultipartRequest(request, savePath, 5 * 1024 * 1024, "UTF-8",
					new DefaultFileRenamePolicy());
			EventVO evo = new EventVO();

			evo.setSubject(multi.getParameter("subject"));
			evo.setContent(multi.getParameter("content"));
			evo.setEnddate(multi.getParameter("enddate"));
			evo.setImage(multi.getFilesystemName("image"));
			evo.setState(1);
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

	@RequestMapping(value = "/adminEventUpdateForm")
	public String adminEventUpdateForm(HttpServletRequest request, Model model, @RequestParam("eseq")int eseq) {
		HttpSession session = request.getSession();
		if (session.getAttribute("loginAdmin") == null) {
			return "admin/adminLogin";
		} else {
			EventVO evo = es.getEvent(eseq);
			model.addAttribute("eventVO", evo);

			return "admin/event/eventUpdate";
		}
	}
	
	@RequestMapping(value="/adminEventUpdate" , method=RequestMethod.POST) 
	  public String adminEventUpdate( Model model, HttpServletRequest request) { 
	  String savePath=context.getRealPath("upload/main/event/eventDetail");
		System.out.println(savePath);
		try {
			MultipartRequest multi=new MultipartRequest(request, savePath,
					5*1024*1024, "UTF-8", new DefaultFileRenamePolicy());
			EventVO evo=new EventVO();
			String enddate = multi.getParameter("enddate");
			enddate = enddate.substring(0, 10);
			evo.setEseq(Integer.parseInt(multi.getParameter("eseq")));
			evo.setSubject(multi.getParameter("subject"));
			evo.setContent(multi.getParameter("content"));
		    evo.setEnddate(enddate);
		    evo.setState(1);
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
//qna
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
	public String adminProductWrite(Model model, HttpServletRequest request,
			HttpServletResponse response) {
		String savePath = context.getRealPath("/images");
		System.out.println(savePath);

		try {
			MultipartRequest multi = new MultipartRequest(request, savePath, 5 * 1024 * 1024, "UTF-8",
					new DefaultFileRenamePolicy());
			
			String k1 = multi.getParameter("kind1");
			String k2 = multi.getParameter("kind2");
			String k3 = multi.getParameter("kind3");
			
			int result = as.checkShortProductYN(k1, k2, k3);
			
			if(result == 2) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter writer = response.getWriter();
				writer.println("<script>alert('해당하는 종류분류 값이 없습니다.'); location.href='adminProductWriteForm';</script>");
				writer.close();
			}else if(result == 3) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter writer = response.getWriter();
				writer.println("<script>alert('해당하는 분류번호 값이 이미 있습니다.'); location.href='adminProductWriteForm';</script>");
				writer.close();
			}else if(result == 4) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter writer = response.getWriter();
				writer.println("<script>alert('입력할 수 없는 세부 값입니다.'); location.href='adminProductWriteForm';</script>");
				writer.close();
			}else {
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
			    
			    as.insertProduct(pvo);
			}
			
		} catch (IOException e) {e.printStackTrace();	}
		return "redirect:/adminProductList";
	}
	@RequestMapping(value = "adminShortProductWrite", method = RequestMethod.POST)
	public String adminShortProductWrite(Model model, HttpServletRequest request,
			HttpServletResponse response) {
		String savePath = context.getRealPath("/images");
		System.out.println(savePath);

		try {
			MultipartRequest multi = new MultipartRequest(request, savePath, 5 * 1024 * 1024, "UTF-8",
					new DefaultFileRenamePolicy());
			
			String k1 = multi.getParameter("kind1");
			String k2 = multi.getParameter("kind2");
			
			int result = as.checkShortProductYN2(k1, k2);
			
			if(result == 2) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter writer = response.getWriter();
				writer.println("<script>alert('해당하는 분류번호 값이 이미 있습니다.'); location.href='adminShortProductWriteForm';</script>");
				writer.close();
			}else {
				ProductVO pvo = new ProductVO();
				pvo.setKind1(multi.getParameter("kind1"));
				pvo.setKind2(multi.getParameter("kind2"));
				pvo.setKind3("4");
			    pvo.setPname(multi.getParameter("pname"));
			    pvo.setPrice1(Integer.parseInt(multi.getParameter("price1")));
			    pvo.setPrice2(0);
			    pvo.setPrice3(0);
			    pvo.setContent("");
			    pvo.setImage(multi.getFilesystemName("image"));
			    pvo.setUseyn(multi.getParameter("useyn"));
			    
			    as.insertProduct(pvo);
			}
			
		} catch (IOException e) {e.printStackTrace();	}
		return "redirect:/adminShortProductList";
	}
	@RequestMapping("adminProductDetail")
	public String productDetail(@RequestParam("pseq") int pseq, HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		if (session.getAttribute("loginAdmin") == null) {
			return "admin/adminLogin";
		} else {
			ProductVO pvo = as.productDetail(pseq);

			// 카테고리 별 타이틀을 배열에 저장 
			String kindList1[] = {"0", "스페셜&할인팩", "프리미엄", "와퍼", "주니어&버거", "올데이킹&치킨버거", "사이드", "음료&디저트", "독퍼"};
			int index = Integer.parseInt(pvo.getKind1());
			String kindList3[] = {"0", "Single", "Set", "LargeSet", "Menu list"};
			int index2 = Integer.parseInt(pvo.getKind3());
			// 추출한 kind 번호로 배열에서 해당 타이틀 추출 & 리퀘스트에 저장 
			request.setAttribute("kind1", kindList1[index]);
			request.setAttribute("kind3", kindList3[index2]);
			request.setAttribute("productVO", pvo); 
			return "admin/product/productDetail";
		}
	}
	
	@RequestMapping("adminShortProductDetail")
	public String shortProductDetail(@RequestParam("pseq") int pseq, HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		if (session.getAttribute("loginAdmin") == null) {
			return "admin/adminLogin";
		} else {
			ProductVO pvo = as.productDetail(pseq);

			// 카테고리 별 타이틀을 배열에 저장 
			String kindList1[] = {"0", "스페셜&할인팩", "프리미엄", "와퍼", "주니어&버거", "올데이킹&치킨버거", "사이드", "음료&디저트", "독퍼"};
			int index = Integer.parseInt(pvo.getKind1());
			String kindList3[] = {"0", "Single", "Set", "LargeSet", "Menu list"};
			int index2 = Integer.parseInt(pvo.getKind3());
			String useynList[] = {"0", "사용", "미사용"};
			int index3 = Integer.parseInt(pvo.getUseyn());
			// 추출한 kind 번호로 배열에서 해당 타이틀 추출 & 리퀘스트에 저장 
			request.setAttribute("kind1", kindList1[index]);
			request.setAttribute("kind3", kindList3[index2]);
			request.setAttribute("useyn", useynList[index3]);
			request.setAttribute("productVO", pvo); 
			return "admin/product/shortproductDetail";
		}
	}
	
	@RequestMapping("adminProductUpdateForm")
	public String adminProductUpdateForm(@RequestParam("pseq") int pseq, HttpServletRequest request, Model model) {
		ProductVO pvo = as.productDetail(pseq);
		model.addAttribute("productVO",pvo);
		String kindList1[] = {"스페셜&할인팩", "프리미엄", "와퍼", "주니어&버거", "올데이킹&치킨버거", "사이드", "음료&디저트", "독퍼"};
		request.setAttribute("kindList1", kindList1);
		int index = Integer.parseInt(pvo.getKind1());
		request.setAttribute("kind", kindList1[index-1]);
		return "admin/product/productUpdate";
	}
	
	@RequestMapping("adminShortProductUpdateForm")
	public String adminShortProductUpdateForm(@RequestParam("pseq") int pseq, HttpServletRequest request, Model model) {
		ProductVO pvo = as.productDetail(pseq);
		model.addAttribute("productVO",pvo);
		String kindList1[] = {"스페셜&할인팩", "프리미엄", "와퍼", "주니어&버거", "올데이킹&치킨버거", "사이드", "음료&디저트", "독퍼"};
		request.setAttribute("kindList1", kindList1);
		int index = Integer.parseInt(pvo.getKind1());
		request.setAttribute("kind", kindList1[index-1]);
		return "admin/product/shortproductUpdate";
	}
	
	@RequestMapping("/selectimg")
	public String selectimg() {
		return "admin/product/selectimg";
	}
	
	@RequestMapping(value="/fileupload", method = RequestMethod.POST)
	public String fileupload(Model model, HttpServletRequest request) {
		String path = context.getRealPath("images");
		
		try {
			MultipartRequest multi = new MultipartRequest(
					request, path, 5*1024*1024, "UTF-8", new DefaultFileRenamePolicy()
			);
			
			// 전송된 파일은 업로드 되고, 파일 이름은 모델에 저장합니다.
			model.addAttribute("image", multi.getFilesystemName("image"));
			model.addAttribute("originalFilename", multi.getFilesystemName("image"));
		} catch (IOException e) {e.printStackTrace();
		}
		return "admin/product/completupload";
	}
	
	@RequestMapping(value="/adminShortProductUpdate", method = RequestMethod.POST)
	public String adminShortProductUpdate(HttpServletRequest request, @ModelAttribute("ProductVO")
			ProductVO p, Model model) {				
		ProductVO pvo = new ProductVO();
		int pseq=0;
		String savePath = context.getRealPath("/images");
		MultipartRequest multi;
		try {
			multi = new MultipartRequest(
					request, savePath , 5*1024*1024,  "UTF-8", new DefaultFileRenamePolicy() );
			pvo.setPseq(Integer.parseInt(multi.getParameter("pseq")));
			pseq=Integer.parseInt(multi.getParameter("pseq"));
			pvo.setKind1(multi.getParameter("kind1"));
			pvo.setKind2(multi.getParameter("kind2"));
			pvo.setKind3(multi.getParameter("kind3"));
			pvo.setPname(multi.getParameter("pname"));
			pvo.setPrice1(0);
			pvo.setPrice2(0);
			pvo.setPrice3(0);
			pvo.setContent("");
			pvo.setUseyn(multi.getParameter("useyn"));
			if(multi.getFilesystemName("image") == null)
				pvo.setImage(multi.getParameter("oldImage"));
			else
				pvo.setImage(multi.getFilesystemName("image"));
		} catch (IOException e) {e.printStackTrace();	}
		as.updateProduct(pvo);
		return "redirect:/adminShortProductDetail?pseq="+pseq;
	}	
	
	@RequestMapping(value="/adminProductUpdate", method = RequestMethod.POST)
	public String adminProductUpdate(HttpServletRequest request, @ModelAttribute("ProductVO")
			ProductVO p, Model model) {				
		ProductVO pvo = new ProductVO();
		int pseq=0;
		String savePath = context.getRealPath("/images");
		MultipartRequest multi;
		try {
			multi = new MultipartRequest(
					request, savePath , 5*1024*1024,  "UTF-8", new DefaultFileRenamePolicy() );
			pvo.setPseq(Integer.parseInt(multi.getParameter("pseq")));
			pseq=Integer.parseInt(multi.getParameter("pseq"));
			pvo.setKind1(multi.getParameter("kind1"));
			pvo.setKind2(multi.getParameter("kind2"));
			pvo.setKind3(multi.getParameter("kind3"));
			pvo.setPname(multi.getParameter("pname"));
			pvo.setPrice1(Integer.parseInt(multi.getParameter("price1")));
			pvo.setPrice2(Integer.parseInt(multi.getParameter("price2")));
			pvo.setPrice3(Integer.parseInt(multi.getParameter("price3")));
			pvo.setContent(multi.getParameter("content"));
			pvo.setUseyn(multi.getParameter("useyn"));
			if(multi.getFilesystemName("image") == null)
				pvo.setImage(multi.getParameter("oldImage"));
			else
				pvo.setImage(multi.getFilesystemName("image"));
		} catch (IOException e) {e.printStackTrace();	}
		as.updateProduct(pvo);
		return "redirect:/adminProductDetail?pseq="+pseq;
	}
}
