package com.ezen.burger.dto;

import java.sql.Timestamp;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ProductVO {
	private int pseq;
	@NotNull
	@NotEmpty
	private String pname;
	@NotNull
	@NotEmpty
	private String kind1;
	@NotNull
	@NotEmpty
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
