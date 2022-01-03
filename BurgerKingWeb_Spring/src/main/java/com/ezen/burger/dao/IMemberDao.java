package com.ezen.burger.dao;

import org.apache.ibatis.annotations.Mapper;

import com.ezen.burger.dto.MemberVO;

@Mapper
public interface IMemberDao {
	public MemberVO getMember(String id);
}
