package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.HouseholdAccountData;

@Repository
public interface HouseholdAccountRepository extends JpaRepository<HouseholdAccountData, Integer>{
	//public List<HouseholdAccountData> findByDateBetween(String begin, String end);
	
}
