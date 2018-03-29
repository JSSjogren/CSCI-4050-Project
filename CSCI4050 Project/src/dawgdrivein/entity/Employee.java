package dawgdrivein.entity;

public class Employee extends User {
	
	private int rank;
	
	public Employee(int id, String email, String password, String firstName, String lastName, String phoneNumber, String address, int rank) {
		super(id, email, password, firstName, lastName, phoneNumber, address);
		this.rank = rank;
	}
	
	private void createReport()
	{
		
	}
	
}
