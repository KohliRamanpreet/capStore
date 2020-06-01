package com.capstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.capstore.model.ConfirmationToken;
import com.capstore.model.CustomerDetails;
import com.capstore.model.MerchantDetails;
import com.capstore.repository.ConfirmationTokenRepository;
import com.capstore.repository.CustomerRepository;
import com.capstore.repository.MerchantRepository;

@Service
public class EmailSenderService {
	@Autowired
	private CustomerServiceInterface customerService;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private ConfirmationTokenRepository confirmationTokenRepository;
	@Autowired
	private MerchantServiceInterface merchantService;
	@Autowired
	private MerchantRepository merchantRepository;
	private JavaMailSender javaMailSender;

	@Autowired
	public EmailSenderService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	@Async
	public void sendEmail(SimpleMailMessage email) {
		javaMailSender.send(email);
	}

	public boolean verifyEmail(String confirmationToken) {
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
}
