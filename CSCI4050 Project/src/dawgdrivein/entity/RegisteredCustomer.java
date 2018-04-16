package dawgdrivein.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "User")
@DiscriminatorValue(value = "1")
public class RegisteredCustomer extends User {

	@Transient
	private ArrayList<OrderHistory> orderHistory;
	
	@Transient
	CreditCard cc;
	
	public RegisteredCustomer(String fn, String ln, String email, String password, int rank, int status, boolean sub_pref)
	{
		super(0, fn, ln, email, generateHash(password), rank, status, sub_pref);
		cc = new CreditCard();
	}
	
	public RegisteredCustomer(String fn, String ln, String email, String password, int rank, int status, boolean sub_pref, int cardnumber, Date exp_date, String name_on_card, int CCV, String billing_address)
	{
		super(0, fn, ln, email, generateHash(password), rank, status, sub_pref);
//		cc = new CreditCard(id, cardnumber, exp_date, name_on_card, CCV, billing_address);
	}
	
	public RegisteredCustomer()
	{
		super(0, null, null, null, null, -1, -1, false);
		cc = null;
	}
	
	public int getDatabaseId()
	{
		return userDBA.getDatabaseId(this);
	}
	
	/**
	 * The servlet is sending a RegisteredCustomer object to
	 * the UserDBA class to be stored in persistent memory.
	 * 
	 * @return success at saving user in DB
	 */
	public boolean saveRegisteredCustomer()
	{
		return userDBA.saveUser(this);
	}
	
	/**
	 * Tells DB to update user profile info
	 * 
	 * @return success at updating user entries in DB
	 */
	public void updateProfile(int uID, String fN, String lN, String email, boolean sub_pref)
	{
		userDBA.updateProfile(uID, fN, lN, email, sub_pref);
	}
	
	public boolean deleteCustomer()
	{
		return userDBA.deleteUser(this);
	}
	
	/**
	 * Queries database to see if email exists
	 * @return
	 */
	public boolean emailExists()
	{
		System.out.println("EmailExists in RegisteredCustomer");
		return userDBA.emailExists(this);
	}
	
	public boolean suspendCustomer()
	{
		return userDBA.suspendCustomer(this);
	}
	
	
	private void changeSubscriptionPreference(boolean pref)
	{
		this.sub_pref = pref;
	}
	
	private boolean bookMovie(Showtime showtime, float totalPrice, int noOfTickets, int showtimeID)
	{
//		Booking booking = new Booking(id, 1, totalPrice, noOfTickets, showtimeID);
//		return booking.saveBooking();
		return false;
	}
	
	private void checkout()
	{
		//todo
	}
	
	private void addReview(String content, int star_rating, int movieId)
	{
		Review review = new Review(1, content, star_rating, movieId);
		review.saveReview();
	}
	
	private void editProfile(String newFN, String newLN, String newEmail, String newAddress, boolean newSubPref)
	{
		this.firstName = newFN;
		this.lastName = newLN;
		this.email = newEmail;
		this.sub_pref = newSubPref;
	}
	
	private void recoverLostPassword()
	{
		
	}
	
	private void returnTickets(Booking booking)
	{
		booking.deleteBooking();
	}
	
	private List<Booking> viewOrderHistory()
	{
		OrderHistory oh = new OrderHistory(id);
		return oh.getOrderHistory(id);
	}
	
	public void verification(int userId)
	{
		userDBA.verifyUser(userId);
	}
}
