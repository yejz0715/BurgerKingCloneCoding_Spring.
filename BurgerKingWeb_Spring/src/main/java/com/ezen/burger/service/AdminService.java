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


}
