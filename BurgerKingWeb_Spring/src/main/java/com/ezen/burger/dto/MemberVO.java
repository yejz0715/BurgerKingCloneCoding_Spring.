package com.ezen.burger.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class MemberVO {
	private int mseq;
	private String id;
	private String pwd;
	private String phone;
	private Timestamp indate;
	private Timestamp lastdate;
	private String name;
	private int memberkind;
}
