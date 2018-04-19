package dawgdrivein.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import dawgdrivein.db.SeatDBA;

@Entity
@Table (name = "Seat")
public class Seat {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Seat")
	private int seat;
	
	@Column(name = "ShowId")
	private int showtimeID;
	
	@Column(name = "Number")
	private int number;
	
	@Transient
	private SeatDBA seatDBA;
	
	public Seat(int seat, int showtimeID, int number) {
		this.seat = seat;
		this.showtimeID = showtimeID;
		this.number = number;
		seatDBA = new SeatDBA();
	}
	
	public Seat()
	{
		this.seat = -1;
		this.showtimeID = -1;
		this.number = -1;
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

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

}
