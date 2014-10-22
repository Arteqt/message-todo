package com.example.todo.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.todo.models.Message;

@Repository
public class MessageDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	public void save(Message message)
	{
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.persist(message);
		tx.commit();
		session.close();
	}
	public List<Message> list(){
		Session session = this.sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Message.class);
		
		@SuppressWarnings("unchecked")
		List<Message> messageList = criteria.list();
		
		session.close();
		return messageList;
	}
	
	public void delete(Message message){
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.delete(message);
		tx.commit();
		session.close();
	}
	
	public void update(Message message){
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.update(message);
		tx.commit();
		session.close();
	}	
}
