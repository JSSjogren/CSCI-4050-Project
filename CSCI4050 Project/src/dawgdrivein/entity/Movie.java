package dawgdrivein.entity;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import dawgdrivein.db.MovieDBA;

@Entity
@Table(name = "Movie")
public class Movie {

	//Should allow us to auto-increment the movieId's
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MovieId")
	private int id;
	
	@Column(name = "Title")
	private String title;
	
	@Column(name = "Genre")
	private String genre;

	@Column(name = "Cast")
	private String cast;
	
	@Column(name = "Director")
	private String director;
	
	@Column(name = "Producer")
	private String producer;
	
	@Column(name = "Description")
	private String description;
	
	@Column(name = "TrailerPicture")
	private String trailer_picture;
	
	@Column(name = "TrailerVideo")
	private String trailer_video;
	
	@Column(name = "MpaaRating")
	private String MPAA_rating;
	
	@Column(name = "releaseDate", columnDefinition="DATETIME")
	private Date releaseDate;
	
	@Column(name = "Expiration", columnDefinition="DATETIME")
	private Date expiration;
	
	@Transient
	MovieDBA movieDBA;
	
	public Movie(int id, String title, String genre, String cast, String director, String producer, String description, String trailer_picture, String trailer_video, String MPAA_rating, Date releaseDate, Date expiration)
	{
		this.id = id;
		this.title = title;
		this.genre = genre;
		this.cast = cast;
		this.director = director;
		this.producer = producer;
		this.description = description;
		this.trailer_picture = trailer_picture;
		this.trailer_video = trailer_video;
		this.MPAA_rating = MPAA_rating;
		this.releaseDate = releaseDate;
		this.expiration = expiration;
		
		movieDBA = new MovieDBA();
	}
	
	public Movie()
	{
		this.id = -1;
		this.title = null;
		this.genre = null;
		this.cast = null;
		this.director = null;
		this.producer = null;
		this.description = null;
		this.trailer_picture = null;
		this.trailer_video = null;
		this.MPAA_rating = null;
		this.releaseDate = null;
		this.expiration = null;
		
		this.movieDBA = new MovieDBA();
	}
	
	/**
	 * Updates this movie object in the DB
	 * @return whether the update was successful
	 */
	public boolean updateMovie()
	{
		return movieDBA.updateMovie(this);
	}
	
	/**
	 * Saves this movie object in the DB
	 * @return whether the save was successful
	 */
	public boolean saveMovie()
	{
		return movieDBA.saveMovie(this);
	}
	
	/**
	 * Deletes this movie object from the DB
	 * @return whether the delete was completed
	 */
	public boolean deleteMovie()
	{
		return movieDBA.deleteMovie(this);
	}
	
	/**
	 * Retrieves a movie object based on its ID
	 * @param id the ID to search for
	 * @return the retrieved Movie object
	 */
	public Movie retrieveMovie(int id)
	{
		return movieDBA.retrieveMovie(id);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getCast() {
		return cast;
	}

	public void setCast(String cast) {
		this.cast = cast;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTrailer_picture() {
		return trailer_picture;
	}

	public void setTrailer_picture(String trailer_picture) {
		this.trailer_picture = trailer_picture;
	}

	public String getTrailer_video() {
		return trailer_video;
	}

	public void setTrailer_video(String trailer_video) {
		this.trailer_video = trailer_video;
	}

	public String getMPAA_rating() {
		return MPAA_rating;
	}

	public void setMPAA_rating(String mPAA_rating) {
		MPAA_rating = mPAA_rating;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public Date getExpiration() {
		return expiration;
	}

	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getMovieIdByName(String name)
	{
		return movieDBA.getMovieIdByName(name);
	}

}
