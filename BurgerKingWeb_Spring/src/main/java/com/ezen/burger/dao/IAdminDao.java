package com.ezen.burger.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.ezen.burger.dto.AdminVO;
import com.ezen.burger.dto.MemberVO;
import com.ezen.burger.dto.Paging;

@Mapper
public interface IAdminDao {

	AdminVO adminCheck(String id);

	int getAllCount(String tableName, String fieldName, String key);

	ArrayList<MemberVO> listMember(Paging paging, String key);

	void adminMemberDelete(int mseq);
	
}
