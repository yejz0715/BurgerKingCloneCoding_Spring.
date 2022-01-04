package com.ezen.burger.dao;

import org.apache.ibatis.annotations.Mapper;

import com.ezen.burger.dto.MemberVO;

@Mapper
public interface IMemberDao {
	public MemberVO getMember(String id);
	public MemberVO findMember(String name, String phone);
	public MemberVO findPwd(String name, String id);
	public void updatePwd(String mseq, String pwd);
	public void adminMemberDelete(int mseq);
}
