package com.ezen.burger.dto;

import java.sql.Timestamp;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class QnaVO {
	private Integer qseq;
	@NotEmpty @NotNull
	private String subject;
	@NotEmpty @NotNull
	private String content;
	private String reply;
	private String id;
	private String rep;
	private Timestamp indate;
	private int readcount;
	@NotEmpty @NotNull
	private String pass;
}
