package com.ezen.burger.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.burger.dao.IProductDao;
import com.ezen.burger.dto.ProductVO;
import com.ezen.burger.dto.subProductVO;
import com.ezen.burger.dto.subproductOrderVO;

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
		return pdao.getProductList(kind1);
	}

	public ArrayList<subproductOrderVO> selectSubProductOrder(int mseq) {
		return pdao.selectSubProductOrder(mseq);
	}

	public ProductVO getDeliverydetail(int pseq) {
		return pdao.getDeliveryDetail(pseq);
	}

	public ProductVO getProducts(int pseq) {
		return pdao.getProducts(pseq);
	}

	public ArrayList<subProductVO> getSubProduct() {
		return pdao.getSubProduct();
	}

	public ArrayList<subproductOrderVO> selectSubProductOrder2(int gseq) {
		return pdao.selectSubProductOrder2(gseq);
	}

	public subProductVO getSubProduct2(int spseq) {
		return pdao.getSubProduct2(spseq);
	}

	public void insertSubProductOrder(int cseq, ArrayList<subProductVO> sublist, int mseq) {
		pdao.insertSubProductOrder(cseq, mseq, sublist);
	}
}
