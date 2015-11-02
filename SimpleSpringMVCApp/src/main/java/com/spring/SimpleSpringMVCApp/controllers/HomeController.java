package com.spring.SimpleSpringMVCApp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.Database;
import com.spring.SimpleSpringMVCApp.repository.AccountRepository;

import com.spring.SimpleSpringMVCApp.dto.*;

@Controller
public class HomeController
{		
	private String name;
	private String username;
	private String password;
	
	@Autowired
	private AccountRepository accountRepository;
	    
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() 
	{
		return "home/home";
	}
	
	@RequestMapping(value = "/about", method = RequestMethod.GET)
	public String about() 
	{
		this.username = "24414d8c-124c-4162-804c-eebe80819b96-bluemix";
		this.password = "c1a5f8f1690bceadfbb35e2251216500928bc070ad11f89653ddc1b5d0bb8845";
		this.name = "talent-manager";
		CloudantClient client = new CloudantClient(username,username,password);
		
		Database db = client.database(name, false);
		
		return "home/about";
	}
	
	/*@RequestMapping(value = "/contact", method = RequestMethod.GET)
	public String contact() 
	{
		return "home/contact";
	}*/
	
	@RequestMapping(value = "/comments", method = RequestMethod.GET, produces = "application/json")
    @ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public List<Account> comments() 
	{
		return accountRepository.findAll();
	}
	
	 @RequestMapping(value = "/comments", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	 @ResponseStatus(value = HttpStatus.CREATED)
	 @ResponseBody
	 public List<Account> post(@RequestBody final Account updated) 
	 {
		 accountRepository.save(updated);
	     return accountRepository.findAll();
	 }
}


