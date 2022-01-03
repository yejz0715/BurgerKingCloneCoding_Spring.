package com.ezen.burger.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.burger.dao.IProductDao;
import com.ezen.burger.dto.ProductVO;

@Service
public class ProductService {
	@Autowired
	IProductDao pdao;

	public ArrayList<ProductVO> getProduct(String kind1) {
		return pdao.getProduct(kind1);
	}
}
