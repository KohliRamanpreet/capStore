package com.capstore.repository;
	import org.springframework.data.jpa.repository.JpaRepository;
	import org.springframework.stereotype.Repository;

import com.capstore.model.Product;

	@Repository
	public interface DBFileRepository extends JpaRepository<Product, Integer> {

	}


