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
	@Column(name = "AddressId")
	private int addressId;
	
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
	
	public Address(int addressId, int userId, String street, String city, String state, int zip)
	{
		this.addressId = addressId;
		this.userId = userId;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
		
		this.addressDBA = new AddressDBA();
	}
	
	public Address()
	{
		this.addressId = -1;
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
}
