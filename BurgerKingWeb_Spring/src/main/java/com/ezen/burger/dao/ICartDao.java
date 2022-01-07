package com.ezen.burger.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.ezen.burger.dto.CartVO;

@Mapper
public interface ICartDao {
	public ArrayList<CartVO> selectCart(String id);
	public void insertCart(CartVO cvo);
	public int getCseq();
	public void deleteCart(int cseq);
	public void minusQuantity(int cseq);
	public int getQuantity(int cseq);
	public void plusQuantity(int cseq);
	public CartVO getPseqCart(int pseq);
}
