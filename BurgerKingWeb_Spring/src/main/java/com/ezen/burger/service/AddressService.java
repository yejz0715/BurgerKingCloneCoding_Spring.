package com.ezen.burger.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.burger.dao.IAddressDao;
import com.ezen.burger.dto.MyAddressVO;

@Service
public class AddressService {
	@Autowired
	IAddressDao adao;

	public MyAddressVO getMyAddress(int mseq) {
		return adao.getMyAddress(mseq);
	}
}
