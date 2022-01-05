package com.ezen.burger.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.ezen.burger.dto.orderVO;

@Mapper
public interface IOrderDao {

	ArrayList<orderVO> getOrderList(String id);
	
}
