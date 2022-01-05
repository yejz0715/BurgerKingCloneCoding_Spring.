package com.ezen.burger.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.burger.dao.IAdminDao;
import com.ezen.burger.dto.AdminVO;

import com.ezen.burger.dto.EventVO;

import com.ezen.burger.dto.MemberVO;

import com.ezen.burger.dto.Paging;
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

	public void adminMemberDelete(int mseq) {
		adao.adminMemberDelete(mseq);
	}

	public ArrayList<QnaVO> listQna(Paging paging, String key) {
		return adao.listQna(paging, key);
	}

	public void adminQnaDelete(int qseq) {
		adao.adminQnaDelete(qseq);
		
	}

	public ArrayList<EventVO> listEvent(Paging paging, String key) {
		return adao.listEvent(paging, key);
	}

	public void deleteEvent(int eseq) {
		adao.deleteEvent(eseq);
		
	}

}
