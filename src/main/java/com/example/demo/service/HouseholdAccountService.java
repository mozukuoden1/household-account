package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.CategoryData;
import com.example.demo.entity.HouseholdAccountData;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.HouseholdAccountRepository;

@Service
public class HouseholdAccountService {

	@Autowired
	private HouseholdAccountRepository repository;
	@Autowired
	private CategoryRepository categoryReipository;
	
	public List<HouseholdAccountData> carrentDate() {
		LocalDate localDate = LocalDate.now();
		int year = localDate.getYear();
		int month = localDate.getMonthValue();
		return this.selectDate(year, month);
	}
	public List<HouseholdAccountData> selectDate(int year, int month) {
		LocalDate localDate = LocalDate.of(year, month, 1);
		String begin = localDate.toString();
		String end = localDate.plusMonths(1).minusDays(1).toString();
		List<HouseholdAccountData> list = repository.findByDateBetween(begin, end);
		return list;
	}
	
	public List<Integer> sumIncome() {
		LocalDate localDate = LocalDate.now();
		int year = localDate.getYear();
		int month = localDate.getMonthValue();
		localDate = LocalDate.of(year, month, 1);
		String begin = localDate.toString();
		String end = localDate.plusMonths(1).minusDays(1).toString();
		List<Integer> list = null;
		list = repository.sumIncome(begin, end);
		if(list.get(0) == null) {
			list.set(0, 0);
		}
		return list;
	}
	public List<Integer> sumExpence() {
		LocalDate localDate = LocalDate.now();
		int year = localDate.getYear();
		int month = localDate.getMonthValue();
		localDate = LocalDate.of(year, month, 1);
		String begin = localDate.toString();
		String end = localDate.plusMonths(1).minusDays(1).toString();
		List<Integer> list = null;
		list = repository.sumExpense(begin, end);
		if(list.get(0) == null) {
			list.set(0, 0);
		}
		return list;
	}
	
	public List<Integer> sumIncome(int year, int month) {
		LocalDate localDate = LocalDate.of(year, month, 1);
		String begin = localDate.toString();
		String end = localDate.plusMonths(1).minusDays(1).toString();
		List<Integer> list = null;
		list = repository.sumIncome(begin, end);
		if(list.get(0) == null) {
			list.set(0, 0);
		}
		return list;
	}
	public List<Integer> sumExpence(int year, int month) {
		LocalDate localDate = LocalDate.of(year, month, 1);
		String begin = localDate.toString();
		String end = localDate.plusMonths(1).minusDays(1).toString();
		List<Integer> list = repository.sumExpense(begin, end);
		if(list.get(0) == null) {
			list.set(0, 0);
		}
		return list;
	}
	@Transactional
	public void saveAndFlush(HouseholdAccountData data) {
		repository.saveAndFlush(data);
	}
	@Transactional
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
	@Transactional
	public Optional<HouseholdAccountData> findById(HouseholdAccountData data) {
		System.out.println("action:");
		Optional<HouseholdAccountData> result = repository.findById(data.getId());
		if(result.isPresent()) {
			return result;
		} else {
			System.out.println("action:");
			throw new NoSuchElementException();
		}
	}
	@Transactional
	public Optional<HouseholdAccountData> findById(int id) {
		System.out.println("action:");
		Optional<HouseholdAccountData> result = repository.findById(id);
		if(result.isPresent()) {
			return result;
		} else {
			System.out.println("action:");
			throw new NoSuchElementException();
		}
	}
	public void deleteById(HouseholdAccountData data) {
		repository.deleteById(data.getId());
	}
	
	public CategoryData findByCategoryName(int id) {
		CategoryData result = categoryReipository.findByCategoryId(id);
		
		return result;
	}
}
