package com.ezen.burger.dao;

import javax.validation.Valid;

import org.apache.ibatis.annotations.Mapper;

import com.ezen.burger.dto.GuestVO;
import com.ezen.burger.dto.MemberVO;

@Mapper
public interface IMemberDao {
	public MemberVO getMember(String id);
	public MemberVO findMember(String name, String phone);
	public MemberVO findPwd(String name, String id);
	public void updatePwd(String mseq, String pwd);
	public MemberVO getMember_mseq(int mseq);
	public void updateMember(@Valid MemberVO mvo);
	public int selectGseq();
	public void deleteMember(int mseq);
	public void deleteMyaddress(int mseq);
	public void insertMember(MemberVO membervo);
	public MemberVO joinMember(String id, String name, String phone, String pwd);
	public void insertGuest(GuestVO gvo);
	public void lastDateUpdate(int mseq);
}
