package dawgdrivein.entity;

import java.sql.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "User")
@DiscriminatorValue(value = "4")
public class SystemAdministrator extends User {


	public SystemAdministrator(String firstName, String lastName, String email, String password, int rank, int status) {
		super(0, firstName, lastName, email, generateHash(password), rank, status, false);
	}

	public SystemAdministrator()
	{
		super(0, null, null, null, null, -1, -1, false);
	}

	private boolean enterNewMovie(int id, String title, String genre, String cast, String director, String producer, String description, String trailer_picture, String trailer_video, String MPAA_rating, Date releaseDate, Date expiration)
	{
		Movie movie = new Movie(id, title, genre, cast, director, producer, description, trailer_picture, trailer_video, MPAA_rating, releaseDate, expiration);
		return movie.saveMovie();
	}

	private boolean updateMovie(int newId, String newTitle, String newGenre, String newCast, String newDirector, String newProducer, String newDescription, String newTrailer_picture, String newTrailer_video, String newMPAA_rating, Date newReleaseDate, Date newExpiration)
	{
		Movie movie = new Movie(newId, newTitle, newGenre, newCast, newDirector, newProducer, newDescription, newTrailer_picture, newTrailer_video, newMPAA_rating, newReleaseDate, newExpiration);
		return movie.updateMovie();
	}

	private boolean deleteMovie(int id, String title, String genre, String cast, String director, String producer, String description, String trailer_picture, String trailer_video, String MPAA_rating, Date releaseDate, Date expiration)
	{
		Movie movie = new Movie(id, title, genre, cast, director, producer, description, trailer_picture, trailer_video, MPAA_rating, releaseDate, expiration);
		return movie.deleteMovie();
	}

	private void addNewEmployee()
	{

	}

	private boolean updateMemberInfo(String newEmail, String newPassword, String newFirstName, String newLastName, int newRank, int newStatus, boolean newSubscription_pref)
	{
		//Is the member being updated a RegisteredCustomer?
		if (rank == 4)
		{
			RegisteredCustomer customer = new RegisteredCustomer(newFirstName, newLastName, newEmail, newPassword, newRank, newStatus, newSubscription_pref);
//			return customer.updateCustomer();
		}
		//Is the member being updated an Employee?
		else if (rank == 3)
		{
			Employee employee = new Employee(newFirstName, newLastName, newEmail, newPassword, newRank, newStatus);
			return employee.updateEmployee();
		}
		//Is the member being updated a Manager?
		else if (rank == 2)
		{
			Manager manager = new Manager(newFirstName, newLastName, newEmail, newPassword, newRank, newStatus);
			return manager.updateManager();
		}
		//Is the member being updated a User?
		else if (rank == 1)
			return false;

		return false;
	}

	private boolean deleteMemberInfo(int id, String email, String password, String firstName, String lastName, int rank, int status, boolean subscription_pref)
	{
		//Is the member being deleted a RegisteredCustomer?
		if (rank == 4)
		{
			RegisteredCustomer customer = new RegisteredCustomer(firstName, lastName, email, password, rank, status, subscription_pref);
			return customer.deleteCustomer();
		}
		//Is the member being deleted an Employee?
		else if (rank == 3)
		{
			Employee employee = new Employee(firstName, lastName, email, password, rank, status);
			return employee.deleteEmployee();
		}
		//Is the member being deleted a Manager?
		else if (rank == 2)
		{
			Manager manager = new Manager(firstName, lastName, email, password, rank, status);
			return manager.deleteManager();
		}
		//Is the member being deleted a User?
		else if (rank == 1)
			return false;

		return false;
	}

	private boolean suspendMemberAcct(String email, String password, String firstName, String lastName, int rank, int status, boolean subscription_pref)
	{
		if (rank == 4)
		{
			RegisteredCustomer customer = new RegisteredCustomer(firstName, lastName, email, password, rank, status, subscription_pref);
			return customer.suspendCustomer();
		}
		
		return false;
	}

	private boolean addPromotion(int id, Date exp_date, String code, int percent_discount)
	{
		Promotion promo = new Promotion(id, exp_date, code, percent_discount);
		return promo.savePromo();
	}
	
	private boolean removePromotion(int id, Date exp_date, String code, int percent_discount)
	{
		Promotion promo = new Promotion(id, exp_date, code, percent_discount);
		return promo.deletePromo();
	}
}

