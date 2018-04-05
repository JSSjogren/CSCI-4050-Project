package dawgdrivein.entity;

import java.util.List;

import dawgdrivein.db.UserDBA;

public class Employee extends User {
		
	private UserDBA userDBA;
	public Employee(int id, String email, String password, String firstName, String lastName, String phoneNumber, String address, int rank, int status) {
		super(id, email, password, firstName, lastName, phoneNumber, address, rank, status);
		userDBA = new UserDBA();
	}
	
	/**
	 * Call the userDBA updateUser method 
	 * @return whether updateUser was successful
	 */
	public boolean updateEmployee()
	{
		return userDBA.updateUser(this);
	}
	
	/**
	 * Call the userDBA deleteUser method
	 * @return whether deleteUser was successful
	 */
	public boolean deleteEmployee()
	{
		return userDBA.deleteUser(this);
	}
	
	/**
	 * Call the userDBA saveUser method
	 * @return whether saveUser was successful
	 */
	public boolean saveEmployee()
	{
		return userDBA.saveUser(this);
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
