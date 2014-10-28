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

	// TODO: Ask what is the best practice
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

	public Message readMessage(Message message) {
		return messageDao.findById(message.getMessageId());
	}

	public List<Message> getOutbox(User sender) {
		return messageDao.inbox(sender);
	}

	public List<Message> getInbox(User receiver) {
		return messageDao.outbox(receiver);
	}

	/**
	 * Test: List all messages
	 */
	public List<Message> listMessages() {
		return messageDao.allMessages();
	}

}
