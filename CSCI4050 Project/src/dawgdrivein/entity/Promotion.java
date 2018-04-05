package dawgdrivein.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import dawgdrivein.db.PromotionDBA;

public class Promotion {
	private int id;
	
	@Column(name = "exp_date", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
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
	
	/**
	 * Saves this Promo object to the DB
	 * @return whether the save was successful
	 */
	public boolean savePromo()
	{
		return promoDBA.savePromo(this);
	}
	
	/**
	 * Deletes this Promo object from the DB
	 * @return whether the delete was completed
	 */
	public boolean deletePromo()
	{
		return promoDBA.deletePromo(this);
	}

	/**
	 * Updates this promo object in the DB
	 * @return whether the update was successful
	 */
	public boolean updatePromo()
	{
		return promoDBA.updatePromo(this);
	}
	
	/**
	 * Retrieves a Promo based on its ID from the DB
	 * @param id the ID to use as reference
	 * @return the retrieved Promo object
	 */
	public Promotion retrievePromotion(int id)
	{
		return promoDBA.retrievePromo(id);
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
