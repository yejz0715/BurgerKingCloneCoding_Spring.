package com.ezen.burger.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.burger.dto.CartVO;
import com.ezen.burger.dto.GuestVO;
import com.ezen.burger.dto.MemberVO;
import com.ezen.burger.dto.ProductVO;
import com.ezen.burger.dto.subProductVO;
import com.ezen.burger.service.CartService;
import com.ezen.burger.service.ProductService;

@Controller
public class ProductController {
	@Autowired
	ProductService ps;
	
	@Autowired
	CartService cs;
	
	@RequestMapping(value="menuListForm")
	public String menuListForm(Model model, @RequestParam("kind1") String kind1) {
		ArrayList<ProductVO> list = ps.getProduct(kind1);
		model.addAttribute("productList",list);
		return "product/menuList";
	}
	
	@RequestMapping(value="menudetailForm")
	public String menudetailForm(Model model, @RequestParam("pseq") int pseq) {
		ArrayList<ProductVO> list = ps.getProductdetail(pseq);
		ProductVO pvo = list.get(0);		
		ArrayList<ProductVO> list2 = ps.getProductkind(pvo.getKind1(), pvo.getKind2());
		model.addAttribute("pvo",pvo);
		model.addAttribute("list2",list2);
		return "product/productDetail";
	}
	
	@RequestMapping(value="/deliveryDetail")
	public ModelAndView deliveryDetail(HttpServletRequest request,
			@RequestParam("pseq") int pseq) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		if(session.getAttribute("memberkind") == null) {
			mav.setViewName("redirect:/loginForm");
			return mav;
		}
		// 해당 물품번호의 상위 목록을 불러오기 (해당 pseq의 kind3=4인 값을 불러온다.)
		ProductVO pvo = ps.getDeliverydetail(pseq);
		
		// 이후 해당 상위 목록의 세부 품목들(kind3=1~3)을 불러온다.
		ArrayList<ProductVO> list1 = ps.getProductkind(pvo.getKind1(), pvo.getKind2());
		
		// 불러온 목록들을 전송
		mav.addObject("list1", list1);
		mav.addObject("pvo", pvo);
		mav.setViewName("delivery/deliveryDetail");
		return mav;
	}
	
	@RequestMapping(value="/deliveryAddMaterial")
	public ModelAndView deliveryAddMaterial(HttpServletRequest request,
			@RequestParam("pseq") int pseq) {
		MemberVO mvo;
		GuestVO gvo;
		CartVO cvo = new CartVO();
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		
		if(session.getAttribute("loginUser") == null) {
			mav.setViewName("redirect:/loginForm");
			return mav;
		}
		ProductVO pvo = ps.getProducts(pseq);
		
		//로그인이 되어 있다면 로그인 정보에스 id 를 추출하고  상품번호와 아이디를  CartVO 에 담아서
		if((int)session.getAttribute("memberkind") == 1) {
			mvo = (MemberVO)session.getAttribute("loginUser");
			cvo.setId( mvo.getId() );   // 아이디 저장
		}else if((int)session.getAttribute("memberkind") == 2) {
			gvo = (GuestVO)session.getAttribute("loginUser");
			cvo.setId( gvo.getId() );   // 아이디 저장
		}
		
		cvo.setPseq(pseq);  // 상품번호저장
		
		// 추가할 재료 목록
		ArrayList<subProductVO> list = ps.getSubProduct();
		
		mav.addObject("subProductVO", list);
		mav.addObject("pvo", pvo);
		mav.addObject("cvo", cvo);
		mav.setViewName("delivery/addMeterial");
		return mav;
	}
}
