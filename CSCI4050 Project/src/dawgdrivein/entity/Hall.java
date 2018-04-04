package dawgdrivein.entity;

import dawgdrivein.db.HallDBA;

public class Hall {
	private int id;
	private int available_capacity;
	private HallDBA hallDBA;
	
	public Hall(int id, int available_capacity)
	{
		this.id = id;
		this.available_capacity = available_capacity;
		hallDBA = new HallDBA();
	}
	
	public boolean removeOneAvailable()
	{
		if (available_capacity >= 1)
		{
			this.available_capacity -= 1;
			return true;
		}
		else
			return false;
	}
	
	public boolean addOneAvailable() 
	{
		if (available_capacity <= 29)
		{
			this.available_capacity += 1;
			return true;
		}
		else
			return false;
	}
	
	public boolean createHall()
	{
		return hallDBA.createHall(this);
	}
	
	public boolean updateHall()
	{
		return hallDBA.updateHall(this);
	}
	
	public boolean deleteHall()
	{
		return hallDBA.deleteHall(this);
	}
	
	public Hall retrieveHall(int id)
	{
		return hallDBA.retrieveHall(id);
	}
}
