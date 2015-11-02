package com.spring.SimpleSpringMVCApp.dto;

import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ConversationDTO {

	private String input;
	private List<ChatMessageDTO> listOfString;
	private int clientID;
	private int conversationID;
	
	public ConversationDTO()
	{
		listOfString = new LinkedList<>();
	}
	
	public int getClientID() {
		return clientID;
	}
	public void setClientID(int clientID) {
		this.clientID = clientID;
	}
	public String getInput() {
		return input;
	}
	public void setInput(String input) {
		this.input = input;
	}
	public int getConversationID() {
		return conversationID;
	}
	public void setConversationID(int conversationID) {
		this.conversationID = conversationID;
	}
	public List<ChatMessageDTO> getListOfString() {
		return listOfString;
	}
	public void setListOfString(List<ChatMessageDTO> listOfString) {
		this.listOfString = listOfString;
	}
	
	public void addChatMessage(ChatMessageDTO chatMessage)
	{
		this.listOfString.add(chatMessage);
	}
	
	@Override
    public String toString() {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(this);
		return json;
    }
	
}
