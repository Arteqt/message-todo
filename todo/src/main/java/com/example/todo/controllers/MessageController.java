package com.example.todo.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

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

	/**
	 * Test: All messages
	 */
	// @RequestMapping(method = RequestMethod.GET)
	// public String listMessages(Model model) {
	// Message message = new Message();
	// List<Message> listMessages = messageService.listMessages();
	//
	// model.addAttribute("message", message);
	// model.addAttribute("listMessages", listMessages);
	// return "message";
	// }

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

	// Out-box
	// @RequestMapping(value = "/outbox")
	// public String outbox(Model model, HttpSession session) {
	// User user = (User) session.getAttribute("loggedUser");
	// List<Message> outbox = messageService.getOutbox(user);
	// Message message = new Message();
	//
	// model.addAttribute("outboxSize", outbox.size());
	// model.addAttribute("message", message);
	// model.addAttribute("outbox", outbox);
	// return "redirect:/messages";
	// }

	// Remove Message
	@RequestMapping(value = "/remove")
	public String removeMessage(@ModelAttribute("message") Message message) {
		messageService.removeMessage(message);
		return "redirect:/messages";
	}

	// Read Message
	@RequestMapping(value = "/{messageId}")
	public void readMessage(@PathVariable("messageId") int id, Model model) {
		Message message = messageService.readMessage(id);
		model.addAttribute("messageDetailed", message);
		// return "redirect:/messages";
	}
}
