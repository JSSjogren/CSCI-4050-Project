package dawgdrivein.entity;

import java.util.List;

import dawgdrivein.db.UserDBA;

public class Manager extends User {
	
	UserDBA userDBA;
	
	public Manager(int id, String email, String password, String firstName, String lastName, String phoneNumber, String address, int rank, int status) {
		super(id, email, password, firstName, lastName, phoneNumber, address, rank, status);
		userDBA = new UserDBA();
	}
	
	/**
	 * Use the UserDBA to save a manager in the DB
	 * @return whether the save was successful
	 */
	public boolean saveManager()
	{
		return userDBA.saveUser(this);
	}
	
	/**
	 * Use the UserDBA to update a manager in the DB
	 * @return whether the update was successful
	 */
	public boolean updateManager()
	{
		return userDBA.updateUser(this);
	}
	
	/**
	 * Use the UserDBA to delete a manager from the DB
	 * @return whether the delete was completed
	 */
	public boolean deleteManager()
	{
		return userDBA.deleteUser(this);
	}
	
	/**
	 * Retrieves complete OrderHistory of the theater
	 * @return a list of bookings for the theater
	 */
	private List<Booking> createReport()
	{
		OrderHistory oh = new OrderHistory();
		return oh.getOrderHistory();
	}
	
}
