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
	
	@Column(name = "SeatId")
	private int seatID;
	
	@Transient
	TicketDBA ticketDBA;
	
	public Ticket(int bookingID, int ticketNo, int seatID) {
		this.bookingID = bookingID;
		this.ticketNo = ticketNo;
		this.seatID = seatID;
		
		ticketDBA = new TicketDBA();
	}
	
	public Ticket()
	{
		this.bookingID = -1;
		this.ticketNo = -1;
		this.seatID = -1;
		
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

	public int getSeatID() {
		return seatID;
	}

	public void setSeatID(int seatID) {
		this.seatID = seatID;
	}

}
