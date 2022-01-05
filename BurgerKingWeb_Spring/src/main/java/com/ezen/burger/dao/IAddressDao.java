package com.ezen.burger.dao;

import org.apache.ibatis.annotations.Mapper;

import com.ezen.burger.dto.MyAddressVO;

@Mapper
public interface IAddressDao {

	public MyAddressVO getMyAddress(int mseq);
	
}
