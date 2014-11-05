package com.example.todo.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.todo.dao.UserDao;
import com.example.todo.models.User;

@Service
public class UserService implements Serializable {

	private static final long serialVersionUID = -6960481465282188717L;

	@Autowired
	private UserDao userDao;

	public void createUser(User user) {
		userDao.save(user);
	}

	public void deleteUser(long id) {
		User user = userDao.findUserById(id);
		userDao.delete(user);
	}

	public List<User> listUsers() {
		return userDao.list();
	}

	public List<User> listUsersExcept(long id) {
		return userDao.listAllUsersExcept(id);
	}

	public User findUserByCredentials(String name, String password) {
		return userDao.findUserByCredentials(name, password);
	}

	public User findUserById(long id) {
		return userDao.findUserById(id);
	}
}
