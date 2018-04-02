package dawgdrivein.entity;

import dawgdrivein.db.UserDBA;

public class Manager extends User {
	
	UserDBA userDBA;
	
	public Manager(int id, String email, String password, String firstName, String lastName, String phoneNumber, String address, int rank, int status) {
		super(id, email, password, firstName, lastName, phoneNumber, address, rank, status);
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
