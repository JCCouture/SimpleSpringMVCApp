package com.spring.SimpleSpringMVCApp.dto;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class AnswerDTO {

	private String answer;
	private int clientID;
	private int conversationID;
	
	public int getClientID() {
		return clientID;
	}
	public void setClientID(int clientID) {
		this.clientID = clientID;
	}
	
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	public int getConversationID() {
		return conversationID;
	}
	public void setConversationID(int conversationID) {
		this.conversationID = conversationID;
	}
	
	@Override
    public String toString() {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(this);
		return json;
    }
	
}
