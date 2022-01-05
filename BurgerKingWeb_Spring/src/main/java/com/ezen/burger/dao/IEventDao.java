package com.ezen.burger.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.ezen.burger.dto.EventVO;

@Mapper
public interface IEventDao {
	ArrayList<EventVO> getAllEvents();

	ArrayList<EventVO> getOngoingEvents();

	ArrayList<EventVO> getPastEvents();

	EventVO getDetailEvent(int eseq);

	EventVO getEvent(int eseq);

	void deleteEvent(int eseq);

	void insertEvent(EventVO eventvo);
}
