package com.capstore.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "transaction1")
public class Transaction {

	@Id
	@Column(name = "transaction_id")
	private int transactionId;
	@Column(name = "order_id")
    private int orderId;
    @Column(name = "transaction_date")
    private Date transactionDate;
    @Column(name = "transaction_money")
    private int transactionMoney;
    @Column(name = "transaction_method")
    private String transactionMethod; 
    @Column(name = "transaction_status")
    private String transactionStatus;   
    
	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public Date getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	public int getTransactionMoney() {
		return transactionMoney;
	}
	public void setTransactionMoney(int transactionMoney) {
		this.transactionMoney = transactionMoney;
	}
	public String getTransactionMethod() {
		return transactionMethod;
	}
	public void setTransactionMethod(String transactionMethod) {
		this.transactionMethod = transactionMethod;
	}
	public String getTransactionStatus() {
		return transactionStatus;
	}
	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}
	public Transaction() {
	}
    
}
