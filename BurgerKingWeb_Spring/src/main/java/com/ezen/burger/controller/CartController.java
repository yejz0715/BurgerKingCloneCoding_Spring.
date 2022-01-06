package com.ezen.burger.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.burger.dto.CartVO;
import com.ezen.burger.dto.GuestVO;
import com.ezen.burger.dto.MemberVO;
import com.ezen.burger.dto.ProductVO;
import com.ezen.burger.dto.orderVO;
import com.ezen.burger.dto.subproductOrderVO;
import com.ezen.burger.service.CartService;
import com.ezen.burger.service.OrderService;
import com.ezen.burger.service.ProductService;

@Controller
public class CartController {
	@Autowired
	CartService cs;

	@Autowired
	OrderService os;
	
	@Autowired
	ProductService ps;
	
	@RequestMapping(value="/deliveryCartForm")
	public ModelAndView deliveryCartForm(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		
		if(session.getAttribute("memberkind") != null) {
			int memberKind = (int)session.getAttribute("memberkind");
			System.out.println("memberkind" + memberKind);
			if(memberKind == 1) {
				MemberVO mvo = (MemberVO)session.getAttribute("loginUser");
				
				//해당 접속 회원의 주문 목록과 카트 목록 가져오기
				ArrayList<orderVO> list1 = os.getOrderList(mvo.getId());
				ArrayList<CartVO> list2 = cs.selectCart( mvo.getId() );
				
				// 가져온 카트 목록에서 가격 총합 계산 
				int totalPrice = 0; 
				for(CartVO cvo : list2) totalPrice += cvo.getPrice1() * cvo.getQuantity();
				
				// 해당 접속 회원의 추가 재료의 목록을 가져오기
				ArrayList<subproductOrderVO> spovo = ps.selectSubProductOrder(mvo.getMseq());
				
				// 추가 재료의 가격까지 총 가격으로 계산
				for(int i = 0; i < spovo.size(); i++) {
					totalPrice += spovo.get(i).getAddprice();
				}
		
				// 해당 값을 전송
				mav.addObject("totalPrice", totalPrice);
				mav.addObject("spseqAm", spovo);
				mav.addObject("ovo", list1);
				mav.addObject("cvo", list2);
				mav.setViewName("delivery/cart");
			}else if(memberKind == 2) {
				GuestVO gvo = (GuestVO)session.getAttribute("loginUser");
				//해당 접속 회원의 주문 목록과 카트 목록 가져오기
				ArrayList<orderVO> list1 = os.getOrderList(gvo.getId());
				ArrayList<CartVO> list2 = cs.selectCart( gvo.getId() );
				
				// 가져온 카트 목록에서 가격 총합 계산 
				int totalPrice = 0; 
				for(CartVO cvo : list2) totalPrice += cvo.getPrice1() * cvo.getQuantity();
				
				// 해당 접속 회원의 추가 재료의 목록을 가져오기
				ArrayList<subproductOrderVO> spovo = ps.selectSubProductOrder2(gvo.getGseq());
				
				// 추가 재료의 가격까지 총 가격으로 계산
				for(int i = 0; i < spovo.size(); i++) {
					totalPrice += spovo.get(i).getAddprice();
				}
		
				// 해당 값을 전송
				mav.addObject("totalPrice", totalPrice);
				mav.addObject("spseqAm", spovo);
				mav.addObject("ovo", list1);
				mav.addObject("cvo", list2);
				mav.setViewName("delivery/cart");
			}else {
				mav.setViewName("redirect:/loginForm");
			}
		}else {
			mav.setViewName("redirect:/loginForm");
		}
		
		
		return mav;
	}
	
	@RequestMapping(value="noMeterialCart")
	public String noMeterialCart(@RequestParam("pseq") int pseq, HttpServletRequest request) {
		HttpSession session = request.getSession();
		MemberVO mvo;
		GuestVO gvo;
		CartVO cvo = new CartVO();
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
		cs.insertCart(cvo);
		
		return "redirect:/deliveryCartForm";
	}
}
