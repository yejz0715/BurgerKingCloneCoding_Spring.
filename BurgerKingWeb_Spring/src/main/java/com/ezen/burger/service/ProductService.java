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

	public ArrayList<ProductVO> getProductdetail(int pseq) {
		return pdao.getProductdetail(pseq);
	}

	public ArrayList<ProductVO> getProductkind(String kind1, String kind2) {
		return pdao.getProductkind(kind1, kind2);
	}

	public ArrayList<ProductVO> getProductList(String kind1) {
		// TODO Auto-generated method stub
		return null;
	}
}
