package com.capstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.capstore.model.ConfirmationToken;
import com.capstore.model.CustomerDetails;
import com.capstore.repository.ConfirmationTokenRepository;
import com.capstore.repository.CustomerRepository;
import com.capstore.service.DBFileStorageService;
import com.capstore.service.EmailSenderService;
import com.capstore.service.customerServiceInterface;
@RestController
@RequestMapping("/api1")
@CrossOrigin(origins = "http://localhost:4200")
public class UserAccount
{
	 @Autowired
	    private DBFileStorageService dbFileStorageService;

	    @PostMapping("/uploadFile")
	    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
	        DBFile dbFile = dbFileStorageService.storeFile(file);

	        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
	                .path("/downloadFile/")
	                .path(dbFile.getId())
	                .toUriString();

	        return new UploadFileResponse(dbFile.getFileName(), fileDownloadUri,
	                file.getContentType(), file.getSize());
	    }

	@Autowired
	private customerServiceInterface customerService;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private ConfirmationTokenRepository confirmationTokenRepository;
	@Autowired
	private EmailSenderService emailSenderService;

    @PostMapping("/registerCustomer")
    public boolean registerCustomer(@RequestBody CustomerDetails cd)
    {
  //CustomerDetails cd=new CustomerDetails(); //htana
    	 CustomerDetails existingCustomer = customerService.findCustomerByEmailIgnoreCase(cd.getEmail());
	 if(existingCustomer !=
	  null) { return false; } else {
	 
			 
		  //cd.setEmail(email); //htana
		  cd.setAlternateEmail("hatt@gmail.com");
		  //cd.setName("rohan");
		  customerRepository.save(cd);
		  System.out.println("hello"+cd);
		  CustomerDetails cd1=customerService.findCustomerByEmailIgnoreCase(cd.getEmail());
		  
		  ConfirmationToken confirmationToken = new ConfirmationToken(cd.getUserId());
	
		  System.out.println("hiii"+confirmationToken.getConfirmationToken());
		  System.out.println("hiii"+confirmationToken.getTokenid());
		  System.out.println("hiii"+confirmationToken.getCreatedDate());
		  System.out.println("hiii"+confirmationToken);
		  
		  confirmationTokenRepository.save(confirmationToken);
		  
		  SimpleMailMessage mailMessage = new SimpleMailMessage();
		  mailMessage.setTo(cd.getEmail());
		  mailMessage.setSubject("Complete Registration!");
		  mailMessage.setFrom("ramanpreetkaur.official@gmail.com");
		  mailMessage.setText("To confirm your account, please click here : "
		  +"http://localhost:8083/api1/confirm-account?token="+confirmationToken.
		  getConfirmationToken());
		  
		  emailSenderService.sendEmail(mailMessage);
		  
		  return true; 
	  }
    }
    
		 
    

	
	/*
	 * @PostMapping(value="/registerMerchant") public ResponseEntity<?>
	 * registerMerchant(@Valid @RequestBody MerchantDetails md) {
	 * 
	 * MerchantDetails existingMerchant =
	 * userRepository.findMerchantByEmailIgnoreCase(md.getEmail());
	 * if(existingMerchant != null) { return new
	 * ResponseEntity<Error>(HttpStatus.CONFLICT); } else {
	 * userRepository.saveMerchant(md);
	 * 
	 * ConfirmationToken confirmationToken = new ConfirmationToken(md);
	 * 
	 * confirmationTokenRepository.save(confirmationToken);
	 * 
	 * SimpleMailMessage mailMessage = new SimpleMailMessage();
	 * mailMessage.setTo(md.getEmail());
	 * mailMessage.setSubject("Complete Registration!");
	 * mailMessage.setFrom("himanshu.rathod1998@gmail.com");
	 * mailMessage.setText("To confirm your account, please click here : "
	 * +"http://localhost:8082/confirm-account?token="+confirmationToken.
	 * getConfirmationToken());
	 * 
	 * emailSenderService.sendEmail(mailMessage);
	 * 
	 * return ResponseEntity.ok(HttpStatus.OK); } }
	 * 
	 */
    @RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
    public boolean confirmUserAccount(@RequestParam("token")String confirmationToken)
    {
    	
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

        if(token != null)
        {
            if(customerService.findCustomerById(token.getUid())!=null) {
            	CustomerDetails cd=customerService.findCustomerById(token.getUid());
            	cd.setActive(true);
                customerRepository.save(cd);
			/*
			 * else
			 * if(userRepository.findMerchantByEmailIgnoreCase(token.getMd().getEmail())!=
			 * null) { MerchantDetails
			 * md=userRepository.findMerchantByEmailIgnoreCase(token.getMd().getEmail());
			 * md.setActive(true); userRepository.saveMerchant(md); }
			 */
            
            return true;
            }
            else
            	return false;
        }
        else
        {
        	System.out.println("done");
        	return false;
        }
    }
    @RequestMapping("/login")
    public CustomerDetails fetchCustomer(@RequestParam String email,@RequestParam String password)
    {
   System.out.println("HIIIII"+customerService.findCustomerByEmailIgnoreCase(email));
   CustomerDetails cd1=customerService.findCustomerByEmailIgnoreCase(email);
   if(cd1!=null)
   {
	  if(cd1.isActive())
	  {
	  if(cd1.getPassword().equals(password))
		return cd1;
	  else 
		 return null;
	  }
	  else
		  return null;
   }
   else
   {
    	return null;
   }
    }
    @RequestMapping("/deletecustomer/{a}")
    public boolean deleteCustomer(@PathVariable String a)
    {
    
    	CustomerDetails entity=customerService.findCustomerByEmailIgnoreCase(a);
    	 customerRepository.delete(entity);
    	 return true;
    }
    
 
}
