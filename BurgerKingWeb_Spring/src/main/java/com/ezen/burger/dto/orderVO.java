package com.ezen.burger.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class orderVO {
	private int odseq;
	private int oseq;
	private String id;
	private Timestamp indate;
	private String mname;
	private String zip_num;
	private String address;
	private String phone;
	private int pseq;
	private String pname;
	private int quantity;
	private int price1;
	private String result;
	private String nid;
	private String memberkind;
}
