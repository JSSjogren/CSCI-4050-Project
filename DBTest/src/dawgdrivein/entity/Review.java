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
	
	/**
	 * Saves this Review in the DB
	 * @return whether the save was successful
	 */
	public boolean saveReview()
	{
		return reviewDBA.saveReview(this);
	}
	
	/**
	 * Updates this Review in the DB
	 * @return whether the update was successful
	 */
	public boolean updateReview()
	{
		return reviewDBA.updateReview(this);
	}
	
	/**
	 * Deletes this Review from the DB
	 * @return whether the delete was completed
	 */
	public boolean deleteReview()
	{
		return reviewDBA.deleteReview(this);
	}
	
	/**
	 * Retrieves a Review object from the DB via ID
	 * @param id the ID to use as reference
	 * @return the retrieved Review object
	 */
	public Review retrieveReview(int id)
	{
		return reviewDBA.retrieveReview(id);
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
