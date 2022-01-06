package com.ezen.burger.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.ezen.burger.dto.AdminVO;

import com.ezen.burger.dto.EventVO;

import com.ezen.burger.dto.MemberVO;

import com.ezen.burger.dto.Paging;
import com.ezen.burger.dto.ProductVO;
import com.ezen.burger.dto.QnaVO;

@Mapper
public interface IAdminDao {

	AdminVO adminCheck(String id);

	int getAllCount(String tableName, String fieldName, String key);

	ArrayList<MemberVO> listMember(Paging paging, String key);

	void deleteMember(int mseq);
	
	ArrayList<EventVO> listEvent(Paging paging, String key);

	void deleteEvent(int eseq);

	ArrayList<QnaVO> listQna(Paging paging, String key);

	void deleteQna(int qseq);

	ArrayList<ProductVO> listShortProduct(Paging paging, String key);

	ArrayList<ProductVO> listProduct(Paging paging, String key);

	void deleteProduct(int pseq);

	void insertProduct(ProductVO pvo);

	void insertEvent(EventVO evo);

	void updateEvent(EventVO evo);

	
}
