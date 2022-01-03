package com.ezen.burger.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.burger.dao.IQnaDao;

@Service
public class QnaService {
	@Autowired
	IQnaDao qdao;
}
