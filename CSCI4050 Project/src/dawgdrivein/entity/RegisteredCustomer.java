package dawgdrivein.entity;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dawgdrivein.db.UserDBA;

public class RegisteredCustomer extends User {

	private boolean subscription_pref;
	private ArrayList<OrderHistory> orderHistory;
	private UserDBA userDBA;
	
	CreditCard cc;
	
	public RegisteredCustomer(int id, String fn, String ln, String email, String password, String phoneNumber, String address, int rank, int status, boolean sub_pref)
	{
		super(id, email, generateHash(password), fn, ln, phoneNumber, address, rank, status);
		this.subscription_pref = sub_pref;
		userDBA = new UserDBA();
		cc = new CreditCard();
	}
	
	public RegisteredCustomer(int id, String fn, String ln, String email, String password, String phoneNumber, String address, int rank, int status, boolean sub_pref, int cardnumber, Date exp_date, String name_on_card, int CCV, String billing_address)
	{
		super(id, email, generateHash(password), fn, ln, phoneNumber, address, rank, status);
		this.subscription_pref = sub_pref;
		userDBA = new UserDBA();
		cc = new CreditCard(id, cardnumber, exp_date, name_on_card, CCV, billing_address);
	}
	
	/**
	 * The servlet is sending a RegisteredCustomer object to
	 * the UserDBA class to be stored in persistent memory.
	 * 
	 * @param user the object passed into the DBAccess class
	 */
	public boolean saveRegisteredCustomer(RegisteredCustomer user)
	{
		return userDBA.saveUser(user);
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
	private boolean validate(String email, String password)
	{
		return userDBA.validateUser(email, generateHash(password));
	}
	
	/**
	 * Turns the user's password into a hashValue
	 * 
	 * @param input the user's password
	 * @return the hashed version of the user's password
	 */
	private static String generateHash(String input) {
		StringBuilder hash = new StringBuilder();

		try {
			MessageDigest sha = MessageDigest.getInstance("SHA-1");
			byte[] hashedBytes = sha.digest(input.getBytes());
			char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
					'a', 'b', 'c', 'd', 'e', 'f' };
			for (int idx = 0; idx < hashedBytes.length; ++idx) {
				byte b = hashedBytes[idx];
				hash.append(digits[(b & 0xf0) >> 4]);
				hash.append(digits[b & 0x0f]);
			}
		} catch (NoSuchAlgorithmException e) {
			// handle error here.
		}

		return hash.toString();
	}
	
	private void changeSubscriptionPreference(boolean pref)
	{
		this.subscription_pref = pref;
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
		this.subscription_pref = newSubPref;
		this.address = newAddress;
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
	
	private void verification(String enteredNo)
	{
		//If the user is inactive
		if (status == 0 && enteredNo.equals("1234"))
			status = 1;
	}
}
