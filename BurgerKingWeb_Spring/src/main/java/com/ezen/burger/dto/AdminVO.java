package com.ezen.burger.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class AdminVO {
	@NotNull @NotEmpty(message="id를 입력하세요")
	private String id;
	@NotNull  @NotEmpty(message="비밀번호를 입력하세요")
	private String pwd;
	private String name;
	private String phone;
}
