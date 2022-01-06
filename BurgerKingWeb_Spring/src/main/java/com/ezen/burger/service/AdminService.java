package com.ezen.burger.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.burger.dao.IAdminDao;
import com.ezen.burger.dto.AdminVO;

import com.ezen.burger.dto.EventVO;

import com.ezen.burger.dto.MemberVO;

import com.ezen.burger.dto.Paging;
import com.ezen.burger.dto.ProductVO;
import com.ezen.burger.dto.QnaVO;

@Service
public class AdminService {
	@Autowired
	IAdminDao adao;

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
		adao.deleteMember(mseq);
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
		if(list2.size()!=0) {
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


}
