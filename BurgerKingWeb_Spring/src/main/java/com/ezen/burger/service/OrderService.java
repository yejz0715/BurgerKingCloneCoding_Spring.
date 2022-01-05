package com.ezen.burger.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.burger.dao.IOrderDao;
import com.ezen.burger.dto.orderVO;

@Service
public class OrderService {
	@Autowired
	IOrderDao odao;

	public ArrayList<orderVO> getOrderList(String id) {
		return odao.getOrderList(id);
	}
}
