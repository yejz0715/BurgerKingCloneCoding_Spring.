package com.ezen.burger.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.ezen.burger.dto.ProductVO;

@Mapper
public interface IProductDao {

	ArrayList<ProductVO> getProduct(String kind1);

}
