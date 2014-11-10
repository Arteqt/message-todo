package com.example.todo.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.todo.models.Message;
import com.example.todo.models.User;
import com.example.todo.service.MessageService;
import com.example.todo.service.UserService;

@Controller
@RequestMapping(value = "/messages")
public class MessageController {

	@Autowired
	private MessageService messageService;

	@Autowired
	private UserService userService;

	// In-box and Out-box
	@RequestMapping(method = RequestMethod.GET)
	public String inbox(Model model, HttpSession session) {
		User user = (User) session.getAttribute("loggedUser");
		List<Message> inbox = messageService.getInbox(user);
		List<Message> outbox = messageService.getOutbox(user);
		Message message = new Message();

		model.addAttribute("inboxSize", inbox.size());
		model.addAttribute("message", message);
		model.addAttribute("inbox", inbox);
		model.addAttribute("outboxSize", outbox.size());
		model.addAttribute("outbox", outbox);

		return "message";
	}

	// Remove Message
	@RequestMapping(value = "/delete/{id}")
	public String removeMessage(@PathVariable long id) {
		messageService.removeMessage(id);
		return "redirect:/messages";
	}

	// Read Message
	@RequestMapping(value = "/{id}")
	public String readMessage(@PathVariable long id, HttpSession session) {
		Message message = messageService.readMessage(id);
		session.setAttribute("messageDetailed", message);
		return "redirect:/messages";
	}

	@RequestMapping(value = "/reply/{id}")
	public String replyMessage(@PathVariable long id, HttpSession session) {
		Message message = messageService.readMessage(id);
		session.setAttribute("replyMessage", message);
		return "redirect:/home";
	}

	@RequestMapping(value = "/conversation/{id}")
	public String conversation(@PathVariable long id, HttpSession session) {
		Message message = messageService.readMessage(id);
		List<Message> conversation = messageService.getConversation(message);
		session.setAttribute("conversation", conversation);
		return "redirect:/messages";
	}

	// Test: 3 User and 3 Message
	@RequestMapping(value = "/setup")
	public String setup() {
		User user1 = new User("Okan", "123");
		User user2 = new User("Destan", "123");
		User user3 = new User("Zafer", "123");

		userService.createUser(user1);
		userService.createUser(user2);
		userService.createUser(user3);

		Message message1 = new Message(user1, user2, "message1", "message1");
		messageService.createMessage(message1);
		Message message2 = new Message(user2, user1, "message2", "message2",
				message1, message1);
		messageService.createMessage(message2);
		Message message3 = new Message(user1, user2, "message3", "message3",
				message1, message2);
		messageService.createMessage(message3);
		Message message4 = new Message(user3, user1, "message4", "message4");
		messageService.createMessage(message4);
		Message message5 = new Message(user1, user3, "message5", "message5",
				message4, message4);
		messageService.createMessage(message5);
		return "redirect:/messages";
	}
}
