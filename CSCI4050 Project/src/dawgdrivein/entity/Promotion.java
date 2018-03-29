package dawgdrivein.entity;

import java.util.Date;

import dawgdrivein.db.PromotionDBA;

public class Promotion {

	private int id;
	private Date exp_date;
	private String code;
	private int percent_discount;
	
	PromotionDBA promoDBA;
	
	public Promotion(int id, Date exp_date, String code, int percent_discount)
	{
		this.id = id;
		this.exp_date = exp_date;
		this.code = code;
		this.percent_discount = percent_discount;
		promoDBA = new PromotionDBA();
	}
	
	public boolean createPromo()
	{
		return promoDBA.createPromo(this);
	}
	
	public boolean removePromo()
	{
		return promoDBA.removePromo(this);
	}
}
