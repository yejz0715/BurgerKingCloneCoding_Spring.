package com.ezen.burger.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.ezen.burger.dto.CartVO;
import com.ezen.burger.dto.orderVO;

@Mapper
public interface IOrderDao {
	public ArrayList<orderVO> getOrderList(String id);

	public void insertOrder(String id);

	public void insertOrderDetail(CartVO list, int oseq);

	public int selectOseq(String id);

	public int selectOdseq(int oseq);

	public void insertseq(int cseq, int oseq, int odseq);
	
}
