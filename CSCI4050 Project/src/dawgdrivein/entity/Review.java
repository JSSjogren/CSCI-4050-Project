package dawgdrivein.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import dawgdrivein.db.ReviewDBA;

@Entity
@Table(name = "Review")
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ReviewId")
	private int id;
	
	@Column(name = "Content")
	private String content;
	
	@Column(name = "StarRating")
	private int star_rating;
	
	@Column(name = "MovieId")
	private int movieId;
	
	@Transient
	private ReviewDBA reviewDBA;
	
	public Review(int id, String content, int star_rating, int movieId)
	{
		this.id = id;
		this.content = content;
		this.star_rating = star_rating;
		this.movieId = movieId;
		this.reviewDBA = new ReviewDBA();
	}
	
	public Review()
	{
		this.id = -1;
		this.content = null;
		this.star_rating = -1;
		this.movieId = -1;
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

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	
}
