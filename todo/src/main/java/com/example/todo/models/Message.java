package com.example.todo.models;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Message implements java.io.Serializable {

	private static final long serialVersionUID = -5001640410168279934L;

	private Integer messageId;
	private String messageSubject;
	private User messageSender;
	private User messageReceiver;
	private String messageContent;

	public Message() {
	}

	public Message(User messageSender, User messageReceiver,
			String messageContent, String messageSubject) {
		this.messageSender = messageSender;
		this.messageReceiver = messageReceiver;
		this.messageContent = messageContent;
		this.messageSubject = messageSubject;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "messageId", unique = true, nullable = false)
	public Integer getMessageId() {
		return messageId;
	}

	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}

	@ManyToOne
	public User getMessageSender() {
		return messageSender;
	}

	public void setMessageSender(User messageSender) {
		this.messageSender = messageSender;
	}

	@ManyToOne
	public User getMessageReceiver() {
		return messageReceiver;
	}

	public void setMessageReceiver(User messageReceiver) {
		this.messageReceiver = messageReceiver;
	}

	@Column(name = "messageContent", nullable = false)
	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	@Column(name = "messageSubject", nullable = true)
	public String getMessageSubject() {
		return messageSubject;
	}

	public void setMessageSubject(String messageSubject) {
		this.messageSubject = messageSubject;
	}
}