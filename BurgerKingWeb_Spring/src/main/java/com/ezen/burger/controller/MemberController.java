package com.ezen.burger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ezen.burger.service.MemberService;

@Controller
public class MemberController {
	@Autowired
	MemberService ms;
}	
