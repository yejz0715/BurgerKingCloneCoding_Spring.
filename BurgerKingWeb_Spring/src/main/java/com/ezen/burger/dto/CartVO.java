package com.ezen.burger.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class CartVO {
	private int cseq;
	private String id;
	private int pseq;
	private int quantity;
	private String result;
	private Timestamp date;
	private String pname;
	private String mname;
	private String image;
	private int price1;
	private String kind1;
	private String kind3;
	private String phone;
	private int memberkind;
}
