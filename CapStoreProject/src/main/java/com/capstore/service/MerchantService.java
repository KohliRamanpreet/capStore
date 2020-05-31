package com.capstore.service;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstore.model.MerchantDetails;
import com.capstore.repository.MerchantRepository;

@Service
public class MerchantService implements MerchantServiceInterface {
	@Autowired
	MerchantRepository merchantRepo;
	@Autowired
	@PersistenceContext
	EntityManager entityManager;

	@Override
	public MerchantDetails findMerchantByEmailIgnoreCase(String email) {
		MerchantDetails md = null;
		try {
			TypedQuery<MerchantDetails> q2 = entityManager.createQuery(
					"SELECT cd from MerchantDetails md where email='" + email + "'", MerchantDetails.class);
			md = (MerchantDetails) q2.getSingleResult();
		} catch (NoResultException nre) {
		}

		return md;
	}

	public void saveMerchant(MerchantDetails md) {
		entityManager.persist(md);
		entityManager.flush();
	}

	@Override
	public MerchantDetails findMerchantById(int id) {

		return merchantRepo.findById(id).get();
	}
}
