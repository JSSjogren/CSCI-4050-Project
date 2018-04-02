package dawgdrivein.entity;

import java.util.Date;

import dawgdrivein.db.CreditCardDBA;

public class CreditCard {
	private int id;
	private int card_number;
	private Date exp_date;
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
	
	public boolean saveCreditCard()
	{
		return ccDBA.saveCreditCard(this);
	}
}
