package com.spring.SimpleSpringMVCApp.dto;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ChatMessageDTO {

	private String message;
	private String type;
	
	public ChatMessageDTO(String message,String type)
	{
		this.message = message;
		this.type = type;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@Override
    public String toString() {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(this);
		return json;
    }
	
}
