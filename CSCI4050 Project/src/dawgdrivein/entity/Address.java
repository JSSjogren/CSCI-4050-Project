package dawgdrivein.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import dawgdrivein.db.AddressDBA;

@Entity
@Table(name = "Address")
public class Address {

	@Id
	@Column(name = "UserId")
	private int userId;
	
	@Column(name = "Street")
	private String street;
	
	@Column(name = "City")
	private String city;
	
	@Column(name = "State")
	private String state;
	
	@Column(name = "Zip")
	private int zip;
	
	@Transient
	private AddressDBA addressDBA;
	
	public Address(int userId, String street, String city, String state, int zip)
	{
		this.userId = userId;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
		
		this.addressDBA = new AddressDBA();
	}
	
	public Address()
	{
		this.userId = -1;
		this.street = null;
		this.city = null;
		this.state = null;
		this.zip = -1;
		
		this.addressDBA = new AddressDBA();
	}
	
	public boolean saveAddress()
	{
		return addressDBA.saveAddress(this);
	}
	
	public void updateAddress(int uID, String street, String city, String state, int zip)
	{
		addressDBA.updateAddress(uID, street, city, state, zip);
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}
}
