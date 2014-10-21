package com.example.todo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.todo.models.Message;
import com.example.todo.models.User;
import com.example.todo.service.MessageService;

@Controller
@RequestMapping(value = "/messages")
public class MessageController {
	@Autowired
	private MessageService messageService;
	
	@RequestMapping(value="/inbox/{user}")
	public List<Message> inbox(@PathVariable User user){
		return messageService.getInbox(user);
	}
	@RequestMapping(value="/outbox")
	public List<Message> outbox(@PathVariable User user){
		return messageService.getOutbox(user);
	}
	@RequestMapping(value="/compose")
	public void compose(){
		Message message;
		message.setMessageSender("Okan");
		message.setMessageReceiver("Destan");
		message.setMessageContent("Hallo");
		messageService.sendMessage(message);
	}
	@RequestMapping(value="/{message}")
	
	
	
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model){
		model.addAttribute("message", new Message());
		return "JSP HERE";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String create(Message messageBean, Model model){
		messageService.sendMessage(messageBean);
		model.addAttribute("message", "Todo created");
		return "JSP HERE";
	}
	
	
}
