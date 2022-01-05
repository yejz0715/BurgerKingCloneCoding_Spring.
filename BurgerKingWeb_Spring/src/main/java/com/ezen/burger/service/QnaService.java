package com.ezen.burger.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.burger.dao.IQnaDao;
import com.ezen.burger.dto.QnaVO;

@Service
public class QnaService {
	@Autowired
	IQnaDao qdao;

	public ArrayList<QnaVO>  listQna(String id) {
		return qdao.listQna(id);
	}
	
	public void insertQna( QnaVO qnavo) {
		qdao.insertQna(qnavo);
	}

	public QnaVO  getQna(int qseq) {
		return qdao.getQna(qseq);
	}

	public void deleteQna(int qseq) {
		qdao.deleteQna(qseq);
	}

	public QnaVO getpassChk(int qseq) {
		return qdao.getpassChk(qseq);
	}

	public void updateQna(int qseq, String reply) {
		qdao.updateQna(qseq, reply);		
	}





}
