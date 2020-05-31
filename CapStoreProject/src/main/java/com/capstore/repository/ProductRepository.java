package com.capstore.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.capstore.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
	List<Product> findByProductCategoryOrderByProductPriceAsc(String category);

	List<Product> findByProductCategory(String category);

	List<Product> findByDiscount(Integer disc);

	List<Product> findByProductCategoryOrderByProductPriceDesc(String category);

	List<Product> findByProductCategoryOrderByProductRatingAsc(String category);

	List<Product> findByProductCategoryOrderByProductRatingDesc(String category);

}
