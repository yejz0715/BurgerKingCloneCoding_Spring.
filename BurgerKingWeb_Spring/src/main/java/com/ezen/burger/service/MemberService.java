package com.ezen.burger.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.burger.dao.IMemberDao;
import com.ezen.burger.dao.IOrderDao;
import com.ezen.burger.dto.GuestVO;
import com.ezen.burger.dto.MemberVO;

@Service
public class MemberService {
	@Autowired
	IMemberDao mdao;
	
	@Autowired
	IOrderDao odao;

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

	public MemberVO getMember_mseq(int mseq) {
		return mdao.getMember_mseq(mseq);
	}

	public void updateMember(MemberVO mvo) {
		mdao.updateMember(mvo);
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

	public void deleteMember(int mseq) {
		MemberVO mvo = mdao.getMember_mseq(mseq);
		int[] oseq = odao.getOseqs(mvo.getId());
		for(int i = 0; i < oseq.length; i++) {
			mdao.deleteOrderDetail(oseq[i]);
		}
		mdao.deleteOrders(mvo.getId());
		mdao.deleteCart(mvo.getId());
		mdao.deleteQna(mvo.getId());
		mdao.deleteMyaddress(mvo.getMseq());
		mdao.deleteMember(mvo.getMseq());
	}

	public void insertMember( MemberVO membervo) {
		mdao.insertMember(membervo);
	}

	public MemberVO joinMember(String id, String name, String phone, String pwd) {
		return mdao.joinMember(id, name, phone, pwd);
	}

	public void insertGuest(GuestVO gvo) {
		mdao.insertGuest(gvo);
	}

	public void lastDateUpdate(int mseq) {
		mdao.lastDateUpdate(mseq);
	}
}
