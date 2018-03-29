package dawgdrivein.entity;

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
	
	MovieDBA movieDBA;
	
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
	
	public boolean updateMovie()
	{
		return movieDBA.updateMovie(this);
	}
	
	public boolean saveMovie()
	{
		return movieDBA.enterNewMovie(this);
	}
	
	public boolean deleteMovie()
	{
		return movieDBA.deleteMovie(this);
	}
}
