package com.ezen.burger.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.burger.dao.ICartDao;
import com.ezen.burger.dto.CartVO;

@Service
public class CartService {
	@Autowired
	ICartDao cdao;

	public ArrayList<CartVO> selectCart(String id) {
		return cdao.selectCart(id);
	}
}
