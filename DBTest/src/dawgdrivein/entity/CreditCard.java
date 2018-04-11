package dawgdrivein.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import dawgdrivein.db.CreditCardDBA;

public class CreditCard {
	
	@Column(name = "exp_date", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date exp_date;
	
	private int id;
	private int card_number;
	private String name_on_card;
	private int CCV;
	private String billing_address;
	
	private CreditCardDBA ccDBA;
	
	public CreditCard(int id, int card_number, Date exp_date, String name_on_card, int CCV, String billing_address)
	{
		this.id = id;
		this.card_number = card_number;
		this.exp_date = exp_date;
		this.name_on_card = name_on_card;
		this.CCV = CCV;
		this.billing_address = billing_address;
		
		ccDBA = new CreditCardDBA();
	}
	
	public CreditCard()
	{
		this.id = -1;
		this.card_number = -1;
		this.exp_date = null;
		this.name_on_card = null;
		this.CCV = -1;
		this.billing_address = null;
		
		ccDBA = new CreditCardDBA();
	}
	
	/**
	 * Save specified credit card object in database
	 * @return whether or not save was successful
	 */
	public boolean saveCreditCard()
	{
		return ccDBA.saveCreditCard(this);
	}
	
	/**
	 * Update specified credit card object in database
	 * @return whether or not update was successful
	 */
	public boolean updateCreditCard()
	{
		return ccDBA.updateCreditCard(this);
	}
	
	/**
	 * Delete specified credit card object from database
	 * @return whether or not deletion was successful
	 */
	public boolean deleteCreditCard()
	{
		return ccDBA.deleteCreditCard(this);
	}
	
	/**
	 * Retrieve a CreditCard by ID from the database
	 * @param id the ID of the credit card to be grabbed
	 * @return the CreditCard object from the Database
	 */
	public CreditCard retrieveCreditCard(int id)
	{
		return ccDBA.retrieveCreditCard(id);
	}
}
