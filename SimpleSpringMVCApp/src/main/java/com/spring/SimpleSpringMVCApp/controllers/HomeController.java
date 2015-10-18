package com.spring.SimpleSpringMVCApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController
{	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() 
	{
		return "home/home";
	}
	
	@RequestMapping(value = "/about", method = RequestMethod.GET)
	public String about() 
	{
		return "home/about";
	}
	
	@RequestMapping(value = "/contact", method = RequestMethod.GET)
	public String contact() 
	{
		return "home/contact";
	}
}
