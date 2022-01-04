package com.ezen.burger.dao;

import org.apache.ibatis.annotations.Mapper;

import com.ezen.burger.dto.AdminVO;

@Mapper
public interface IAdminDao {

	AdminVO adminCheck(String id);

	int getAllCount(String tableName, String fieldName, String key);
	
}
