package com.spring.SimpleSpringMVCApp.dto;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Account 
{
	private String email;
	private String password;

    protected Account() {

	}
	
	public Account(String email, String password) {
		this.email = email;
		this.password = password;
	}

    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
    public String toString() {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(this);
		return json;
    }
}
