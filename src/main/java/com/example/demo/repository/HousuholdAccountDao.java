package com.example.demo.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface HousuholdAccountDao<T> extends Serializable {

	public List<T> findAll();
	public Optional<T> findById(int id);
}
