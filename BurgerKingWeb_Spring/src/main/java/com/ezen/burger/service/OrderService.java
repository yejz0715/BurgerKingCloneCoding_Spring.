package com.ezen.burger.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.burger.dao.ICartDao;
import com.ezen.burger.dao.IOrderDao;
import com.ezen.burger.dto.CartVO;
import com.ezen.burger.dto.orderVO;

@Service
public class OrderService {
	@Autowired
	IOrderDao odao;
	
	@Autowired
	ICartDao cdao;

	public ArrayList<orderVO> getOrderList(String id) {
		return odao.getOrderList(id);
	}

	public void insertOrder(ArrayList<CartVO> list, String id) {
		// orders에 주문번호 하나 추가
		odao.insertOrder(id);
		
		// 방금 추가한 주문의 oseq값을 추출(해당 아이디의 oseq값들중 indate기준으로 내림차순 정렬해
		// 그 중 젤 위의 하나의 oseq 값을 추출한다.)
		int oseq = odao.selectOseq(id);
		
		for(int i = 0; i < list.size(); i++) {
			// 추출해낸 oseq 값과 cart들의 정보를 가지고 order_Detail의 내용을 추가한다.
			odao.insertOrderDetail(list.get(i), oseq);
			
			// 해당 oseq 값을 가진 주문상세중 odseq 기준으로 내림차순 정렬해 가장 위의 odseq값을 추출한다.
			int odseq = odao.selectOdseq(oseq);
			
			// 해당 카트의 cseq값을 가진 추가 재료의 oseq와 odseq에
			// 추출한 두 값을 삽입해준다.
			odao.insertseq(list.get(i).getCseq(), oseq, odseq);
		}
		
		// 모든 작업 뒤 카트를 비운다.
		for(int i = 0; i < list.size(); i++) {
			cdao.deleteCart(list.get(i).getCseq());
		}
	}
	
	public void insertOrderByGuest(ArrayList<CartVO> list, String id) {
		// orders에 주문번호 하나 추가
		odao.insertOrder(id);
		
		// 방금 추가한 주문의 oseq값을 추출(해당 아이디의 oseq값들중 indate기준으로 내림차순 정렬해
		// 그 중 젤 위의 하나의 oseq 값을 추출한다.)
		int oseq = odao.selectOseq(id);
		
		for(int i = 0; i < list.size(); i++) {
			// 추출해낸 oseq 값과 cart들의 정보를 가지고 order_Detail의 내용을 추가한다.
			odao.insertOrderDetail(list.get(i), oseq);
			
			// 해당 oseq 값을 가진 주문상세중 odseq 기준으로 내림차순 정렬해 가장 위의 odseq값을 추출한다.
			int odseq = odao.selectOdseq(oseq);
			
			// 해당 카트의 cseq값을 가진 추가 재료의 oseq와 odseq에
			// 추출한 두 값을 삽입해준다.
			odao.insertseq(list.get(i).getCseq(), oseq, odseq);
		}
	}

	public ArrayList<orderVO> getOrderListByGuest(String id) {
		return odao.getOrderListByGuest(id);
	}

	public ArrayList<orderVO> getOrderByOseq(int oseq) {
		return odao.getOrderByOseq(oseq);
	}

	public int getOseq(String odseq) {
		return odao.getOseq(odseq);
	}

	public void deleteOrder(String odseq, int oseq) {
		odao.deleteOrderDetail(odseq);
		// oseq 값을 기반으로 남은 odseq 즉 주문상세가 남아있는지 확인을 한다.
		ArrayList<orderVO> list = odao.getOrderDetailByOseq(oseq);
		odao.deleteSpo(odseq);
		// 해당 oseq값에 해당하는 detail이 없으면 orders 테이블의 데이터도 삭제
		if(list.size() == 0) {
			odao.deleteOrders(oseq);
		}
	}
	
	public void deleteOrder2(String odseq) {
		int oseq = odao.getOseq(odseq);
		odao.deleteOrderDetail(odseq);
		// oseq 값을 기반으로 남은 odseq 즉 주문상세가 남아있는지 확인을 한다.
		ArrayList<orderVO> list = odao.getOrderDetailByOseq(oseq);
		odao.deleteSpo(odseq);
		// 해당 oseq값에 해당하는 detail이 없으면 orders 테이블의 데이터도 삭제
		if(list.size() == 0) {
			odao.deleteOrders(oseq);
		}
	}

	public orderVO getOrder_view(String odseq) {
		return odao.getOrder_view(odseq);
	}
	
	public orderVO getOrder_view2(String odseq) {
		return odao.getOrder_view2(odseq);
	}
}
