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
	private ConfirmationTokenRepository confirmationTokenRepository;
	@Autowired
	private EmailSenderService emailSenderService;
	@Autowired
	private MerchantServiceInterface merchantService;
	@Autowired
	private MerchantRepository merchantRepository;
	@Autowired
	private ProductServiceInterface productService;

	@PostMapping("/registerCustomer")
	public ResponseEntity<?> registerCustomer(@RequestBody CustomerDetails cd) throws Exception {
		CustomerDetails existingCustomer = customerService.findCustomerByEmailIgnoreCase(cd.getEmail());
		if (existingCustomer != null) {
			return new ResponseEntity<Error>(HttpStatus.CONFLICT);
		} else {
			System.out.println("HIUIIIII" + cd.getPassword());
			System.out.println("SOOOOOO" + PasswordProtector.encrypt(cd.getPassword()));
			cd.setPassword(PasswordProtector.encrypt(cd.getPassword()));
			cd.setAlternateEmail("hatt@gmail.com");
			// cd.setName("rohan");
			customerRepository.save(cd);
			System.out.println("hello" + cd);
			CustomerDetails cd1 = customerService.findCustomerByEmailIgnoreCase(cd.getEmail());

			ConfirmationToken confirmationToken = new ConfirmationToken(cd.getUserId());

			System.out.println("hiii" + confirmationToken.getConfirmationToken());
			System.out.println("hiii" + confirmationToken.getTokenid());
			System.out.println("hiii" + confirmationToken.getCreatedDate());
			System.out.println("hiii" + confirmationToken);

			confirmationTokenRepository.save(confirmationToken);

			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setTo(cd.getEmail());
			mailMessage.setSubject("Complete Registration!");
			mailMessage.setFrom("ramanpreetkaur.official@gmail.com");
			mailMessage.setText("To confirm your account, please click here : "
					+ "http://localhost:8083/api1/confirm-account?token=" + confirmationToken.getConfirmationToken());

			emailSenderService.sendEmail(mailMessage);

			return ResponseEntity.ok(HttpStatus.OK);
		}
	}

	@PostMapping("/registerMerchant")
	public boolean registerMerchant(@RequestBody MerchantDetails md) {
		// CustomerDetails cd=new CustomerDetails(); //htana
		MerchantDetails existingCustomer = merchantService.findMerchantByEmailIgnoreCase(md.getEmail());
		if (existingCustomer != null) {
			return false;
		} else {

			// cd.setEmail(email); //htana
			md.setAlternateEmail("hatt@gmail.com");
			// cd.setName("rohan");
			merchantRepository.save(md);
			System.out.println("hello" + md);
			CustomerDetails cd1 = customerService.findCustomerByEmailIgnoreCase(md.getEmail());

			ConfirmationToken confirmationToken = new ConfirmationToken(md.getUserId());

			System.out.println("hiii" + confirmationToken.getConfirmationToken());
			System.out.println("hiii" + confirmationToken.getTokenid());
			System.out.println("hiii" + confirmationToken.getCreatedDate());
			System.out.println("hiii" + confirmationToken);

			confirmationTokenRepository.save(confirmationToken);

			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setTo(md.getEmail());
			mailMessage.setSubject("Complete Registration!");
			mailMessage.setFrom("ramanpreetkaur.official@gmail.com");
			mailMessage.setText("To confirm your account, please click here : "
					+ "http://localhost:8083/api1/confirm-account?token=" + confirmationToken.getConfirmationToken());

			emailSenderService.sendEmail(mailMessage);

			return true;
		}
	}

	@RequestMapping(value = "/confirm-account", method = { RequestMethod.GET, RequestMethod.POST })
	public boolean confirmUserAccount(@RequestParam("token") String confirmationToken) {

		ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

		if (token != null) {
			if (customerService.findCustomerById(token.getUid()) != null) {
				CustomerDetails cd = customerService.findCustomerById(token.getUid());
				cd.setActive(true);
				customerRepository.save(cd);
				return true;
			}

			else if (merchantService.findMerchantById(token.getUid()) != null) {
				MerchantDetails md = merchantService.findMerchantById(token.getUid());
				md.setActive(true);
				merchantRepository.save(md);
				return true;
			}

			else
				return false;
		}

		else {
			System.out.println("done");
			return false;
		}
	}

	@GetMapping("/login")
	public ResponseEntity<?> fetchCustomer(@RequestParam String email, @RequestParam String password) throws Exception {
		JSONObject obj = new JSONObject();
		System.out.println("HIIIII" + customerService.findCustomerByEmailIgnoreCase(email));
		CustomerDetails cd1 = customerService.findCustomerByEmailIgnoreCase(email);
		if (cd1 != null) {
			if (cd1.isActive()) {
				if (password.equals(PasswordProtector.decrypt(cd1.getPassword()))) {
					obj.put("error", "false");
					obj.put("object", cd1);
					return ResponseEntity.ok().body(obj);
				} else {
					obj.put("error", "true");
					obj.put("message", "Incorrect password");
					return ResponseEntity.ok().body(obj);
				}
			} else {
				obj.put("error", "true");
				obj.put("message", "Email not verified");
				return ResponseEntity.ok().body(obj);
			}
			// return new ResponseEntity<>(
			// "Email is not verified",
			// HttpStatus.BAD_REQUEST);
		} else {
			//obj.put("error", "true");
			//obj.put("message", "Email does not exist");
			//return ResponseEntity.ok().body(obj);
			throw new UserNotFoundException("No user exists with this Account Id");
		}
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
	@GetMapping(value = "/discountCategory/{discountPercent}")
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
	
	  @ExceptionHandler(UserNotFoundException.class) 
	  public JSONObject noUserErrorMessage(UserNotFoundException unf) { JSONObject obj = new
	  JSONObject(); obj.put("error", "true"); obj.put("message", unf.getMessage());
	  return obj; }
	 

}
