package dawgdrivein.entity;

import java.util.Date;

public class Showtime {

	private int id;
	private Date showtime;
	
	public Showtime(int id, Date showtime) 
	{
		this.id = id;
		this.showtime = showtime;
	}

	public boolean addShowtime() 
	{
		//Todo: Add ShowtimeDBA and add Showtime to DB
		return true;
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
}
