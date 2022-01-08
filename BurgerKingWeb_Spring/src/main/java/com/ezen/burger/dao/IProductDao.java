package com.ezen.burger.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.ezen.burger.dto.ProductVO;
import com.ezen.burger.dto.subProductVO;
import com.ezen.burger.dto.subproductOrderVO;

@Mapper
public interface IProductDao {

	public ArrayList<ProductVO> getProduct(String kind1);

	public ArrayList<ProductVO> getProductdetail(int pseq);

	public ArrayList<ProductVO> getProductkind(String kind1, String kind2);

	public ArrayList<ProductVO> getProductList(String kind1);

	public ArrayList<subproductOrderVO> selectSubProductOrder(int mseq);

	public ProductVO getDeliveryDetail(int pseq);

	public ProductVO getProducts(int pseq);

	public ArrayList<subProductVO> getSubProduct();

	public ArrayList<subproductOrderVO> selectSubProductOrder2(int gseq);

	public subProductVO getSubProduct2(int spseq);

	public void insertSubProductOrder(int cseq, int mseq, subProductVO subProductVO);

	public void insertSubProductOrderByGseq(int cseq, int gseq, subProductVO subProductVO);

	public ArrayList<subproductOrderVO> selectSubProductOrder3(int mseq);

	public ArrayList<subproductOrderVO> selectSubProductOrder4(int gseq);

	public ArrayList<subproductOrderVO> selectSubProductOrder5(int oseq);

}
