package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.HouseholdAccountData;

@Repository
public interface HouseholdAccountRepository extends JpaRepository<HouseholdAccountData, Integer>{
	public List<HouseholdAccountData> findByDateBetween(String begin, String end);
	
	@Query("SELECT SUM(d.income) FROM HouseholdAccountData d where date between ?1 and ?2")
	public List<Integer> sumIncome(String begin, String end);
	@Query("SELECT SUM(d.expense) FROM HouseholdAccountData d where date between ?1 and ?2")
	public List<Integer> sumExpense(String begin, String end);
	@Query("SELECT d FROM HouseholdAccountData d join CategoryData c on d.categoryId = c.categoryId order by id asc")
	public List<HouseholdAccountData> findByIdNotNullOrderByIdDesc();
}
