package com.capstore.service;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.capstore.exception.UserNotFoundException;
import com.capstore.model.ConfirmationToken;
import com.capstore.model.CustomerDetails;
import com.capstore.model.MerchantDetails;
import com.capstore.repository.ConfirmationTokenRepository;
import com.capstore.repository.MerchantRepository;

@Service
public class MerchantService implements MerchantServiceInterface {
	@Autowired
	MerchantRepository merchantRepo;
	@Autowired
	@PersistenceContext
	EntityManager entityManager;
	@Autowired
	private ConfirmationTokenRepository confirmationTokenRepository;
	@Autowired
	private EmailSenderService emailSenderService;


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

	@Override
	public ResponseEntity<?> registerMerchant(MerchantDetails md) throws Exception {
		MerchantDetails existingCustomer = findMerchantByEmailIgnoreCase(md.getEmail());
		if (existingCustomer != null) {
			throw new UserNotFoundException("This Email already Exist");
		} else {
			System.out.println("HIUIIIII" + md.getPassword());
			System.out.println("SOOOOOO" + PasswordProtector.encrypt(md.getPassword()));
			md.setPassword(PasswordProtector.encrypt(md.getPassword()));
			md.setAlternateEmail("hatt@gmail.com");
			// cd.setName("rohan");
			merchantRepo.save(md);
			System.out.println("hello" + md);
			MerchantDetails cd1 = findMerchantByEmailIgnoreCase(md.getEmail());

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

			return ResponseEntity.ok(HttpStatus.OK);
		}
	}
}
