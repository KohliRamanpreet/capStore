package com.capstore.service;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.capstore.exception.IncorrectPasswordException;
import com.capstore.exception.InvalidUserException;
import com.capstore.exception.UserNotFoundException;
import com.capstore.model.ConfirmationToken;
import com.capstore.model.CustomerDetails;
import com.capstore.repository.ConfirmationTokenRepository;
import com.capstore.repository.CustomerRepository;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

@Service
public class CustomerService implements CustomerServiceInterface {
	@Autowired
	CustomerRepository customerRepo;
	@Autowired
	@PersistenceContext
	EntityManager entityManager;
	@Autowired
	private ConfirmationTokenRepository confirmationTokenRepository;
	@Autowired
	private EmailSenderService emailSenderService;

	@Override
	public CustomerDetails findCustomerByEmailIgnoreCase(String email) {
		CustomerDetails cd = null;
		try {
			TypedQuery<CustomerDetails> q2 = entityManager.createQuery(
					"SELECT cd from CustomerDetails cd where email='" + email + "'", CustomerDetails.class);
			cd = (CustomerDetails) q2.getSingleResult();
		} catch (NoResultException nre) {
		}

		return cd;
	}

	public void saveCustomer(CustomerDetails cd) {
		entityManager.persist(cd);
		entityManager.flush();
	}

	@Override
	public CustomerDetails findCustomerById(int id) {

		return customerRepo.findById(id).get();
	}

	@Override
	public ResponseEntity<?> loginCustomer(String email, String password) throws Exception {
		JSONObject obj = new JSONObject();
		System.out.println("HIIIII" + findCustomerByEmailIgnoreCase(email));
		CustomerDetails cd1 = findCustomerByEmailIgnoreCase(email);
		if (cd1 != null) {
			if (cd1.isActive()) {
				if (password.equals(PasswordProtector.decrypt(cd1.getPassword()))) {
					obj.put("error", "false");
					obj.put("message", null);
					obj.put("object", cd1);
					return ResponseEntity.ok().body(obj);
				} else {
					throw new IncorrectPasswordException("Incorrect Password");
				}
			} else {
				throw new InvalidUserException("Email is not verified");
			}
		} else {
			throw new UserNotFoundException("No customer exists with this Email Id");
		}

	}

	@Override
	public ResponseEntity<?> registerCustomer(CustomerDetails cd) throws Exception {
		CustomerDetails existingCustomer = findCustomerByEmailIgnoreCase(cd.getEmail());
		if (existingCustomer != null) {
			throw new UserNotFoundException("This Email already Exist");
		} else {
			System.out.println("HIUIIIII" + cd.getPassword());
			System.out.println("SOOOOOO" + PasswordProtector.encrypt(cd.getPassword()));
			cd.setPassword(PasswordProtector.encrypt(cd.getPassword()));
			cd.setAlternateEmail("hatt@gmail.com");
			// cd.setName("rohan");
			customerRepo.save(cd);
			System.out.println("hello" + cd);
			CustomerDetails cd1 = findCustomerByEmailIgnoreCase(cd.getEmail());

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
					+ "http://localhost:8080/api1/confirm-account?token=" + confirmationToken.getConfirmationToken());

			emailSenderService.sendEmail(mailMessage);

			return ResponseEntity.ok(HttpStatus.OK);
		}
	}

	@Override
	public ResponseEntity<?> forgotPassword(String email) throws Exception {
		JSONObject obj=new JSONObject();
		CustomerDetails cd = findCustomerByEmailIgnoreCase(email);
		if (cd == null) {
			throw new UserNotFoundException("This Email doest not Exist");
		} else {
			System.out.println("HI" + cd.getPassword());
			System.out.println("SO" + PasswordProtector.decrypt(cd.getPassword()));
			String pass=(PasswordProtector.decrypt(cd.getPassword()));
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setTo(cd.getEmail());
			mailMessage.setSubject("CapStore Password Retrieval");
			mailMessage.setFrom("ramanpreetkaur.official@gmail.com");
			mailMessage.setText("Your password:"+ pass);

			emailSenderService.sendEmail(mailMessage);

			obj.put("error", "false");
			obj.put("message", "Password has been sent to your email");
			obj.put("object", null);
			return ResponseEntity.ok().body(obj);
		}
	}

}
