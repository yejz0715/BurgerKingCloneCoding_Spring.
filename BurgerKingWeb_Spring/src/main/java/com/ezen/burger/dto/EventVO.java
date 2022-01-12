package com.ezen.burger.dto;



import lombok.Data;

@Data
public class EventVO {
	private int eseq;
	private String subject;
	private String content;
	private String image;
	private String thumbnail;	
	private String startdate;
	private String enddate;
	private int state;
}
