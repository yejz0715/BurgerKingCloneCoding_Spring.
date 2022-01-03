package com.ezen.burger.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.burger.dao.IAdminDao;

@Service
public class AdminService {
	@Autowired
	IAdminDao adao;
}
