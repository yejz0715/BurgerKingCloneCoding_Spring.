package com.ezen.burger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ezen.burger.service.AdminService;

@Controller
public class AdminController {
	@Autowired
	AdminService as;
}
