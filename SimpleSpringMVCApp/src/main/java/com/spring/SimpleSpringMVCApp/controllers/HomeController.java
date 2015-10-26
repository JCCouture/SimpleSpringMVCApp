package com.spring.SimpleSpringMVCApp.controllers;

import java.net.MalformedURLException;
import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.Database;

import org.apache.wink.json4j.JSONArray;
import org.apache.wink.json4j.JSONException;
import org.apache.wink.json4j.JSONObject;

@Controller
public class HomeController
{		
	private int port;
	private String name;
	private String host;
	private String username;
	private String password;
	    
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() 
	{
		return "home/home";
	}
	
	@RequestMapping(value = "/about", method = RequestMethod.GET)
	public String about() 
	{
		this.port = 443;
		this.host = "24414d8c-124c-4162-804c-eebe80819b96-bluemix.cloudant.com";
		this.username = "24414d8c-124c-4162-804c-eebe80819b96-bluemix";
		this.password = "c1a5f8f1690bceadfbb35e2251216500928bc070ad11f89653ddc1b5d0bb8845";
		this.name = "talent-manager";
		CloudantClient client = new CloudantClient(username,username,password);
		
		Database db = client.database(name, false);
		
		Person p = new Person("Roger Comptois", null, null);
		p.group = "group1";
		
		db.save(p);
		
		return "home/about";
	}
	
	@RequestMapping(value = "/contact", method = RequestMethod.GET)
	public String contact() 
	{
		return "home/contact";
	}
}
