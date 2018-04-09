package dawgdrivein.entity;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import dawgdrivein.db.UserDBA;

@Entity
@Table(name = "User")
public class RegisteredCustomer extends User {

	@Transient
	private ArrayList<OrderHistory> orderHistory;
	@Transient
	private UserDBA userDBA;
	
	@Transient
	CreditCard cc;
	
	public RegisteredCustomer(String fn, String ln, String email, String password, int rank, int status, boolean sub_pref)
	{
		super(0, fn, ln, email, generateHash(password), rank, status, sub_pref);
		userDBA = new UserDBA();
		cc = new CreditCard();
	}
	
	public RegisteredCustomer(String fn, String ln, String email, String password, int rank, int status, boolean sub_pref, int cardnumber, Date exp_date, String name_on_card, int CCV, String billing_address)
	{
		super(0, fn, ln, email, generateHash(password), rank, status, sub_pref);
		userDBA = new UserDBA();
		cc = new CreditCard(id, cardnumber, exp_date, name_on_card, CCV, billing_address);
	}
	
	public RegisteredCustomer()
	{
		super(0, null, null, null, null, -1, -1, false);
		userDBA = new UserDBA();
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
	 * Tells DB to update user info
	 * 
	 * @return success at updating user entries in DB
	 */
	public boolean updateCustomer()
	{
		return userDBA.updateUser(this);
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
	
	/**
	 * Checks to see if passed in user information
	 * can be validated for login.
	 * 
	 * @param email the user's primary key
	 * @param password the user's password
	 * @return validation success/failure
	 */
	public RegisteredCustomer validate(String email, String password)
	{
		return userDBA.validateUser(email, generateHash(password));
	}
	
	private void changeSubscriptionPreference(boolean pref)
	{
		this.sub_pref = pref;
	}
	
	private boolean bookMovie(Showtime showtime, float totalPrice, int adult_tickets, int child_tickets, int senior_tickets, int showtimeID)
	{
		Booking booking = new Booking(id, 1, totalPrice, adult_tickets, child_tickets, senior_tickets, showtimeID);
		return booking.saveBooking();
	}
	
	private void checkout()
	{
		//todo
	}
	
	private void addReview(String content, int star_rating)
	{
		Review review = new Review(1, content, star_rating);
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
