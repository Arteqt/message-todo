package com.example.todo.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.todo.models.Message;
import com.example.todo.models.User;

@Repository
public class MessageDao {
	@Autowired
	private SessionFactory sessionFactory;

	public void save(Message message) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.persist(message);
		tx.commit();
		session.close();
	}

	public Message findById(Integer id) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Message.class);
		criteria.add(Restrictions.eq("messageId", id));

		Message message = (Message) criteria.uniqueResult();

		session.close();
		return message;
	}

	public List<Message> allMessages() {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Message.class);

		@SuppressWarnings("unchecked")
		List<Message> messageList = criteria.list();

		session.close();
		return messageList;
	}

	public List<Message> inbox(User receiver) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Message.class);
		criteria.add(Restrictions.eq("messageReceiver", receiver));

		@SuppressWarnings("unchecked")
		List<Message> inbox = criteria.list();

		session.close();
		return inbox;
	}

	public List<Message> outbox(User sender) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Message.class);
		criteria.add(Restrictions.eq("messageSender", sender));

		@SuppressWarnings("unchecked")
		List<Message> outbox = criteria.list();

		session.close();
		return outbox;
	}

	public void delete(Message message) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.delete(message);
		tx.commit();
		session.close();
	}

	public void update(Message message) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.update(message);
		tx.commit();
		session.close();
	}
}
