package com.ezen.burger.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ezen.burger.dto.ProductVO;
import com.ezen.burger.service.ProductService;

@Controller
public class ProductController {
	@Autowired
	ProductService ps;
	
	@RequestMapping(value="menuListForm")
	public String menuListForm(Model model, @RequestParam("kind1") String kind1) {
		ArrayList<ProductVO> list = ps.getProduct(kind1);
		model.addAttribute("productList",list);
		return "product/menuList";
	}
}
