package dawgdrivein.entity;

import java.util.List;

import dawgdrivein.db.OrderHistoryDBA;

public class OrderHistory {

	private int userID;
	private OrderHistoryDBA ohDBA;
	
	/**
	 * Constructor for OrderHistory when querying 1 user's orderhistory
	 * @param userID the ID of the user to find the orderhistory of
	 */
	public OrderHistory(int userID)
	{
		this.userID = userID;
		ohDBA = new OrderHistoryDBA();
	}

	/**
	 * Constructor for OrderHistory when querying all bookings
	 */
	public OrderHistory()
	{
		this.userID = -1;
		ohDBA = new OrderHistoryDBA();
	}
	
	/**
	 * Returns list of bookings that contain userID
	 * @param userID the user ID to be searched for
	 * @return list of bookings
	 */
	public List<Booking> getOrderHistory(int userID)
	{
		return ohDBA.getOrderHistory(userID);
	}
	
	/**
	 * Returns list of bookings for all movies/users
	 * @return list of all bookings
	 */
	public List<Booking> getOrderHistory()
	{
		return ohDBA.getOrderHistory();
	}
	
	public int getUserID() {
		return userID;
	}
	
	public void setUserID(int userID) {
		this.userID = userID;
	}
}
