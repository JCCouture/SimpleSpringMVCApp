package com.spring.SimpleSpringMVCApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.ibm.watson.developer_cloud.dialog.v1.model.Conversation;
import com.spring.SimpleSpringMVCApp.dto.Account;
import com.spring.SimpleSpringMVCApp.dto.AnswerDTO;
import com.spring.SimpleSpringMVCApp.dto.ChatMessageDTO;
import com.spring.SimpleSpringMVCApp.dto.ConversationDTO;
import com.spring.SimpleSpringMVCApp.service.WatsonDialogService;

@Controller
public class DialogController {

	@Autowired
	private WatsonDialogService dialogService;
	
	private ConversationDTO buffer;
	
	@RequestMapping(value = "/contact", method = RequestMethod.GET)
	public String index() 
	{
		return "home/contact";
	}
	
	@RequestMapping(value = "/startConversation", method = RequestMethod.GET,produces = "application/json")
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public ConversationDTO startConversation() 
	{
		Conversation conv = dialogService.StartConversation();
		
		ConversationDTO dto = new ConversationDTO();
		dto.setClientID(conv.getClientId());
		dto.setConversationID(conv.getId());
		
		for (String i : conv.getResponse()) { 
		    dto.addChatMessage(new ChatMessageDTO(i,"response"));
		}
		
		return dto;
	}
	
	@RequestMapping(value = "/getConversation", method = RequestMethod.GET,produces = "application/json")
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public ConversationDTO getConversation() 
	{
		return buffer;
	}
	
	@RequestMapping(value = "/converse",
					method = RequestMethod.POST,
					consumes = "application/json",
					produces = "application/json")
	@ResponseStatus(value = HttpStatus.CREATED)
	@ResponseBody
	public ConversationDTO converse(@RequestBody final AnswerDTO answer) 
	{
		Conversation conv = dialogService.Converse(dialogService.RDV_DIALOG_ID,
												   answer.getAnswer(),
												   answer.getClientID(),
												   answer.getConversationID());
		
		ConversationDTO dto = new ConversationDTO();
		dto.setClientID(conv.getClientId());
		dto.setConversationID(conv.getId());
		
		for (String i : conv.getResponse()) { 
		    dto.addChatMessage(new ChatMessageDTO(i,"response"));
		}
		
		buffer = dto;
		return dto;
	}
	
}
