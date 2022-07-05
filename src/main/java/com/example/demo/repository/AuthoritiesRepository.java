package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Authorities;

@Repository
public interface AuthoritiesRepository extends JpaRepository<Authorities, String>{

}
