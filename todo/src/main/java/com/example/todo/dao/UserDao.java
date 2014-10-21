package com.example.todo.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.todo.models.User;

public class UserDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	public void save(User user)
	{
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.persist(user);
		tx.commit();
		session.close();
	}
	public List<User> list(){
		Session session = this.sessionFactory.openSession();
		Criteria criteria = session.createCriteria(User.class);
		
		@SuppressWarnings("unchecked")
		List<User> userList = criteria.list();
		
		session.close();
		return userList;
	}
	
	public void delete(User user){
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.delete(user);
		tx.commit();
		session.close();
	}
	
	public void update(User user){
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.update(user);
		tx.commit();
		session.close();
	}	
}
