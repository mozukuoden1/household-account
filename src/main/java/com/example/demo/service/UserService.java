package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Authorities;
import com.example.demo.entity.UserData;
import com.example.demo.repository.AuthoritiesRepository;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	@Autowired
	private AuthoritiesRepository authoritiesRepository;
	
	public void saveAndFlush(UserData user) {
		repository.saveAndFlush(user);
	}
	public void saveAndFlush(Authorities auth) {
		authoritiesRepository.saveAndFlush(auth);
	}
}
