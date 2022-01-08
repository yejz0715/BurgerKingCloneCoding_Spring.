package com.ezen.burger.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.burger.dao.IAddressDao;
import com.ezen.burger.dto.AddressVO;
import com.ezen.burger.dto.MemberVO;
import com.ezen.burger.dto.MyAddressVO;

@Service
public class AddressService {
	@Autowired
	IAddressDao adao;

	public MyAddressVO getMyAddress(int mseq) {
		return adao.getMyAddress(mseq);
	}

	public ArrayList<AddressVO> selectAddressByDong(String dong) {
		return adao.selectAddressByDong(dong);
	}

	public void setUserAddress(MyAddressVO mavo) {
		adao.setUserAddress(mavo);
	}

	public void updateUserAddress(MyAddressVO mavo) {
		adao.updateUserAddress(mavo);
	}

	public void setGuestAddress(String address, int gseq) {
		adao.setGuestAddress(address, gseq);
	}
}
