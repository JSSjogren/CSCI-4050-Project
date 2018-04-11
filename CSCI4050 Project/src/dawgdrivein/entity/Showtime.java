package dawgdrivein.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import dawgdrivein.db.ShowtimeDBA;

@Entity
@Table (name = "Showtime")
public class Showtime {

	@Column(name = "exp_date", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date showtime;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ShowId")
	private int id;
	
	@ManyToOne(targetEntity = Movie.class)
	private int movieId;
	
	@Transient
	private ShowtimeDBA showtimeDBA;
	
	public Showtime(int id, Date showtime, int movieId) 
	{
		this.id = id;
		this.showtime = showtime;
		this.movieId = movieId;
		showtimeDBA = new ShowtimeDBA();
	}
	
	public Showtime()
	{
		this.id = -1;
		this.showtime = null;
		this.movieId = -1;
		this.showtimeDBA = new ShowtimeDBA();
	}

	public boolean saveShowtime() 
	{
		return showtimeDBA.saveShowtime(this);
	}
	
	public boolean updateShowtime()
	{
		return showtimeDBA.updateShowtime(this);
	}
	
	public boolean deleteShowtime()
	{
		return showtimeDBA.deleteShowtime(this);
	}

	public Date getShowtime() {
		return showtime;
	}

	public void setShowtime(Date showtime) {
		this.showtime = showtime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	
}
