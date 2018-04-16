package dawgdrivein.entity;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import dawgdrivein.db.UserDBA;

@Entity
@Table(name = "User")
@DiscriminatorValue(value = "3")
public class Manager extends User {
	
	@Transient
	UserDBA userDBA;
	
	public Manager(String email, String password, String firstName, String lastName, int rank, int status) {
		super(0, firstName, lastName, email, generateHash(password), rank, status, false);
		userDBA = new UserDBA();
	}
	
	public Manager()
	{
		super(0, null, null, null, null, -1, -1, false);
		userDBA = null;
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
