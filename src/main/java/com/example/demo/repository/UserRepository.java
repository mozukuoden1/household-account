package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.UserData;

@Repository
public interface UserRepository extends JpaRepository<UserData, String>{

	Optional<UserData> findByUsername(String username);
	
	//public Optional<UserData> findByUserMail(String mail);
}
