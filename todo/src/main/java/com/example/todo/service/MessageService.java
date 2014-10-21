package com.example.todo.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.todo.dao.MessageDao;
import com.example.todo.models.Message;
import com.example.todo.models.User;

@Service
public class MessageService implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8529339097663168581L;
	
	@Autowired
	private MessageDao messageDao;
	
	public void sendMessage(Message message){
		messageDao.save(message);
	}
	
	@SuppressWarnings("null")
	public List<Message> getOutbox(User user){
		List<Message> outbox = null;
		for(Message message : messageDao.list())
		{
			if(message.getMessageReceiver()==user){
				outbox.add(message);
			}
		}
		return outbox;
	}
	
	@SuppressWarnings("null")
	public List<Message> getInbox(User user){
		List<Message> inbox = null;
		for(Message message : messageDao.list()){
			if(message.getMessageReceiver()==user){
				inbox.add(message);
			}
		}
		return inbox;
	}
	
	
}
