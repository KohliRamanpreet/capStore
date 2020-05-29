package com.capstore.model;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name = "customer1_details")
public class CustomerDetails extends User {
	
	 @Override
	public String toString() {
		return "CustomerDetails [phoneNumber=" + phoneNumber + ", alternatePhoneNumber=" + alternatePhoneNumber
				+ ", alternateEmail=" + alternateEmail + ", address=" + address + "]";
	}

	@Column(name = "phone_number")
	    private String phoneNumber;
	    @Column(name = "alternate_phone_number")
	    private String alternatePhoneNumber;
	    @Column(name = "alternate_email")
	    private String alternateEmail;
	    @Column(name="address")
	    private String address;
	    
		
		  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity =
		  CommonFeedback.class) Set<CommonFeedback> cCF;
		  
		  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity =
		  ProductFeedback.class) Set<ProductFeedback> cPF;
		  
		  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity =
		  Order.class) Set<Order> orders;
		  
		  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity =
		  Cart.class) private Set<Cart> cC;
		  
		  
		  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity =
		  UserAddress.class) private Set<UserAddress> addresses;
		 
		 
		  public Set<Order> getOrders() { return orders; } public void
		  setOrders(Set<Order> orders) { this.orders = orders; }
		 
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}

		
		  public Set<Cart> getCustomerCarts() { return cC; } public void
		  setCustomerCarts(Set<Cart> customerCarts) { this.cC = customerCarts; }
		  
		  public Set<CommonFeedback> getcCF() { return cCF; } public void
		  setcCF(Set<CommonFeedback> cCF) { this.cCF = cCF; } public
		  Set<ProductFeedback> getcPF() { return cPF; } public void
		  setcPF(Set<ProductFeedback> cPF) { this.cPF = cPF; } public Set<Cart> getcC()
		  { return cC; } public void setcC(Set<Cart> cC) { this.cC = cC; }
		 
		public String getPhoneNumber() {
			return phoneNumber;
		}
		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		}
		public String getAlternatePhoneNumber() {
			return alternatePhoneNumber;
		}
		public void setAlternatePhoneNumber(String alternatePhoneNumber) {
			this.alternatePhoneNumber = alternatePhoneNumber;
		}
		public String getAlternateEmail() { 
			return alternateEmail;
		}
		public void setAlternateEmail(String alternateEmail) {
			this.alternateEmail = alternateEmail;
		}
		
		
		  public Set<CommonFeedback> getFeedbacks() { return cCF; } public void
		  setFeedbacks(Set<CommonFeedback> feedbacks) { this.cCF = feedbacks; }
		  
		  Set<UserAddress> getAddresses() { return addresses; } public void
		  setAddresses(Set<UserAddress> addresses) { this.addresses = addresses; }
		  
		  public Set<ProductFeedback> getProductFeedbacks() { return cPF;} public void
		  setProductFeedbacks(Set<ProductFeedback> productFeedbacks) { this.cPF =
		  productFeedbacks; }
		 
		public CustomerDetails() {
		}
}
