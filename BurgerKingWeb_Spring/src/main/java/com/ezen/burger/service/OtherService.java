package com.ezen.burger.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.burger.dao.IOtherDao;
import com.ezen.burger.dto.ProductVO;

@Service
public class OtherService {
	@Autowired
	IOtherDao odao;

	public ArrayList<ProductVO> getFaq(Object fnum1) {
		return getFaq(fnum1);
	}
}
