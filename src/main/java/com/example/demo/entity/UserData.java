package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
@Entity
public class UserData {
	
	
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;
	@Id
	@Column
	private String username;
	@Column
	private String password;
	@Column(name = "enabled")
	private boolean enable;	
	@OneToOne
	@JoinColumn(name = "username")
	Authorities authorities;
	
	public UserData(int id, String username, String password, boolean enable, Authorities authorities) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.enable = enable;
		this.authorities = authorities;
	}

	public UserData(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public UserData() {
	
	}
}
