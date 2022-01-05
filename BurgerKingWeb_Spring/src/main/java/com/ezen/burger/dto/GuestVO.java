package com.ezen.burger.dto;

import lombok.Data;

@Data
public class GuestVO {
	private int gseq;
	private String id;
	private String pwd;
	private String phone;
	private String name;
	private int memberkind;
	private int oseq;
	private String zip_num;
	private String address;
}
