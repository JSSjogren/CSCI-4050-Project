package dawgdrivein.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import dawgdrivein.db.CreditCardDBA;

@Entity
@Table(name = "CreditCard")
public class CreditCard {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CardId")
	private int id;
	
	@Column(name = "ExpDate", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date exp_date;
	
	@Column(name = "UserId")
	private int userId;
	
	@Column(name = "CardNo")
	private String card_number;
	
	@Column(name = "Ccv")
	private int CCV;
	
	@Column(name = "CardType")
	private String cardType;
	
	@Transient
	private CreditCardDBA ccDBA;
	
	public CreditCard(int id, Date exp_date, int userId, String card_number, int cCV, String cardType) {
		this.id = id;
		this.exp_date = exp_date;
		this.userId = userId;
		this.card_number = card_number;
		CCV = cCV;
		this.cardType = cardType;
		
		this.ccDBA = new CreditCardDBA();
	}
	
	public CreditCard()
	{
		this.id = -1;
		this.exp_date = null;
		this.userId = -1;
		this.card_number = null;
		CCV = -1;
		this.cardType = null;
		
		this.ccDBA = new CreditCardDBA();
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getExp_date() {
		return exp_date;
	}

	public void setExp_date(Date exp_date) {
		this.exp_date = exp_date;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getCard_number() {
		return card_number;
	}

	public void setCard_number(String card_number) {
		this.card_number = card_number;
	}

	public int getCCV() {
		return CCV;
	}

	public void setCCV(int cCV) {
		CCV = cCV;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

}
