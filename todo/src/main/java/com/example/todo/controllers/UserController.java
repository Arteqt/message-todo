package com.example.todo.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.todo.models.Message;
import com.example.todo.models.User;
import com.example.todo.service.UserService;

@Controller
@Scope("session")
@RequestMapping(value = "/users")
public class UserController {

	@Autowired
	private UserService userService;

	/* TEST: List Users to Add/Delete */
	@RequestMapping(method = RequestMethod.GET)
	public String listUsers(Model model) {
		User user = new User();
		List<User> users = userService.listUsers();
		
		model.addAttribute("user", user);
		model.addAttribute("listUsers", users);
		return "user";
	}

	/* TEST: Registration */
	@RequestMapping(value = "/add")
	public String createUser(User user) {
		userService.createUser(user);
		return "redirect:/users";
	}
	
	@RequestMapping(value = "/{id}")
	public String readUser(@PathVariable("id")String id, Model model) {
		model.addAttribute("user", userService.readUser(id));
		return "redirect:/users";
	}
	
	@RequestMapping(value = "/update/{id}")
	public String updateUser(@PathVariable("id")String id) {
		userService.updateUser(id);
		return "redirect:/users";
	}
	
	@RequestMapping(value = "/delete/{id}")
	public String deleteUser(@PathVariable("id")String id) {
		userService.deleteUser(id);
		return "redirect:/users";
	}
}
