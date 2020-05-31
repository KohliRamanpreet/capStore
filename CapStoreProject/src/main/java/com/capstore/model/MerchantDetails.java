package com.capstore.model;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "merchant_details")
public class MerchantDetails extends User {

	@Column(name = "phone_number")
	private String phoneNumber;
	@Column(name = "alternate_phone_number")
	private String alternatePhoneNumber;
	@Column(name = "alternate_email")
	private String alternateEmail;
	@Column(name = "is_approved")
	private boolean isApproved;
	@Column(name = "rating")
	private int rating;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = Product.class)
	private Set<Product> products;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = UserAddress.class)
	private Set<UserAddress> addresses;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = ProductFeedback.class)
	private Set<ProductFeedback> pF;

	public MerchantDetails() {
		super();
		this.isApproved = false;
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

	public boolean isApproved() {
		return isApproved;
	}

	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	public Set<UserAddress> getAddresses() {
		return addresses;
	}

	public void setAddresses(Set<UserAddress> addresses) {
		this.addresses = addresses;
	}

	public Set<ProductFeedback> getpF() {
		return pF;
	}

	public void setpF(Set<ProductFeedback> pF) {
		this.pF = pF;
	}

	@Override
	public String toString() {
		return "MerchantDetails [phoneNumber=" + phoneNumber + ", alternatePhoneNumber=" + alternatePhoneNumber
				+ ", alternateEmail=" + alternateEmail + ", isApproved=" + isApproved + ", rating=" + rating
				+ ", products=" + products + ", addresses=" + addresses + ", pF=" + pF + "]";
	}

}
