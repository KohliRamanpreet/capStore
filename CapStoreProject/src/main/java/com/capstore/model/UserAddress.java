package com.capstore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user1_address")
public class UserAddress {

	@Id
	@Column(name = "address_id")
	private int addressId; 
	@Column(name = "address_line1")
    private String address_line1;
	@Column(name = "address_line2")
    private String address_line2;
	@Column(name = "district")
    private String district;
	@Column(name = "state")
    private String state;
	@Column(name = "landmark")
    private String landmark;
	@Column(name = "pincode")
    private int pinCode;
	@Column(name = "user_id")
    private int userId;  //(Foreign Key)
    
	public int getAddressId() {
		return addressId;
	}
	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
	public String getAddress_line1() {
		return address_line1;
	}
	public void setAddress_line1(String address_line1) {
		this.address_line1 = address_line1;
	}
	public String getAddress_line2() {
		return address_line2;
	}
	public void setAddress_line2(String address_line2) {
		this.address_line2 = address_line2;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getLandmark() {
		return landmark;
	}
	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}
	public int getPinCode() {
		return pinCode;
	}
	public void setPinCode(int pinCode) {
		this.pinCode = pinCode;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public UserAddress() {
	}
    
    
    
}
