package dawgdrivein.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import dawgdrivein.db.BookingDBA;

@Entity
@Table (name = "Booking")
public class Booking {

	//Should allow us to auto-increment the bookingNo's
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BookingId")
	private int bookingNo;
	
	@Column(name = "UserId")
	private int userID;
	
	@Column(name = "TotalPrice")
	private float totalPrice;
	
	@Column(name = "NoOfTickets")
	private int noOfTickets;
	
    @Column(name = "ShowtimeId")
	private int showtimeID;
    
    @Column(name = "PromoId")
    private int promoId;
    
    @Column(name = "MovieId")
    private int movieId;
	
	@Transient
	private BookingDBA bookingDBA;

	public Booking(int userID, int bookingNo, float totalPrice, int noOfTickets, int showtimeID, int promoId, int movieId)
	{
		this.userID = userID;
		this.bookingNo = bookingNo;
		this.totalPrice = totalPrice;
		this.noOfTickets = noOfTickets;
		this.showtimeID = showtimeID;
		this.promoId = promoId;
		this.movieId = movieId;

		bookingDBA = new BookingDBA();
	}

	public Booking()
	{
		this.userID = -1;
		this.bookingNo = -1;
		this.totalPrice = -1.0f;
		this.noOfTickets = -1;
		this.showtimeID = -1;
		this.promoId = -1;
		this.movieId = -1;
		bookingDBA = new BookingDBA();
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

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getNoOfTickets() {
		return noOfTickets;
	}

	public void setNoOfTickets(int noOfTickets) {
		this.noOfTickets = noOfTickets;
	}

	public int getShowtimeID() {
		return showtimeID;
	}

	public void setShowtimeID(int showtimeID) {
		this.showtimeID = showtimeID;
	}

	public int getPromoId() {
		return promoId;
	}

	public void setPromoId(int promoId) {
		this.promoId = promoId;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

}
