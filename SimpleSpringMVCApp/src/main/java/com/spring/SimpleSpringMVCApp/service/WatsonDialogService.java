package com.spring.SimpleSpringMVCApp.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ibm.watson.developer_cloud.dialog.v1.DialogService;
import com.ibm.watson.developer_cloud.dialog.v1.model.Conversation;
import com.ibm.watson.developer_cloud.dialog.v1.model.Dialog;

@Service
public class WatsonDialogService {

	public final String RDV_DIALOG_ID = "72fae9e8-7862-46f7-a9da-47c030e5a553";
	
	private final String USERNAME = "a59a6a59-5246-4952-825a-53a8dfbbb22b";
	private final String PASSWORD = "igZ7W98LrK7a";
	private final String ENDPOINT = "https://gateway.watsonplatform.net/dialog/api";
	
	private DialogService service;
	
	public WatsonDialogService()
	{
		service = new DialogService();
		service.setUsernameAndPassword(USERNAME, PASSWORD);
		service.setEndPoint(ENDPOINT);
		
		/*ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("test.xml").getFile());
		
		Dialog dialog = service.createDialog(RDV_DIALOG_ID,file);*/
	}
	
	public Conversation StartConversation()
	{
		return service.createConversation(RDV_DIALOG_ID);
	}
	
	public Conversation Converse(String dialogID,String input,Integer clientId,Integer conversationID)
	{
		HashMap<String,Object> conversationDetail = new HashMap<>();
		
		conversationDetail.put(DialogService.DIALOG_ID, dialogID);
		conversationDetail.put(DialogService.INPUT, input);
		conversationDetail.put(DialogService.CLIENT_ID, clientId);
		conversationDetail.put(DialogService.CONVERSATION_ID, conversationID);
		
		return service.converse(conversationDetail);
	}
	
	public List<Dialog> GetAllDialog()
	{
		return service.getDialogs();
	}
	
}
