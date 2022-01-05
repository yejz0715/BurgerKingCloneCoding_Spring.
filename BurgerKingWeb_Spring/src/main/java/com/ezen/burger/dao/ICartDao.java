package com.ezen.burger.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.ezen.burger.dto.CartVO;

@Mapper
public interface ICartDao {

	ArrayList<CartVO> selectCart(String id);
	
}
