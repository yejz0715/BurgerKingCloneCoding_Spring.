package com.ezen.burger.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.burger.dao.IMemberDao;
import com.ezen.burger.dto.GuestVO;
import com.ezen.burger.dto.MemberVO;

@Service
public class MemberService {
	@Autowired
	IMemberDao mdao;

	public MemberVO getMember(String id) {
		return mdao.getMember(id);
	}

	public MemberVO findMember(String name, String phone) {
		return mdao.findMember(name, phone);
	}

	public MemberVO findPwd(String name, String id) {
		return mdao.findPwd(name, id);
	}

	public void updatePwd(String mseq, String pwd) {
		mdao.updatePwd(mseq, pwd);
	}

	public void adminMemberDelete(int mseq) {
		mdao.adminMemberDelete(mseq);
		
	}

	public GuestVO guestSessionLogin(String name, String phone, String pwd) {
		int gseq = mdao.selectGseq();
		String id = "Non" + gseq;
		GuestVO gvo = new GuestVO();
		gvo.setGseq(gseq);
		gvo.setId(id);
		gvo.setName(name);
		gvo.setPhone(phone);
		gvo.setPwd(pwd);
		gvo.setMemberkind(2);
		
		return gvo;
	}
}
