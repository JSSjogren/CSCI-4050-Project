package dawgdrivein.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import dawgdrivein.db.PriceDBA;

@Entity
@Table(name = "Price")
public class Price {

	@Id
	@Column(name = "type")
	private String type;
	
	@Column(name = "price")
	private double price;
	
	@Transient
	private PriceDBA priceDBA;
	
	public Price(String type, double price)
	{
		this.type = type;
		this.price = price;
		
		priceDBA = new PriceDBA();
	}
	
	public Price()
	{
		this.type = null;
		this.price = -1.0;
		
		priceDBA = new PriceDBA();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public void updatePrice()
	{
		priceDBA.updatePrice(this);
	}
	
	public double retrieveChildTicketPrice()
	{
		return priceDBA.retrieveChildTicketPrice();
	}
	
	public double retrieveAdultTicketPrice()
	{
		return priceDBA.retrieveAdultTicketPrice();
	}
	
	public double retrieveSeniorTicketPrice()
	{
		return priceDBA.retrieveSeniorTicketPrice();
	}
	
	public double retrieveOnlineFee()
	{
		return priceDBA.retrieveOnlineFee();
	}

	public double retrieveParkingSpaceFee()
	{
		return priceDBA.retrieveParkingSpaceFee();
	}
	
	public double retrievePriceByType(String type)
	{
		return priceDBA.retrievePriceByType(type);
	}
}
