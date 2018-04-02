package dawgdrivein.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import dawgdrivein.db.PromotionDBA;

public class Promotion {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "exp_date", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	
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

	public Promotion()
	{
		this.id = -1;
		this.exp_date = null;
		this.code = null;
		this.percent_discount = -1;
		this.promoDBA = null;
	}
	
	public boolean createPromo()
	{
		return promoDBA.createPromo(this);
	}
	
	public boolean removePromo()
	{
		return promoDBA.removePromo(this);
	}

	public boolean updatePromo()
	{
		return promoDBA.updatePromo(this);
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getPercent_discount() {
		return percent_discount;
	}

	public void setPercent_discount(int percent_discount) {
		this.percent_discount = percent_discount;
	}

}
