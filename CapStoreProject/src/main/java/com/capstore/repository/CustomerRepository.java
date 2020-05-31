package com.capstore.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.capstore.model.CustomerDetails;
@Repository
public interface CustomerRepository extends CrudRepository<CustomerDetails, Integer> {

}
