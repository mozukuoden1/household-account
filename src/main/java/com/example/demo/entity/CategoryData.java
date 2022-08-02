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

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class CategoryData {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "category_id")
	private int categoryId;
	@Column(name = "category_name")
	private String categoryName;
	@JsonIgnore
	@OneToMany(mappedBy = "categorydata", cascade = CascadeType.ALL)
	List<HouseholdAccountData> householdAccountData;
	
	public CategoryData() {
		
	}
}
