package com.ezen.burger.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class ProductVO {
	private int pseq;
	private String pname;
	private String kind1;
	private String kind2;
	private String kind3;
	private Integer price1;
	private Integer price2;
	private Integer price3;
	private String content;
	private String image;
	private String useyn;
	private Timestamp indate;
}
