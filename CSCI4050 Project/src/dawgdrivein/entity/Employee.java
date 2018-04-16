package dawgdrivein.entity;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import dawgdrivein.db.UserDBA;

@Entity
@Table(name = "User")
@DiscriminatorValue(value = "2")
public class Employee extends User {
		
	@Transient
	private UserDBA userDBA;
	
	public Employee(String email, String password, String firstName, String lastName, int rank, int status) {
		super(0, firstName, lastName, email, generateHash(password), rank, status, false);
		userDBA = new UserDBA();
	}
	
	public Employee()
	{
		super(0, null, null, null, null, -1, -1, false);
		userDBA = null;
	}
	
	/**
	 * Call the userDBA updateUser method 
	 * @return whether updateUser was successful
	 */
	public boolean updateEmployee()
	{
		return true;
//		return userDBA.updateUser(this);
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
