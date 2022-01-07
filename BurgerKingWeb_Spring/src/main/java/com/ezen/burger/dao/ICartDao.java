package com.ezen.burger.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.ezen.burger.dto.CartVO;

@Mapper
public interface ICartDao {
	public ArrayList<CartVO> selectCart(String id);
	public void insertCart(CartVO cvo);
	public void deleteguestCart(String id);
	public int getCseq();
}
