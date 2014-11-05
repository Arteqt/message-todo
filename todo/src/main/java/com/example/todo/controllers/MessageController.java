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

@Controller
@RequestMapping(value = "/messages")
public class MessageController {

	@Autowired
	private MessageService messageService;

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
	public String readMessage(@PathVariable long id, Model model,
			HttpSession session) {
		Message message = messageService.readMessage(id);
		session.setAttribute("messageDetailed", message);
		return "redirect:/messages";
	}
}
