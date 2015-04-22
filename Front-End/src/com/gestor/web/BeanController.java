package com.gestor.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BeanController {
	
	@RequestMapping("/welcome")
	public ModelAndView home(){
		return new ModelAndView("home");
	}
}
