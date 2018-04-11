package dawgdrivein.entity;

import dawgdrivein.db.TicketDBA;

public class Ticket {

	private int bookingID;
	private int ticketNo;
	private int showtimeID;
	private int movieID;
	private int seatID;
	
	TicketDBA ticketDBA;
	
	public Ticket(int bookingID, int ticketNo, int showtimeID, int movieID, int seatID) {
		this.bookingID = bookingID;
		this.ticketNo = ticketNo;
		this.showtimeID = showtimeID;
		this.movieID = movieID;
		this.seatID = seatID;
		ticketDBA = new TicketDBA();
	}
	
	public Ticket()
	{
		this.bookingID = -1;
		this.ticketNo = -1;
		this.showtimeID = -1;
		this.movieID = -1;
		this.seatID = -1;
		ticketDBA = null;
	}
	
	public boolean saveTicket()
	{
		return ticketDBA.saveTicket(this);
	}
	
	public boolean updateTicket()
	{
		return ticketDBA.updateTicket(this);
	}
	
	public boolean deleteTicket()
	{
		return ticketDBA.deleteTicket(this);
	}
	
	public int getBookingID() {
		return bookingID;
	}
	
	public void setBookingID(int bookingID) {
		this.bookingID = bookingID;
	}
	
	public int getTicketNo() {
		return ticketNo;
	}
	
	public void setTicketNo(int ticketNo) {
		this.ticketNo = ticketNo;
	}
	
	public int getShowtimeID() {
		return showtimeID;
	}
	
	public void setShowtimeID(int showtimeID) {
		this.showtimeID = showtimeID;
	}
	
	public int getMovieID() {
		return movieID;
	}
	public void setMovieID(int movieID) {
		this.movieID = movieID;
	}
	
	public int getSeatID() {
		return seatID;
	}
	
	public void setSeatID(int seatID) {
		this.seatID = seatID;
	}
	
}
