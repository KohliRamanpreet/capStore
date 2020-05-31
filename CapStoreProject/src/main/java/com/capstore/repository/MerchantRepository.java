package com.capstore.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.capstore.model.MerchantDetails;
@Repository
public interface MerchantRepository extends CrudRepository<MerchantDetails, Integer> {

}