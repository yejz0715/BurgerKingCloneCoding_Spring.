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

	public ArrayList<orderVO> getOrderListByGuest(String id);

	public ArrayList<orderVO> getOrderByOseq(int oseq);

	public int getOseq(String odseq);

	public void deleteOrderDetail(String odseq);

	public ArrayList<orderVO> getOrderDetailByOseq(int oseq);

	public void deleteOrders(int oseq);

	public orderVO getOrder_view(String odseq);
	
	public orderVO getOrder_view2(String odseq);

	public void deleteSpo(String odseq);
}
