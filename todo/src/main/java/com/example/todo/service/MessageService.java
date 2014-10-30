package com.example.todo.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.todo.dao.MessageDao;
import com.example.todo.models.Message;
import com.example.todo.models.User;

@Service
public class MessageService implements Serializable {

	private static final long serialVersionUID = 8529339097663168581L;

	@Autowired
	private MessageDao messageDao;

	public void sendMessage(String messageContent, String messageSubject,
			User messageSender, User messageReceiver) {
		Message message = new Message();
		message.setMessageContent(messageContent);
		message.setMessageSubject(messageSubject);
		message.setMessageReceiver(messageReceiver);
		message.setMessageSender(messageSender);

		messageDao.save(message);
	}

	public void removeMessage(Message message) {
		messageDao.delete(message);
	}

	public Message readMessage(int id) {
		return messageDao.findById(id);
	}

	public List<Message> getOutbox(User sender) {
		return messageDao.outbox(sender);
	}

	public List<Message> getInbox(User receiver) {
		return messageDao.inbox(receiver);
	}
}
