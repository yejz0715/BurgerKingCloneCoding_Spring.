package com.ezen.burger.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.burger.dao.IAdminDao;
import com.ezen.burger.dao.IMemberDao;
import com.ezen.burger.dao.IOrderDao;
import com.ezen.burger.dto.AdminVO;
import com.ezen.burger.dto.EventVO;
import com.ezen.burger.dto.MemberVO;
import com.ezen.burger.dto.Paging;
import com.ezen.burger.dto.ProductVO;
import com.ezen.burger.dto.QnaVO;
import com.ezen.burger.dto.orderVO;

@Service
public class AdminService {
	@Autowired
	IAdminDao adao;
	
	@Autowired
	IMemberDao mdao;
	
	@Autowired
	IOrderDao odao;

	public AdminVO adminCheck(String id) {
		return adao.adminCheck(id);
	}

	public int getAllCount(String tableName, String fieldName, String key) {
		return adao.getAllCount(tableName,fieldName,key);
	}

	public ArrayList<MemberVO> listMember(Paging paging, String key) {
		return adao.listMember(paging, key);
	}

	public void deleteMember(int mseq) {
		// 해당 mseq의 회원 정보를 불러온다.
		MemberVO mvo = mdao.getMember_mseq(mseq);
		
		// 이후 해당 아이디의 oseq 값들을 조회하여
		// 해당 oseq 값을 가진 order_detail을 삭제한다.
		int[] oseq = odao.getOseqs(mvo.getId());
		for(int i = 0; i < oseq.length; i++) {
			mdao.deleteOrderDetail(oseq[i]);
		}
		// 이후 해당 아이디의 orders, cart, qna를 삭제한다.
		mdao.deleteOrders(mvo.getId());
		mdao.deleteCart(mvo.getId());
		mdao.deleteQna(mvo.getId());
		
		// 그 뒤 해당 mseq값을 가진 주소를 삭제하고 최종적으로 회원을 삭제한다.
		mdao.deleteMyaddress(mvo.getMseq());
		mdao.deleteMember(mvo.getMseq());
	}

	public ArrayList<QnaVO> listQna(Paging paging, String key) {
		return adao.listQna(paging, key);
	}

	public void deleteQna(int qseq) {
		adao.deleteQna(qseq);
		
	}

	public ArrayList<EventVO> listEvent(Paging paging, String key) {
		return adao.listEvent(paging, key);
		
	}

	public void deleteEvent(int eseq) {
		adao.deleteEvent(eseq);
		
	}

	public ArrayList<ProductVO> listShortProduct(Paging paging, String key) {
		return adao.listShortProduct(paging, key);
	}

	public ArrayList<ProductVO> listProduct(Paging paging, String key) {
		return adao.listProduct(paging, key);
	}

	public void deleteProduct(int pseq) {
		adao.deleteProduct(pseq);
		
	}

	public void insertProduct(ProductVO pvo) {
		adao.insertProduct(pvo);
		
	}

	public void insertEvent(EventVO evo) {
		adao.insertEvent(evo);
	}

	public void updateEvent(EventVO evo) {
		adao.updateEvent(evo);        
	}

	public int checkShortProductYN(String k1, String k2, String k3) {
		int result = 1;
		ArrayList<ProductVO> list1=adao.selectProduct1(k1);
		if(list1.size()==0) {
			result=2; return result;
		}
		
		ArrayList<ProductVO> list2=adao.selectProduct2(k1,k2);
		if(list2.size()==0) {
			result=3; return result;
		}
		if(k3.equals("4")) {
			result=4; return result;
		}
		return result;
	}

	public int checkShortProductYN2(String k1, String k2) {
		int result = 1;
		ArrayList<ProductVO> list2=adao.selectProduct2(k1,k2);
		if(list2.size()!=0) {
			result=2; return result;
		}
		return result;
	}

	public ProductVO productDetail(int pseq) {
		return adao.productDetail(pseq);
	}

	public void updateProduct(ProductVO pvo) {
		adao.updateProduct(pvo);
		
	}

	public ArrayList<orderVO> listOrder(Paging paging, String key, String kind) {
		ArrayList<orderVO> ovo = new ArrayList<orderVO>();
		if(kind.equals("1")) {
			ovo = adao.listOrder(paging, key);
		}else {
			ovo = adao.listOrder2(paging, key);
		}
		return ovo;
	}

	public void updateOrderResult(String odseq, String step) {
		adao.updateOrderResult(Integer.parseInt(odseq), step);
	}

	public String getResult(String odseq) {
		String result = adao.getResult(odseq);
		result = String.valueOf(Integer.parseInt(result) + 1);
		return result;
	}

	public int getShortProductAllCount(String key) {
		return adao.getShortProductAllCount(key);
	}
	
	public int getProductAllCount(String key) {
		return adao.getProductAllCount(key);
	}
}
