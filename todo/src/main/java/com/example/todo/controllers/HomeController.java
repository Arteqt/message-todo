package com.example.todo.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.todo.models.User;
import com.example.todo.service.MessageService;
import com.example.todo.service.UserService;

@Controller
@RequestMapping(value = "/home")
public class HomeController {

	@Autowired
	private UserService userService;
	@Autowired
	private MessageService messageService;

	/**
	 * Test: List Users to Add/Delete
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String listUsers(Model model, HttpSession session) {
		User user = new User();
		User loggedUser = (User) session.getAttribute("loggedUser");
		if (loggedUser != null) {
			List<User> otherUsers = userService.listUsersExcept(loggedUser
					.getUserId());
			model.addAttribute("otherUsers", otherUsers);
		}
		model.addAttribute("user", user);
		return "home";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam("username") String username,
			@RequestParam("password") String password, HttpSession session) {

		User user = userService.findUserByCredentials(username, password);

		if (user != null) {
			session.setAttribute("loggedUser", user);
		}
		return "redirect:/home";
	}

	@RequestMapping(value = "/logout")
	public String logout(HttpSession session) {

		session.setAttribute("loggedUser", null);

		return "redirect:/home";
	}

	@RequestMapping(value = "/send", method = RequestMethod.POST)
	public String send(@RequestParam("receiver") int messageReceiverId,
			@RequestParam("subject") String messageSubject,
			@RequestParam("content") String messageContent, HttpSession session) {
		User messageSender = (User) session.getAttribute("loggedUser");
		User messageReceiver = userService.findUserById(messageReceiverId);
		messageService.sendMessage(messageContent, messageSubject,
				messageSender, messageReceiver);
		return "redirect:/home";
	}
}
