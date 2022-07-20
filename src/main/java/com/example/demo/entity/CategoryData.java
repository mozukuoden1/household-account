package com.example.demo.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class CategoryData {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "category_id")
	private int categoryId;
	@Column
	private String categoryname;
	@JsonIgnore
	@OneToMany(mappedBy = "categorydata", cascade = CascadeType.ALL)
	List<HouseholdAccountData> householdAccountData;
	
	public CategoryData() {
		
	}
	
	public String getCategoryname() {
		return categoryname;
	}

	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}

	public List<HouseholdAccountData> getHouseholdAccountData() {
		return householdAccountData;
	}

	public void setHouseholdAccountData(List<HouseholdAccountData> householdAccountData) {
		this.householdAccountData = householdAccountData;
	}

	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	
}
