package dawgdrivein.entity;

import dawgdrivein.db.SeatDBA;

public class Seat {

	private int id;
	private int hallID;
	private int seat;
	private int showtimeID;
	private int ticketID;
	private SeatDBA seatDBA;
	
	public Seat(int id, int hallID, int seat, int showtimeID, int ticketID) {
		this.id = id;
		this.hallID = hallID;
		this.seat = seat;
		this.showtimeID = showtimeID;
		this.ticketID = ticketID;
		seatDBA = new SeatDBA();
	}
	
	public Seat()
	{
		this.id = -1;
		this.hallID = -1;
		this.seat = -1;
		this.showtimeID = -1;
		this.ticketID = -1;
		seatDBA = null;
	}

	public boolean saveReservedSeat()
	{
		return seatDBA.saveReservedSeat(this);
	}
	
	public boolean deleteReservedSeat()
	{
		return seatDBA.deleteReservedSeat(this);
	}
	
	public boolean isSeatAvailable()
	{
		return seatDBA.isSeatAvailable(this);
	}
	
	public int getHallID() {
		return hallID;
	}
	
	public void setHallID(int hallID) {
		this.hallID = hallID;
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
	
	public int getTicketID() {
		return ticketID;
	}
	
	public void setTicketID(int ticketID) {
		this.ticketID = ticketID;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
