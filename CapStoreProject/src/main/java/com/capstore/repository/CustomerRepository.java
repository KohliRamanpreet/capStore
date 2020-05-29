package com.capstore.repository;
import org.springframework.data.repository.CrudRepository;
import com.capstore.model.CustomerDetails;
public interface CustomerRepository extends CrudRepository<CustomerDetails, Integer> {

}
