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

	/* Simple CRUD operations */

	public void createUser(User user) {
		userDao.save(user);
	}

	public User readUser(String id) {
		for (User user : userDao.list()) {
			if (user.getUserId().toString().contentEquals(id)) {
				return user;
			}
		}
		return null;
	}

	public void updateUser(String id) {
		for (User user : userDao.list()) {
			if (user.getUserId().toString().contentEquals(id)) {
				userDao.update(user);
			}
		}
	}

	public void deleteUser(String id) {

		for (User user : userDao.list()) {
			if (user.getUserId().toString().contentEquals(id)) {
				userDao.delete(user);
			}
		}

	}

	public List<User> listUsers() {
		return userDao.list();
	}

	public List<User> listUsersExcept(int id) {
		return userDao.listAllUsersExcept(id);
	}

	public User findUserByCredentials(String username, String password) {
		return userDao.findUserByCredentials(username, password);
	}

	public User findUserById(int id) {
		return userDao.findUserById(id);
	}
}
