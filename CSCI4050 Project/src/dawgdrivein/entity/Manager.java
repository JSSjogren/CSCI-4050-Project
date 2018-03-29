package dawgdrivein.entity;

import dawgdrivein.db.UserDBA;

public class Manager extends User {
	
	private int rank;
	UserDBA userDBA;
	
	public Manager(int id, String email, String password, String firstName, String lastName, String phoneNumber, String address, int rank) {
		super(id, email, password, firstName, lastName, phoneNumber, address);
		this.rank = rank;
		userDBA = new UserDBA();
	}
	
	public boolean updateManager()
	{
		return userDBA.updateUser(this);
	}
	
	public boolean deleteManager()
	{
		return userDBA.deleteUser(this);
	}
	
	private void createReport()
	{
		
	}
	
}
