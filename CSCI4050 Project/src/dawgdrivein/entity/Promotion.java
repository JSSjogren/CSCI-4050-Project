package dawgdrivein.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import dawgdrivein.db.PromotionDBA;

@Entity
@Table(name = "Promotion")
public class Promotion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PromoId")
	private int id;
	
	@Column(name = "ExpDate", columnDefinition="DATETIME")
	private Date exp_date;
	
	@Column(name = "Code")
	private String code;
	
	@Column(name = "PercentDiscount")
	private int percent_discount;
	
	@Transient
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
		this.promoDBA = new PromotionDBA();
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
	
	public int retrievePromoAmount(String code)
	{
		return promoDBA.retrievePromoAmount(code);
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
