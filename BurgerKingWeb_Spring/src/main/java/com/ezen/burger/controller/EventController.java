package com.ezen.burger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ezen.burger.service.EventService;

@Controller
public class EventController {
	@Autowired
	EventService es;
	
	@RequestMapping("/eventListForm")
	   public String eventListForm(Model model) {
	   
	      
	      return "redirect:/event/eventListForm";
	   }
}
