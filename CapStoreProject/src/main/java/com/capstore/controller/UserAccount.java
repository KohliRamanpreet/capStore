package com.capstore.controller;

import java.util.List;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capstore.exception.IncorrectPasswordException;
import com.capstore.exception.InvalidUserException;
import com.capstore.exception.UserAlreadyExistException;
import com.capstore.exception.UserNotFoundException;
import com.capstore.model.ConfirmationToken;
import com.capstore.model.CustomerDetails;
import com.capstore.model.MerchantDetails;
import com.capstore.model.Product;
import com.capstore.repository.ConfirmationTokenRepository;
import com.capstore.repository.CustomerRepository;
import com.capstore.repository.MerchantRepository;
import com.capstore.service.EmailSenderService;
import com.capstore.service.MerchantService;
import com.capstore.service.MerchantServiceInterface;
import com.capstore.service.PasswordProtector;
import com.capstore.service.ProductService;
import com.capstore.service.ProductServiceInterface;
import com.capstore.service.CustomerServiceInterface;

@RestController
@RequestMapping("/api1")
@CrossOrigin(origins = "http://localhost:4200")
public class UserAccount {

	@Autowired
	private CustomerServiceInterface customerService;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private EmailSenderService emailSenderService;
	@Autowired
	private MerchantServiceInterface merchantService;
	@Autowired
	private ProductServiceInterface productService;

	@PostMapping("/registerCustomer")
	public ResponseEntity<?> registerCustomer(@RequestBody CustomerDetails cd) throws Exception {
		return customerService.registerCustomer(cd);
	}

	@PostMapping("/registerMerchant")
	public ResponseEntity<?> registerMerchant(@RequestBody MerchantDetails md) throws Exception {
		return merchantService.registerMerchant(md);
	}

	@RequestMapping(value = "/confirm-account", method = { RequestMethod.GET, RequestMethod.POST })
	public boolean confirmUserAccount(@RequestParam("token") String confirmationToken) {
		return emailSenderService.verifyEmail(confirmationToken);
	}

	@GetMapping("/login")
	public ResponseEntity<?> fetchCustomer(@RequestParam String email, @RequestParam String password) throws Exception {
		return customerService.loginCustomer(email, password);
	}

	@RequestMapping("/deletecustomer/{a}")
	public boolean deleteCustomer(@PathVariable String a) {

		CustomerDetails entity = customerService.findCustomerByEmailIgnoreCase(a);
		customerRepository.delete(entity);
		return true;
	}

	// All Products Data
	@GetMapping(value = "/allproducts")
	public List<Product> getAllProducts() {
		return productService.allProductsList();
	}

	// Products data of particular category
	@GetMapping(value = "/productcategory/{category}")
	public List<Product> getCategory(@PathVariable("category") String productCategory) {

		return productService.specificCategoryProducts(productCategory);
	}

	// Product data based on discount
	@GetMapping(value = "/discountcategory/{discountPercent}")
	public List<Product> getDiscountProducts(@PathVariable("discountPercent") Integer discount) {

		return productService.specificDiscountProducts(discount);
	}

	@GetMapping(value = "/searchProducts/{category}")
	public List<Product> getSearchProducts(@PathVariable("category") String productSearch) {
		return productService.searchProducts(productSearch);
	}

	@GetMapping("/{category}/{order}")
	public List<Product> filterandcategory(@PathVariable String category, @PathVariable String order) {
		return productService.filterAndCategory(category, order);
	}

	@GetMapping("/forgotpassword/{email}")
	public ResponseEntity<?> forgotPassword(@PathVariable("email") String email) throws Exception {
		System.out.println("HIII");
		return customerService.forgotPassword(email);
	}

	@ExceptionHandler(InvalidUserException.class)
	public ResponseEntity<?> invalidUserErrorMessage(InvalidUserException unf) {
		JSONObject obj = new JSONObject();
		obj.put("error", "true");
		obj.put("message", unf.getMessage());
		obj.put("object", null);
		return ResponseEntity.ok().body(obj);
	}

	@ExceptionHandler(IncorrectPasswordException.class)
	public ResponseEntity<?> incorrectPasswordErrorMessage(IncorrectPasswordException unf) {
		JSONObject obj = new JSONObject();
		obj.put("error", "true");
		obj.put("message", unf.getMessage());
		obj.put("object", null);
		return ResponseEntity.ok().body(obj);
	}

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<?> noUserErrorMessage(UserNotFoundException unf) {
		JSONObject obj = new JSONObject();
		obj.put("error", "true");
		obj.put("message", unf.getMessage());
		obj.put("object", null);
		return ResponseEntity.ok().body(obj);
	}

	@ExceptionHandler(UserAlreadyExistException.class)
	public ResponseEntity<?> userAlreadyErrorMessage(UserAlreadyExistException unf) {
		JSONObject obj = new JSONObject();
		obj.put("error", "true");
		obj.put("message", unf.getMessage());
		obj.put("object", null);
		return ResponseEntity.ok().body(obj);
	}

}
