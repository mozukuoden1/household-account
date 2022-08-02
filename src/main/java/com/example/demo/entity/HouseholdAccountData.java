package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class HouseholdAccountData {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;
	@Column
	private String date;
	@Column(name = "category_id")
	private int categoryId;
	@Column
	private String memo;
	@Column
	private int income;
	@Column
	private int expense;	
	@ManyToOne
	@JoinColumn(name = "category_id", referencedColumnName = "category_id", insertable = false, updatable = false)
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
}
