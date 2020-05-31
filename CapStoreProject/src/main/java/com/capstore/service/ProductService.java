package com.capstore.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstore.model.Product;
import com.capstore.repository.ProductRepository;

@Service
public class ProductService implements ProductServiceInterface {
	@Autowired
	private ProductRepository productRepository;

	// Returns all products in database
	@Override
	public List<Product> allProductsList() {
		List<Product> allProducts = productRepository.findAll();
		List<Product> productList = allProducts.stream().map((customer) -> {
			return customer;
		}).collect(Collectors.toList());

		return productList;
	}

	// Returns products of specific category
	@Override
	public List<Product> specificCategoryProducts(String productCategory) {

		List<Product> allProducts = productRepository.findByProductCategory(productCategory);
		List<Product> productList = allProducts.stream().map((customer) -> {
			return customer;
		}).collect(Collectors.toList());

		return productList;

	}

	// Returns products of specific category and discount
	@Override
	public List<Product> specificDiscountProducts(Integer discount) {
		List<Product> allProducts = productRepository.findByDiscount(discount);
		List<Product> productList = allProducts.stream().map((customer) -> {
			return customer;
		}).collect(Collectors.toList());

		return productList;

	}

	@Override
	public List<Product> searchProducts(String category) {
		List<Product> allProducts = productList();
		List<Product> products = new ArrayList<Product>();
		for (Product product : allProducts) {
			if (product.getProductInfo().toLowerCase().contains(category.toLowerCase())) {
				products.add(product);
			}
		}
		return products;
	}

	/*
	 * @Override public List<Product> sortAsc() { return
	 * productRepository.findByOrderByProductPriceAsc(); }
	 * 
	 * @Override public List<Product> sortDesc() { return
	 * productRepository.findByOrderByProductPriceDesc(); }
	 * 
	 * @Override public List<Product> sortRasc() { // TODO Auto-generated method
	 * stub return productRepository.findByOrderByProductRatingAsc(); }
	 * 
	 * @Override public List<Product> sortRdesc() { // TODO Auto-generated method
	 * stub return productRepository.findByOrderByProductRatingDesc(); }
	 */

	private List<Product> productList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> filterAndCategory(String category, String order) {

		if (order.equals("Asc"))
			return productRepository.findByProductCategoryOrderByProductPriceAsc(category);
		if (order.equals("Desc"))
			return productRepository.findByProductCategoryOrderByProductPriceDesc(category);
		if (order.equals("Rasc"))
			return productRepository.findByProductCategoryOrderByProductRatingAsc(category);
		if (order.equals("Rdesc"))
			return productRepository.findByProductCategoryOrderByProductRatingDesc(category);

		return null;

	}

}
