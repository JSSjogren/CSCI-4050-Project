package dawgdrivein.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import dawgdrivein.db.ShowtimeDBA;

public class Showtime {

	@Column(name = "exp_date", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date showtime;
	
	private int id;
	private int available_seats;
	private ShowtimeDBA showtimeDBA;
	
	public Showtime(int id, Date showtime, int available_seats) 
	{
		this.id = id;
		this.showtime = showtime;
		this.available_seats = available_seats;
		showtimeDBA = new ShowtimeDBA();
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
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getShowtime() {
		return showtime;
	}

	public void setShowtime(Date showtime) {
		this.showtime = showtime;
	}

	public int getAvailable_seats() {
		return available_seats;
	}

	public void setAvailable_seats(int available_seats) {
		this.available_seats = available_seats;
	}
}
