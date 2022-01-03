package com.ezen.burger.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.burger.dao.ICartDao;

@Service
public class CartService {
	@Autowired
	ICartDao cdao;
}
