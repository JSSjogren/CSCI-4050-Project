package dawgdrivein.entity;

import dawgdrivein.db.ReviewDBA;

public class Review {

	private int id;
	private String content;
	private int star_rating;
	private ReviewDBA reviewDBA;
	
	public Review(int id, String content, int star_rating)
	{
		this.id = id;
		this.content = content;
		this.star_rating = star_rating;
		this.reviewDBA = new ReviewDBA();
	}
	
	public Review()
	{
		this.id = -1;
		this.content = null;
		this.star_rating = -1;
		this.reviewDBA = new ReviewDBA();
	}
	
	public boolean saveReview()
	{
		return reviewDBA.saveReview(this);
	}
	
	public boolean updateReview()
	{
		return reviewDBA.updateReview(this);
	}
	
	public boolean deleteReview()
	{
		return reviewDBA.deleteReview(this);
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public int getStar_rating() {
		return star_rating;
	}
	
	public void setStar_rating(int star_rating) {
		this.star_rating = star_rating;
	}
	
	
}
