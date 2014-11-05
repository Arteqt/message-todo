package com.example.todo.models;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = "name") })
public class User implements java.io.Serializable {

	private static final long serialVersionUID = 1473349524955527373L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private long id;

	@Column(name = "name", unique = false, nullable = false)
	private String name;

	@Column(name = "password", unique = false, nullable = false)
	private String password;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "sender")
	private Set<Message> messages = new HashSet<Message>(0);

	public User() {
	}

	public User(String name, String password) {
		this.name = name;
		this.password = password;
	}

	public User(String name, String password, Set<Message> messages) {
		this.name = name;
		this.password = password;
		this.messages = messages;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Message> getMessages() {
		return messages;
	}

	public void setMessages(Set<Message> messages) {
		this.messages = messages;
	}

}