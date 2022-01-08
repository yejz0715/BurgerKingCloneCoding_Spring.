package com.ezen.burger.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.burger.dto.CartVO;
import com.ezen.burger.dto.GuestVO;
import com.ezen.burger.dto.MemberVO;
import com.ezen.burger.dto.MyAddressVO;
import com.ezen.burger.dto.orderVO;
import com.ezen.burger.dto.subproductOrderVO;
import com.ezen.burger.service.AddressService;
import com.ezen.burger.service.CartService;
import com.ezen.burger.service.OrderService;
import com.ezen.burger.service.ProductService;

@Controller
public class OrderCotroller {
	@Autowired
	OrderService os;
	
	@Autowired
	CartService cs;
	
	@Autowired
	ProductService ps;
	
	@Autowired
	AddressService as;
	
	@RequestMapping(value="/deliveryOrderList")
	public ModelAndView deliveryOrderList(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		if(session.getAttribute("memberkind") != null && session.getAttribute("loginUser") != null) {
			int memberKind = (int)session.getAttribute("memberkind");
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
				
				// 로그인 회원의 주소 정보 호출
				MyAddressVO mavo = as.getMyAddress(mvo.getMseq());
		
				// 해당 값을 전송
				mav.addObject("totalPrice", totalPrice);
				mav.addObject("spseqAm", spovo);
				mav.addObject("userPhone", mvo.getPhone());
				mav.addObject("Myaddress", mavo);
				mav.addObject("ovo", list1);
				mav.addObject("cvo", list2);
				mav.setViewName("delivery/orderList");
			}else if(memberKind == 2) {
				GuestVO gvo = (GuestVO)session.getAttribute("loginUser");
				
			}else {
				mav.setViewName("redirect:/loginForm");
			}
		}else {
			mav.setViewName("redirect:/loginForm");
		}
		return mav;
	}
}
