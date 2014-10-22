package com.example.todo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.todo.models.Message;
import com.example.todo.models.User;
import com.example.todo.service.MessageService;

@Controller
@RequestMapping(value = "/messages")
//@SessionAttributes("")
public class MessageController {
	
	@Autowired
	private MessageService messageService;
	
	/*Test: All messages*/
	@RequestMapping(method = RequestMethod.GET)
	public String listMessages(Model model){
		Message message = new Message();
		List<Message> listMessages = messageService.listMessages();
		
		model.addAttribute("message", message);
		model.addAttribute("listMessages", listMessages);
		return "message";
	}
	/*In-box*/
	@RequestMapping(value="/inbox")
	public String inbox(@ModelAttribute("user")User user, Model model){
		List<Message> inbox = messageService.getInbox(user);
		Message message = new Message();
		
		model.addAttribute("message", message);
		model.addAttribute("inbox", inbox);
		return "redirect:/messages";
	}
	
	/*Out-box*/
	@RequestMapping(value="/outbox")
	public String outbox(@ModelAttribute("user")User user, Model model){
		List<Message> outbox = messageService.getOutbox(user);
		Message message = new Message();
		
		model.addAttribute("message", message);
		model.addAttribute("outbox", outbox);
		return "redirect:/messages";
	}
	
	/*Send Message*/
	@RequestMapping(value="/send")
	public String sendMessage(@ModelAttribute("message")Message message){
		messageService.sendMessage(message);
		return "redirect:/messages";
	}
	
	/*Remove Message*/
	@RequestMapping(value="/remove")
	public String removeMessage(@ModelAttribute("message")Message message){
		messageService.removeMessage(message);
		return "redirect:/messages";
	}
	
	/*TEST: Sets 2 Users and a Message*/
	@RequestMapping(value="/compose")
	public void compose(){
		User user1 = new User();
		User user2 = new User();
		user1.setUserName("Okan");
		user1.setUserPassword("1");
		user2.setUserName("Destan");
		user2.setUserPassword("2");
		
		Message message = new Message();
		message.setMessageSender(user1);
		message.setMessageReceiver(user2);
		message.setMessageContent("Hallo");
		messageService.sendMessage(message);
	}
	
	/*Read Message*/
	@RequestMapping(value="/read/{message}")
	public String readMessage(@ModelAttribute("message")Message message, Model model){
		model.addAttribute("message", messageService.readMessage(message));
		//TODO User sees the message when he clicks on it.
		return "return messageForm";
	}
}
