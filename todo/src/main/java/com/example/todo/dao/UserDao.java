package com.example.todo.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.todo.models.User;

@Repository
public class UserDao {
	@Autowired
	private SessionFactory sessionFactory;

	public void save(User user) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.persist(user);
		tx.commit();
		session.close();
	}

	public List<User> listAllUsersExcept(long id) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.ne("id", id));

		@SuppressWarnings("unchecked")
		List<User> userList = criteria.list();
		session.close();
		return userList;
	}

	public User findUserByCredentials(String username, String password) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("name", username));
		criteria.add(Restrictions.eq("password", password));

		User user = (User) criteria.uniqueResult();
		session.close();
		return user;
	}

	public User findUserById(long id) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("id", id));

		User user = (User) criteria.uniqueResult();
		session.close();
		return user;
	}

	public List<User> list() {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(User.class);

		@SuppressWarnings("unchecked")
		List<User> userList = criteria.list();

		session.close();
		return userList;
	}

	public void delete(long id) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("id", id));

		Transaction tx = session.beginTransaction();
		session.delete(criteria.uniqueResult());
		tx.commit();
		session.close();
	}

	public void update(User user) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.update(user);
		tx.commit();
		session.close();
	}
}
