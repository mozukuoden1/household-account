package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.example.demo.entity.HouseholdAccountData;

public class HouseholdAccountDaoImp implements HousuholdAccountDao<HouseholdAccountData> {

	@PersistenceContext
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<HouseholdAccountData> findAll() {
		Query query = entityManager.createQuery("select * from HouseholdAccountData");
		List<HouseholdAccountData> list = query.getResultList();
		entityManager.close();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Optional<HouseholdAccountData> findById(int id) {
		Query query = entityManager.createQuery("select * from HouseholdAccountData where id = ?1").setParameter(1, id);
		Optional<HouseholdAccountData> result = (Optional<HouseholdAccountData>) query.getSingleResult();
		return result;
	}

}
