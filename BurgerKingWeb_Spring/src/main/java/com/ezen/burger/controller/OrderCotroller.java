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
	
	// 주문 페이지로 이동
	@RequestMapping(value="/deliveryOrderList")
	public ModelAndView deliveryOrderList(HttpServletRequest request,
			@RequestParam(value="message", required = false) String message) {
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
				for(orderVO ovo : list1) totalPrice += ovo.getPrice1() * ovo.getQuantity();
				
				// 해당 접속 회원의 추가 재료의 목록을 가져오기
				ArrayList<subproductOrderVO> spovo = ps.selectSubProductOrder3(mvo.getMseq());
				
				// 추가 재료의 가격까지 총 가격으로 계산
				for(int i = 0; i < spovo.size(); i++) {
					int result = ps.getResult(spovo.get(i).getOdseq());
					if(result == 1 || result == 2 || result == 3) {
						totalPrice += spovo.get(i).getAddprice();
					}
				}

				// 로그인 회원의 주소 정보 호출
				MyAddressVO mavo = as.getMyAddress(mvo.getMseq());
		
				// 해당 값을 전송
				if(message !=null) {
					mav.addObject("message", message);
				}
				mav.addObject("totalPrice", totalPrice);
				mav.addObject("spseqAm", spovo);
				mav.addObject("userPhone", mvo.getPhone());
				mav.addObject("Myaddress", mavo);
				mav.addObject("ovo", list1);
				mav.addObject("cvo", list2);
				mav.setViewName("delivery/orderList");
			}else if(memberKind == 2) {
				GuestVO gvo = (GuestVO)session.getAttribute("loginUser");
				ArrayList<orderVO> list1 = os.getOrderListByGuest(gvo.getId());
				// 가져온 카트 목록에서 가격 총합 계산 
				int totalPrice = 0; 
				for(orderVO ovo : list1) totalPrice += ovo.getPrice1() * ovo.getQuantity();
				
				// 해당 접속 회원의 추가 재료의 목록을 가져오기
				ArrayList<subproductOrderVO> spovo = ps.selectSubProductOrder4(gvo.getGseq());
				
				// 추가 재료의 가격까지 총 가격으로 계산
				for(int i = 0; i < spovo.size(); i++) {
					int result = ps.getResult(spovo.get(i).getOdseq());
					if(result == 1 || result == 2 || result == 3) {
						totalPrice += spovo.get(i).getAddprice();
					}
				}
				
				// 주문 페이지에 띄울 로그인한 유저의 주소지 
				MyAddressVO mavo = new MyAddressVO();
				mavo.setAddress(gvo.getAddress());
				
				// 해당 값을 전송
				if(message !=null) {
					mav.addObject("message", message);
				}
				mav.addObject("totalPrice", totalPrice);
				mav.addObject("spseqAm", spovo);
				mav.addObject("userPhone", gvo.getPhone());
				mav.addObject("Myaddress", mavo);
				mav.addObject("ovo", list1);
				mav.addObject("mkind", session.getAttribute("memberkind"));
				mav.setViewName("delivery/orderList");
			}else {
				mav.setViewName("redirect:/loginForm");
			}
		}else {
			mav.setViewName("redirect:/loginForm");
		}
		return mav;
	}
	
	// 카트 목록 주문하기
	@RequestMapping(value="/deliveryCartOrder")
	public ModelAndView deliveryCartOrder(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		if(session.getAttribute("memberkind") != null && session.getAttribute("loginUser") != null) {
			int memberKind = (int)session.getAttribute("memberkind");
			if(memberKind == 1) {
				MemberVO mvo = (MemberVO)session.getAttribute("loginUser");
				MyAddressVO mavo = as.getMyAddress(mvo.getMseq());
				
				// 주소가 없으면 주소 설정 창으로 이동
				if(mavo.getAddress() == null || mavo.getAddress().equals("")) {
					ArrayList<orderVO> list1 = os.getOrderList(mvo.getId());
					ArrayList<CartVO> list2 = cs.selectCart( mvo.getId() );	
					
					mav.addObject("ovo", list1);
					mav.addObject("cvo", list2);
					mav.setViewName("delivery/addressSet");
				}else {
					// 주문자 아이디로 검색한 카트 목록(지금 주문 처리할) 목록을 먼저 조회합니다
					ArrayList<CartVO> list = cs.selectCart(mvo.getId());
					
					// 카트 목록의 총가격을 계산하여 12000원 미만이면 주문을 실행하지 않는다.
					int totalPrice = 0;
					for(CartVO cvo : list) {
						totalPrice += cvo.getPrice1() * cvo.getQuantity();
					}
					
					// 해당 접속 회원의 추가 재료의 목록을 가져오기
					ArrayList<subproductOrderVO> spovo = ps.selectSubProductOrder(mvo.getMseq());
					
					// 추가 재료의 가격까지 총 가격으로 계산
					for(int j = 0; j < list.size(); j++) {
						for(int i = 0; i < spovo.size(); i++) {
							if(list.get(j).getCseq() == spovo.get(i).getCseq()) {
								totalPrice += spovo.get(i).getAddprice();
							}
						}
					}
					
					if(totalPrice < 12000) {
						mav.addObject("message", "가격이 12000원 이상이어야 주문 가능합니다.");
						mav.setViewName("redirect:/deliveryCartForm");
						return mav;
					}
					
					// 추출한 list 와 주문자의 아디를 갖고 OrderDao 에 가서 오더 와 오더 디테일에 데이터를 추가합니다.
					os.insertOrder(list, mvo.getId());
					
					mav.setViewName("redirect:/deliveryOrderList");
				}
			}else if(memberKind == 2) {
				GuestVO gvo = (GuestVO)session.getAttribute("loginUser");
				// 주소가 없으면 주소 설정 창으로 이동
				if(gvo.getAddress() == null || gvo.getAddress().equals("")) {
					mav.setViewName("delivery/addressSet");
				}else {
					// 주문자 아이디로 검색한 카트 목록(지금 주문 처리할) 목록을 먼저 조회합니다
					ArrayList<CartVO> list = (ArrayList<CartVO>)session.getAttribute("guestCartList");
					
					// 카트 목록의 총가격을 계산하여 12000원 미만이면 주문을 실행하지 않는다.
					int totalPrice = 0;
					for(CartVO cvo : list) {
						totalPrice += cvo.getPrice1() * cvo.getQuantity();
					}
					
					// 해당 접속 회원의 추가 재료의 목록을 가져오기
					ArrayList<subproductOrderVO> spovo = ps.selectSubProductOrder2(gvo.getGseq());
					
					// 추가 재료의 가격까지 총 가격으로 계산
					for(int j = 0; j < list.size(); j++) {
						for(int i = 0; i < spovo.size(); i++) {
							if(list.get(j).getCseq() == spovo.get(i).getCseq()) {
								totalPrice += spovo.get(i).getAddprice();
							}
						}
					}
					
					if(totalPrice < 12000) {
						mav.addObject("message", "가격이 12000원 이상이어야 주문 가능합니다.");
						mav.setViewName("redirect:/deliveryCartForm");
						return mav;
					}
					
					
					// 추출한 list 와 주문자의 아디를 갖고 OrderDao 에 가서 오더 와 오더 디테일에 데이터를 추가합니다.
					os.insertOrderByGuest(list, gvo.getId());
					
					// 비회원의 카트세션을 초기화한다.
					list.clear();
					session.setAttribute("guestCartList", list);
					mav.setViewName("redirect:/deliveryOrderList");
				}
			}else {
				mav.setViewName("redirect:/loginForm");
			}
		}else {
			mav.setViewName("redirect:/loginForm");
		}
		return mav;
	}
	
	// 비회원 주문 내역창 불러오기
	@RequestMapping(value="/nonOrderList")
	public ModelAndView nonOrderList(HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		int oseq = Integer.parseInt(request.getParameter("oseq"));
		int pwd = Integer.parseInt(request.getParameter("pwd2"));
		
		if(request.getParameter("oseq")==null || request.getParameter("pwd2")==null) {
			mav.setViewName("redirect:/loginForm");
			return mav;
		}		
		// 입력받은 주문번호의 내역을 가져오기
		ArrayList<orderVO> ovo = os.getOrderByOseq(oseq);
		
		// 해당 주문이 없거나, 비번이 없을 때,
		if(ovo.size() == 0) {
			mav.addObject("message2", "해당 주문이 없습니다.");
			mav.setViewName("member/loginForm");
		}else if(Integer.parseInt(ovo.get(0).getPwd()) != pwd) {
			mav.addObject("message2", "비밀번호가 다릅니다.");
			mav.setViewName("member/loginForm");
		}else if(Integer.parseInt(ovo.get(0).getPwd()) == pwd) {
			int totalPrice = 0; 
			for(orderVO order : ovo) totalPrice += order.getPrice1() * order.getQuantity();
			
			// 해당 접속 회원의 추가 재료의 목록을 가져오기
			ArrayList<subproductOrderVO> spovo = ps.selectSubProductOrder5(oseq);
			
			for(int i = 0; i < spovo.size(); i++) {
				int result = ps.getResult(spovo.get(i).getOdseq());
				if(result == 1 || result == 2 || result == 3) {
					totalPrice += spovo.get(i).getAddprice();
				}
			}
			
			mav.addObject("totalPrice", totalPrice);
			mav.addObject("spseqAm", spovo);
			mav.addObject("address", ovo.get(0).getAddress());
			mav.addObject("userPhone", ovo.get(0).getPhone());
			mav.addObject("result", ovo.get(0).getResult());
			mav.addObject("ovo", ovo);
			mav.setViewName("delivery/guestOrderList");
		}else {
			mav.addObject("message2", "기타 오류로 조회가 불가능합니다. 관리자에게 문의하세요.");
			mav.setViewName("member/loginForm");
		}
		return mav;
	}
	
	// 주문 삭제
	@RequestMapping(value="/orderDelete")
	public ModelAndView orderDelete(HttpServletRequest request,
			@RequestParam("odseq") String odseq) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		String result = os.getOrderDetail(odseq);
		if(result.equals("3")) {
			mav.addObject("message", "배달이 진행중이라 취소가 불가능합니다.");
			mav.setViewName("redirect:/deliveryOrderList");
			return mav;
		}
		if(session.getAttribute("memberkind") != null && session.getAttribute("loginUser") != null) {
			os.deleteOrder2(odseq);
			mav.setViewName("redirect:/deliveryOrderList");
		}else {
			mav.setViewName("redirect:/loginForm");
		}
		return mav;
	}
}
