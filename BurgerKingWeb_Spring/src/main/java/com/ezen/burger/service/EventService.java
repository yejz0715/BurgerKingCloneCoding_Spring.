package com.ezen.burger.service;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.burger.dao.IEventDao;
import com.ezen.burger.dto.EventVO;

@Service
public class EventService {
	@Autowired
	IEventDao edao;

	public ArrayList<EventVO> getAllEvents() {
		return edao.getAllEvents();
	}

	public ArrayList<EventVO> getOngoingEvents() {
		return edao.getOngoingEvents();
	}

	public ArrayList<EventVO> getPastEvents() {
		return edao.getPastEvents();
	}

	public EventVO getDetailEvent(int eseq) {
		return edao.getDetailEvent(eseq);
	}

	public EventVO getEvent(int eseq) {
		
		return edao.getEvent(eseq);
	}

	public void deleteEvent(int eseq) {
		edao.deleteEvent(eseq);
		
	}

	public void insertEvent(EventVO eventvo) {
		edao.insertEvent(eventvo);
		
	}
}
