package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class HouseholdAccountData {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;
	@Column
	private String date;
	@Column
	private int categoryId;
	@Column
	private String memo;
	@Column
	private int income;
	@Column
	private int expense;	
	@ManyToOne
	@JoinColumn(name = "categoryId", referencedColumnName = "categoryId", insertable = false, updatable = false)
	CategoryData categorydata;
	
	
	public HouseholdAccountData(int id, String date, int categoryId, String memo, int income, int expense,
			CategoryData categorydata) {
		
		this.id = id;
		this.date = date;
		this.categoryId = categoryId;
		this.memo = memo;
		this.income = income;
		this.expense = expense;
		this.categorydata = categorydata;
	}
	public HouseholdAccountData() {
		
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public int getIncome() {
		return income;
	}
	public void setIncome(int income) {
		this.income = income;
	}
	public int getExpense() {
		return expense;
	}
	public void setExpense(int expense) {
		this.expense = expense;
	}
	public CategoryData getCategorydata() {
		return categorydata;
	}
	public void setCategorydata(CategoryData categorydata) {
		this.categorydata = categorydata;
	}
}
