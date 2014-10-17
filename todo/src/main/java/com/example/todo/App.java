package com.example.todo;

import org.hibernate.Session;

import com.example.todo.models.Message;
import com.example.todo.models.User;
import com.example.todo.utils.HibernateUtil;

public class App {
	public static void main(String[] args) {

		System.out.println("Hibernate one to many (Annotation)");
		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();

		User user = new User();
		user.setUserName("Okan");
		user.setUserPassword("123");
		session.save(user);
		
		User user2 = new User();
		user2.setUserName("Destan");
		user2.setUserPassword("123");
		session.save(user2);
		
		Message messages = new Message();
		messages.setMessageSender(user);
		messages.setMessageReceiver(user2);
		messages.setMessageContent("rerero");
		
		user.getMessages().add(messages);

		session.save(messages);

		session.getTransaction().commit();
		System.out.println("Done");
	}
}