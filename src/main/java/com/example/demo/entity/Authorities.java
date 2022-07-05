package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "authorities")
@Entity
public class Authorities {

	@Id
	private String username;
	@Column
	private int id;
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private String authority;
	@OneToOne(mappedBy = "authorities")
	UserData userdata;
	
	public Authorities(String username, int id, String authority, UserData userdata) {
		this.username = username;
		this.id = id;
		this.authority = authority;
		this.userdata = userdata;
	}

	public Authorities() {
	
	}
}
