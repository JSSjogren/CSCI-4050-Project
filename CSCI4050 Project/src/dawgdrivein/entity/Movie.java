package dawgdrivein.entity;

import java.util.ArrayList;
import java.util.Date;

import dawgdrivein.db.MovieDBA;

public class Movie {

	private int id;
	private String genre;
	private String cast;
	private String director;
	private String producer;
	private String description;
	private String trailer_picture;
	private String trailer_video;
	private String MPAA_rating;
	private int status;
	ArrayList<Showtime> showtimes;
	
	MovieDBA movieDBA;
	
	//Movie must have at least 1 showtime
	public Movie(int id, String genre, String cast, String director, String producer, String description, String trailer_picture, String trailer_video, String MPAA_rating, int status, Date showtime)
	{
		this.id = id;
		this.genre = genre;
		this.cast = cast;
		this.director = director;
		this.producer = producer;
		this.description = description;
		this.trailer_picture = trailer_picture;
		this.trailer_video = trailer_video;
		this.MPAA_rating = MPAA_rating;
		this.status = status;
		movieDBA = new MovieDBA();
		
		Showtime firstShowtime = new Showtime(id, showtime, 30);
		firstShowtime.saveShowtime();
	}
	
	public Movie(int id, String genre, String cast, String director, String producer, String description, String trailer_picture, String trailer_video, String MPAA_rating, int status)
	{
		this.id = id;
		this.genre = genre;
		this.cast = cast;
		this.director = director;
		this.producer = producer;
		this.description = description;
		this.trailer_picture = trailer_picture;
		this.trailer_video = trailer_video;
		this.MPAA_rating = MPAA_rating;
		this.status = status;
		movieDBA = new MovieDBA();
	}
	
	public Movie()
	{
		this.id = -1;
		this.genre = null;
		this.cast = null;
		this.director = null;
		this.producer = null;
		this.description = null;
		this.trailer_picture = null;
		this.trailer_video = null;
		this.MPAA_rating = null;
		this.status = -1;
		this.movieDBA = null;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
