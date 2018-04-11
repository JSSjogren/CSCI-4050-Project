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
	
	/**
	 * Remove one available seat from hall and update in DB
	 * @return whether update happened
	 */
	public boolean removeOneAvailable()
	{
		if (available_capacity >= 1)
		{
			this.available_capacity -= 1;
			updateHall();
			return true;
		}
		else
			return false;
	}
	
	/**
	 * Add one available seat to hall and update in DB
	 * @return whether update happened
	 */
	public boolean addOneAvailable() 
	{
		if (available_capacity <= 29)
		{
			this.available_capacity += 1;
			updateHall();
			return true;
		}
		else
			return false;
		
	}
	
	/**
	 * Save Hall in database
	 * @return whether save was completed
	 */
	public boolean createHall()
	{
		return hallDBA.createHall(this);
	}
	
	/**
	 * Update Hall object in database
	 * @return whether update was successful
	 */
	public boolean updateHall()
	{
		return hallDBA.updateHall(this);
	}
	
	/**
	 * Delete Hall object in database
	 * @return whether delete was successful
	 */
	public boolean deleteHall()
	{
		return hallDBA.deleteHall(this);
	}
	
	/**
	 * Retrieve this specific Hall object from DB
	 * @param id the ID to search for in the DB
	 * @return the retrieved Hall object
	 */
	public Hall retrieveHall(int id)
	{
		return hallDBA.retrieveHall(id);
	}
}
