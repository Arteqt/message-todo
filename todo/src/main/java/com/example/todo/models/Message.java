package com.example.todo.models;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table
public class Message implements java.io.Serializable {

	private static final long serialVersionUID = -5001640410168279934L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private long id;

	@Column
	private String subject;

	@ManyToOne
	private User sender;

	@ManyToOne
	private User receiver;

	@Column(nullable = false)
	private String content;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date", nullable = false)
	private Calendar date;

	@Column(name = "isRead", columnDefinition = "tinyint default false")
	private Boolean isRead;

	@Column(name = "root")
	private long root;

	@Column(name = "parent")
	private long parent;

	public Message() {
	}

	public Message(User sender, User receiver, String content, String subject) {
		this.sender = sender;
		this.receiver = receiver;
		this.content = content;
		this.subject = subject;
		date = Calendar.getInstance();
	}

	public Message(User sender, User receiver, String content, String subject,
			long root, long parent) {
		this.sender = sender;
		this.receiver = receiver;
		this.content = content;
		this.subject = subject;
		this.root = root;
		this.parent = parent;
		date = Calendar.getInstance();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Date getDate() {

		return date.getTime();
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public Boolean getIsRead() {
		return isRead;
	}

	public void setIsRead(Boolean isRead) {
		this.isRead = isRead;
	}

	public long getRoot() {
		return root;
	}

	public void setRoot(long root) {
		this.root = root;
	}

	public long getParent() {
		return parent;
	}

	public void setParent(long parent) {
		this.parent = parent;
	}

}