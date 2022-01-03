package com.ezen.burger.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class EventVO {
	private int eseq;
	private String subject;
	private String content;
	private String image;
	private Timestamp startdate;
	private Timestamp enddate;
	private int state;
}
