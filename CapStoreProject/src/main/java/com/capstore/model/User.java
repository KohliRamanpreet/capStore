package com.capstore.model;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class User{
	@Id
	@GeneratedValue(strategy  = GenerationType.AUTO)
	@Column(name="user_id")
	private int userId;  //( number auto generated): Primary Key
    private String name;
    private String username;  //(min: 3)
	private String password;    //(min: 8)
	private String email;
    private String role;           //(ROLE_CUSTOMER,ROLE_ADMIN,ROLE_MERCHANT)
    private boolean isActive;
	private String securityQuestion;
	private String securityAnswer;
	public User(int userId, String name, String username, String password, String email, String role, boolean isActive,
			String securityQuestion, String securityAnswer) {
		super();
		this.userId = userId;
		this.name = name;
		this.username = username;
		this.password = password;
		this.email = email;
		this.role = role;
		this.isActive = isActive;
		this.securityQuestion = securityQuestion;
		this.securityAnswer = securityAnswer;
	}
	public User() {
		super();
		this.isActive=false;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public String getSecurityQuestion() {
		return securityQuestion;
	}
	public void setSecurityQuestion(String securityQuestion) {
		this.securityQuestion = securityQuestion;
	}
	public String getSecurityAnswer() {
		return securityAnswer;
	}
	public void setSecurityAnswer(String securityAnswer) {
		this.securityAnswer = securityAnswer;
	}
	
}
