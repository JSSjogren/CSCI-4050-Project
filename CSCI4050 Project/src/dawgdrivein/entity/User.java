package dawgdrivein.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import dawgdrivein.db.UserDBA;

public class User {

	//Should allow us to auto-increment the User ID's
	@Id
	@GenericGenerator(name="gen",strategy="increment")
	@GeneratedValue(generator="gen")
	@Column(name = "id", unique = true, nullable = false, precision = 15, scale = 0)
	protected int id;
	
	protected String email;
	protected String password;
	protected String firstName;
	protected String lastName;
	protected String phoneNumber;
	protected String address;
	protected int status;
	protected int rank;
	
	public User(int id, String email, String password, String firstName, String lastName, String phoneNumber, String address, int status, int rank)
	{
		this.id = id;
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.status = status;
		this.rank = rank;
	}
	
	public User()
	{
		this.id = -1;
		this.email = null;
		this.password = null;
		this.firstName = null;
		this.lastName = null;
		this.phoneNumber = null;
		this.address = null;
		this.status = -1;
		this.rank = 01;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
}
