package com.example.todo.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
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

	public Message findById(long id) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Message.class);
		criteria.add(Restrictions.eq("id", id));

		Message message = (Message) criteria.uniqueResult();

		session.close();
		return message;
	}

	public List<Message> inbox(User receiver) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Message.class);
		criteria.add(Restrictions.eq("receiver", receiver));
		criteria.addOrder(Order.desc("date"));

		@SuppressWarnings("unchecked")
		List<Message> inbox = criteria.list();

		session.close();
		return inbox;
	}

	public List<Message> outbox(User sender) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Message.class);
		criteria.add(Restrictions.eq("sender", sender));
		criteria.addOrder(Order.desc("date"));
		@SuppressWarnings("unchecked")
		List<Message> outbox = criteria.list();

		session.close();
		return outbox;
	}

	public List<Message> conversation(Message message) {
		long root = message.getRoot();

		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Message.class);

		criteria.add(Restrictions.eq("root", root));
		criteria.addOrder(Order.desc("parent"));

		@SuppressWarnings("unchecked")
		List<Message> conversation = criteria.list();

		session.close();
		return conversation;
	}

	public void delete(long id) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Message.class);
		criteria.add(Restrictions.eq("id", id));

		Transaction tx = session.beginTransaction();
		session.delete(criteria.uniqueResult());
		tx.commit();
		session.close();
	}

}
