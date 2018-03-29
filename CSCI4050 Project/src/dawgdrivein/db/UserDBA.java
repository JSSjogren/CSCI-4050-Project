package dawgdrivein.db;

import dawgdrivein.entity.RegisteredCustomer;
import dawgdrivein.entity.User;

public class UserDBA {
	
	
	public boolean saveUser(RegisteredCustomer user)
	{
	
		return true;
	}
	
	public boolean validateUser(String email, String password)
	{
		
		return true;
	}
	
	public boolean updateUser(User user)
	{
		
		return true;
	}
	
	public boolean deleteUser(User user)
	{
		return true;
	}
	
	public boolean suspendCustomer(RegisteredCustomer customer)
	{
		return true;
	}
}
