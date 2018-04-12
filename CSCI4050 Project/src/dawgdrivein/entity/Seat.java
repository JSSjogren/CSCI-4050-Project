package dawgdrivein.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import dawgdrivein.db.SeatDBA;

@Entity
@Table (name = "Seat")
public class Seat {

	@Column(name = "Seat")
	private int seat;
	
	@Column(name = "ShowId")
	private int showtimeID;
	
	@Transient
	private SeatDBA seatDBA;
	
	public Seat(int seat, int showtimeID) {
		this.seat = seat;
		this.showtimeID = showtimeID;
		seatDBA = new SeatDBA();
	}
	
	public Seat()
	{
		this.seat = -1;
		this.showtimeID = -1;
		seatDBA = new SeatDBA();
	}

	public boolean saveReservedSeat()
	{
		return seatDBA.saveReservedSeat(this);
	}
	
	public boolean deleteReservedSeat()
	{
		return seatDBA.deleteReservedSeat(this);
	}

	public int getSeat() {
		return seat;
	}

	public void setSeat(int seat) {
		this.seat = seat;
	}

	public int getShowtimeID() {
		return showtimeID;
	}

	public void setShowtimeID(int showtimeID) {
		this.showtimeID = showtimeID;
	}
	
}
