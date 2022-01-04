package com.ezen.burger.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.burger.dao.IAdminDao;
import com.ezen.burger.dto.AdminVO;

@Service
public class AdminService {
	@Autowired
	IAdminDao adao;

	public AdminVO adminCheck(String id) {
		return adao.adminCheck(id);
	}

	public int getAllCount(String tableName, String fieldName, String key) {
		return adao.getAllCount(tableName,fieldName,key);
	}
}
