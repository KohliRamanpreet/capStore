package com.capstore.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Cart1")
public class Cart {

	@Id
	@Column(name = "cart_id")
    private int cartId;   //(Primary Key)
	@Column(name = "user_id")
	private int userID;  //(references cust_id)
	@Column(name = "type")
    private String type;  //(“Wishlist”,”cart”)
	@Column(name = "product_id")
	private int productId;
	@Column(name = "product_quantity")
	private int quantity;
	
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public int getCartId() {
		return cartId;
	}
	public void setCartId(int cartId) {
		this.cartId = cartId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public Cart() {
	}

}
