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
	@Column(name = "phone_number")
	private String phoneNumber;
	@Column(name = "alternate_phone_number")
	private String alternatePhoneNumber;
	@Column(name = "alternate_email")
	private String alternateEmail;
	@Column(name = "address")
	private String address;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = CommonFeedback.class)
	Set<CommonFeedback> cCF;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = ProductFeedback.class)
	Set<ProductFeedback> cPF;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = Order.class)
	Set<Order> orders;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = Cart.class)
	private Set<Cart> cC;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = UserAddress.class)
	private Set<UserAddress> addresses;

	public CustomerDetails(int userId, String name, String username, String password, String email, String role,
			boolean isActive, String securityQuestion, String securityAnswer, String phoneNumber,
			String alternatePhoneNumber, String alternateEmail, String address, Set<CommonFeedback> cCF,
			Set<ProductFeedback> cPF, Set<Order> orders, Set<Cart> cC, Set<UserAddress> addresses) {
		super(userId, name, username, password, email, role, isActive, securityQuestion, securityAnswer);
		this.phoneNumber = phoneNumber;
		this.alternatePhoneNumber = alternatePhoneNumber;
		this.alternateEmail = alternateEmail;
		this.address = address;
		this.cCF = cCF;
		this.cPF = cPF;
		this.orders = orders;
		this.cC = cC;
		this.addresses = addresses;
	}

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Set<CommonFeedback> getcCF() {
		return cCF;
	}

	public void setcCF(Set<CommonFeedback> cCF) {
		this.cCF = cCF;
	}

	public Set<ProductFeedback> getcPF() {
		return cPF;
	}

	public void setcPF(Set<ProductFeedback> cPF) {
		this.cPF = cPF;
	}

	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

	public Set<Cart> getcC() {
		return cC;
	}

	public void setcC(Set<Cart> cC) {
		this.cC = cC;
	}

	public Set<UserAddress> getAddresses() {
		return addresses;
	}

	public void setAddresses(Set<UserAddress> addresses) {
		this.addresses = addresses;
	}

	@Override
	public String toString() {
		return "CustomerDetails [phoneNumber=" + phoneNumber + ", alternatePhoneNumber=" + alternatePhoneNumber
				+ ", alternateEmail=" + alternateEmail + ", address=" + address + ", cCF=" + cCF + ", cPF=" + cPF
				+ ", orders=" + orders + ", cC=" + cC + ", addresses=" + addresses + "]";
	}

}
