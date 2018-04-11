package dawgdrivein.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import dawgdrivein.db.BookingDBA;

public class Booking {

	//Should allow us to auto-increment the bookingNo's
	@Id
	@GenericGenerator(name="gen",strategy="increment")
	@GeneratedValue(generator="gen")
	@Column(name = "bookingNo", unique = true, nullable = false, precision = 15, scale = 0)
	private int bookingNo;
	
	private int userID;
	private float totalPrice;
	private int adult_tickets;
	private int child_tickets;
	private int senior_tickets;
	private int showtimeID;
	private BookingDBA bookingDBA;

	public Booking(int userID, int bookingNo, float totalPrice, int adult_tickets, int child_tickets, int senior_tickets, int showtimeID)
	{
		this.userID = userID;
		this.bookingNo = bookingNo;
		this.totalPrice = totalPrice;
		this.adult_tickets = adult_tickets;
		this.child_tickets = child_tickets;
		this.senior_tickets = senior_tickets;
		this.showtimeID = showtimeID;

		bookingDBA = new BookingDBA();
	}

	public Booking()
	{
		this.userID = -1;
		this.bookingNo = -1;
		this.totalPrice = -1.0f;
		this.adult_tickets = -1;
		this.child_tickets = -1;
		this.senior_tickets = -1;
		this.showtimeID = -1;
		bookingDBA = null;
	}

	public boolean saveBooking()
	{
		return bookingDBA.saveBooking(this);
	}

	public boolean deleteBooking()
	{
		return bookingDBA.deleteBooking(this);
	}

	public boolean updateBooking()
	{
		return bookingDBA.updateBooking(this);
	}

	/**
	 * Retrieves a Booking from the database using its ID
	 * @param id the booking ID that hibernate will use to retrieve a Booking object
	 * @return the matching Booking
	 */
	public Booking retrieveBooking(int id)
	{
		return bookingDBA.retrieveBooking(id);
	}
	
	private float applyPromo(Promotion promo)
	{
		this.totalPrice = this.totalPrice - (this.totalPrice * promo.getPercent_discount());
		return (float) this.totalPrice;
	}

	public int getBookingNo() {
		return bookingNo;
	}

	public void setBookingNo(int bookingNo) {
		this.bookingNo = bookingNo;
	}

	public float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getAdult_tickets() {
		return adult_tickets;
	}

	public void setAdult_tickets(int adult_tickets) {
		this.adult_tickets = adult_tickets;
	}

	public int getChild_tickets() {
		return child_tickets;
	}

	public void setChild_tickets(int child_tickets) {
		this.child_tickets = child_tickets;
	}

	public int getSenior_tickets() {
		return senior_tickets;
	}

	public void setSenior_tickets(int senior_tickets) {
		this.senior_tickets = senior_tickets;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

}
