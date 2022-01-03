package com.ezen.burger.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.burger.dto.EventVO;
import com.ezen.burger.service.EventService;

@Controller
public class EventController {
	@Autowired
	EventService es;
	
	@RequestMapping(value="/eventListForm")
	   public ModelAndView eventListForm(Model model) {
		ModelAndView mav=new ModelAndView();
		ArrayList<EventVO> list = es.getAllEvents();
		mav.addObject("eventList", list);
	    mav.setViewName("event/eventList");
	      return mav;
	   }
	
	@RequestMapping(value="/eventTab2")
	 public ModelAndView eventTab2(Model model) {
		ModelAndView mav=new ModelAndView();
		ArrayList<EventVO> list=es.getOngoingEvents();
		mav.addObject("eventList", list);
	    mav.setViewName("event/eventTab2");
	      return mav;
	   }
	
	@RequestMapping(value="/eventTab3")
	 public ModelAndView eventTab3(Model model) {
		ModelAndView mav=new ModelAndView();
		ArrayList<EventVO> list=es.getPastEvents();
		mav.addObject("eventList", list);
	    mav.setViewName("event/eventTab3");
	      return mav;
	   }
	
}
