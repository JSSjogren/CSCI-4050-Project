package dawgdrivein.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import dawgdrivein.db.TicketDBA;

@Entity
@Table(name = "Ticket")
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TicketId")
	private int ticketNo;
	
	@Column(name = "BookingId")
	private int bookingID;
	
	@Column(name = "ShowtimeId")
	private int showtimeID;
	
	@Column(name = "MovieId")
	private int movieID;
	
	@Column(name = "SeatId")
	private int seatID;
	
	@Column(name = "TicketPrice")
	private int ticketPrice;
	
	@Transient
	TicketDBA ticketDBA;
	
	public Ticket(int bookingID, int ticketNo, int showtimeID, int movieID, int seatID, int ticketPrice) {
		this.bookingID = bookingID;
		this.ticketNo = ticketNo;
		this.showtimeID = showtimeID;
		this.movieID = movieID;
		this.seatID = seatID;
		this.ticketPrice = ticketPrice;
		
		ticketDBA = new TicketDBA();
	}
	
	public Ticket()
	{
		this.bookingID = -1;
		this.ticketNo = -1;
		this.showtimeID = -1;
		this.movieID = -1;
		this.seatID = -1;
		this.ticketPrice = -1;
		
		ticketDBA = new TicketDBA();
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

	public int getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(int ticketPrice) {
		this.ticketPrice = ticketPrice;
	}
	
}
