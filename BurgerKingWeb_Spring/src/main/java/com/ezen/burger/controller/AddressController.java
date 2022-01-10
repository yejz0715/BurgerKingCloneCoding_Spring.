package com.ezen.burger.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.burger.dto.AddressVO;
import com.ezen.burger.dto.CartVO;
import com.ezen.burger.dto.GuestVO;
import com.ezen.burger.dto.MemberVO;
import com.ezen.burger.dto.MyAddressVO;
import com.ezen.burger.dto.ProductVO;
import com.ezen.burger.dto.orderVO;
import com.ezen.burger.service.AddressService;
import com.ezen.burger.service.CartService;
import com.ezen.burger.service.OrderService;
import com.ezen.burger.service.ProductService;

@Controller
public class AddressController {
	@Autowired
	AddressService as;
	@Autowired
	ProductService ps;
	@Autowired
	OrderService os;
	@Autowired
	CartService cs;
	
	// 주소찾기 팝업창 띄우기
	@RequestMapping(value="/findZipNum")
	public ModelAndView findZipNum(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String dong = request.getParameter("dong");
		if( dong != null) {
			if( dong.equals("") == false ) {
				ArrayList<AddressVO> list = as.selectAddressByDong(dong);
				mav.addObject("addressList", list);
			}
		}else {
			// 별일없이 다음 페이지로 이동
		}
		mav.setViewName("delivery/findZipNum");
		return mav;
	}
	
	// 입력한 주소 저장
	@RequestMapping(value="/myAddress")
	public ModelAndView myAddress(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		if(session.getAttribute("memberkind") != null) {
			int memberKind = (int)session.getAttribute("memberkind");
			String address = request.getParameter("addr1") + " " + request.getParameter("addr2");
			String zip_num = request.getParameter("zip_num");
			// 회원 종류 검사 (1:회원, 2:비회원)
			if(memberKind == 1) {
				MemberVO mvo = (MemberVO)session.getAttribute("loginUser");
				if(mvo == null) {
					mav.setViewName("redirect:/loginForm");
				}else {
					MyAddressVO mavo = new MyAddressVO();
					mavo.setZip_num(zip_num);
					mavo.setAddress(address);
					mavo.setMseq(mvo.getMseq());
					as.setUserAddress(mavo);
					mav.addObject("kind1", 1);
					mav.setViewName("redirect:/deliveryForm");
				}
			}else if(memberKind == 2){
				GuestVO gvo = (GuestVO)session.getAttribute("loginUser");
				if(gvo == null) {
					mav.setViewName("redirect:/loginForm");
				}else {
					gvo.setZip_num(zip_num);
					gvo.setAddress(address);
					as.setGuestAddress(address, gvo.getGseq(), gvo.getZip_num());
					session.setAttribute("loginUser", gvo);
					session.setAttribute("memberkind", gvo.getMemberkind());
					mav.addObject("kind1", 1);
					mav.setViewName("redirect:/deliveryForm");
				}
			}else {
				mav.setViewName("redirect:/loginForm");
			}
		}else {
			mav.setViewName("redirect:/loginForm");
		}
		return mav;
	}
	
	@RequestMapping(value="/myAddressForm")
	public ModelAndView myAddressForm(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		if(session.getAttribute("memberkind") != null && session.getAttribute("loginUser") != null) {
			if((int)session.getAttribute("memberkind") == 1) {
				MemberVO mvo = (MemberVO)session.getAttribute("loginUser");
				MyAddressVO mavo = as.getMyAddress(mvo.getMseq());
				ArrayList<orderVO> list1 = os.getOrderList(mvo.getId());
				ArrayList<CartVO> list2 = cs.selectCart( mvo.getId() );
				String addr = mavo.getAddress();
				String[] addrs= addr.split(" ");
				String addr1="";
				for(int i=0; i<3; i++) {
					addr1 += addrs[i] + " ";
				}
				String addr2="";
				for(int i=3; i<addrs.length; i++) {
					addr2 += addrs[i] + " ";
				}			 
				mav.addObject("addr1", addr1);
				mav.addObject("addr2", addr2);
				mav.addObject("zip_num", mavo.getZip_num());
				request.setAttribute("ovo", list1);
				request.setAttribute("cvo", list2);
				mav.setViewName("delivery/myaddress");
			}else if((int)session.getAttribute("memberkind") == 2) {
				GuestVO gvo = (GuestVO)session.getAttribute("loginUser");
			}
		}else {
			mav.setViewName("redirect:/loginForm");
		}
		return mav;
	}
	
	@RequestMapping(value="/updateAddress")
	public ModelAndView updateAddress(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		if(session.getAttribute("memberkind") != null && session.getAttribute("loginUser") != null) {
			int memberKind = (int)session.getAttribute("memberkind");
			String address = request.getParameter("addr1") + " " + request.getParameter("addr2");
			String zip_num = request.getParameter("zip_num");
			// 회원 종류 검사 (1:회원, 2:비회원)
			if(memberKind == 1) {
				MemberVO mvo = (MemberVO)session.getAttribute("loginUser");
				if(mvo == null) {
					mav.setViewName("redirect:/loginForm");
				}else {
					MyAddressVO mavo = new MyAddressVO();
					mavo.setZip_num(zip_num);
					mavo.setAddress(address);
					mavo.setMseq(mvo.getMseq());
					as.updateUserAddress(mavo);
					mav.setViewName("redirect:/deliveryMypageForm");
				}
			} else{
				mav.setViewName("redirect:/loginForm");
			}
		}
		return mav;
	}
}
