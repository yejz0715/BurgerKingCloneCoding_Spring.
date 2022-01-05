package com.ezen.burger.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.ezen.burger.dto.AddressVO;
import com.ezen.burger.dto.MemberVO;
import com.ezen.burger.dto.MyAddressVO;

@Mapper
public interface IAddressDao {

	public MyAddressVO getMyAddress(int mseq);

	public ArrayList<AddressVO> selectAddressByDong(String dong);

	public void setUserAddress(MyAddressVO mavo);
	
}
