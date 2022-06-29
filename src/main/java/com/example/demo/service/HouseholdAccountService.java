package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.CategoryData;
import com.example.demo.entity.HouseholdAccountData;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.HouseholdAccountRepository;

@Service
public class HouseholdAccountService {

	@Autowired
	HouseholdAccountRepository repository;
	@Autowired
	CategoryRepository categoryReipository;
	
	public List<HouseholdAccountData> carrentDate() {
		LocalDate localdate = LocalDate.now();
		int year = localdate.getYear();
		int month = localdate.getMonthValue();
		return this.selectDate(year, month);
	}
	public List<HouseholdAccountData> selectDate(int year, int month) {
		LocalDate localdate = LocalDate.of(year, month, 1);
		String begin = localdate.minusDays(1).toString();
		String end = localdate.plusMonths(1).toString();
		List<HouseholdAccountData> list = repository.findByDateBetween(begin, end);
		return list;
	}
	
	public void saveAndFlush(HouseholdAccountData data) {
		repository.saveAndFlush(data);
	}
	public void saveAndFlush(CategoryData data) {
		categoryReipository.saveAndFlush(data);
	}
	
	public List<HouseholdAccountData> findAll() {
		List<HouseholdAccountData> list = repository.findAll();
		return list;
	}
	
	/**
	* idごとの取得
	* @throws NoSuchElementException 指定されたIDはありません。
	*/
	public Optional<HouseholdAccountData> findById(HouseholdAccountData data, String value) {
		System.out.println("action:" + value);
		Optional<HouseholdAccountData> result = repository.findById(data.getId());
		if(result.isPresent()) {
			return result;
		} else {
			System.out.println("action:" + value);
			throw new NoSuchElementException(value);
		}
		
	}
	
	public void deleteById(HouseholdAccountData data) {
		repository.deleteById(data.getId());
	}
}
