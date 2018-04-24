package dawgdrivein.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import dawgdrivein.entity.Employee;
import dawgdrivein.entity.Manager;
import dawgdrivein.entity.RegisteredCustomer;
import dawgdrivein.entity.SystemAdministrator;
import dawgdrivein.entity.User;

public class UserDBA {

	public boolean emailExists(RegisteredCustomer user)
	{
		System.out.println("Email exists method");
		Connection connect = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			//Drop on ground
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			connect = DriverManager.getConnection("jdbc:mysql://69.89.31.237:3306/ristiod8_dawgcinema?user=ristiod8_dcuser&password=cinemadb&useSSL=false");

			// Statements allow to issue SQL queries to the database
			statement = connect.createStatement();
			// Result set get the result of the SQL query
			resultSet = statement.executeQuery("SELECT * FROM User WHERE email = '" + user.getEmail() + "';");

			//If ResultSet is empty the email doesn't exist, otherwise it does.
			if (!resultSet.next())
			{
				System.out.println("Email does not exist");
				//Close connection
				connect.close();
				return false;
			}
			else
			{
				System.out.println("Email exists");
				//Close connection
				connect.close();
				return true;
			}

		} catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	public int getDatabaseId(User user)
	{

		return -1;
	}


	public boolean saveUser(User user)
	{
		SessionFactory sessionFactory = null;
		if (user.getRank() == 1)
			sessionFactory = new Configuration().configure().addAnnotatedClass(RegisteredCustomer.class).buildSessionFactory();
		else if (user.getRank() == 2)
			sessionFactory = new Configuration().configure().addAnnotatedClass(Employee.class).buildSessionFactory();
		else if (user.getRank() == 3)
			sessionFactory = new Configuration().configure().addAnnotatedClass(Manager.class).buildSessionFactory();
		else if (user.getRank() == 4)
			sessionFactory = new Configuration().configure().addAnnotatedClass(SystemAdministrator.class).buildSessionFactory();

		Session session = sessionFactory.openSession();

		try {
			// Starting Transaction
			Transaction transaction = session.beginTransaction();
			session.save(user);
			session.flush();
			transaction.commit();
			System.out.println("\n\n Details Added \n");
			return true;

		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			System.out.println("error");
			return false;
		}
		finally
		{
			sessionFactory.close();
		}
	}

	public User validateUser(String email, String password)
	{
		Connection connect = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			//Drop on ground
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			connect = DriverManager.getConnection("jdbc:mysql://69.89.31.237:3306/ristiod8_dawgcinema?user=ristiod8_dcuser&password=cinemadb&useSSL=false");

			// Statements allow to issue SQL queries to the database
			statement = connect.createStatement();
			// Result set get the result of the SQL query
			resultSet = statement.executeQuery("SELECT User.userId, User.password, User.typeId FROM User WHERE email = '" + email + "';");

			if (!resultSet.next())
			{	
				System.out.println("ResultSet null");
				return null;
			}
			
			//If ResultSet is empty the email doesn't exist, otherwise it does.
			resultSet.beforeFirst();
			while (resultSet.next())
			{
				int userId = resultSet.getInt("userId");
				int userType = resultSet.getInt("typeId");
				if (resultSet.getString("password").equals(password))
				{
					resultSet = statement.executeQuery("SELECT User.userId, User.firstName, User.lastName, User.status, User.typeId, User.subpref FROM User WHERE email = '" + email + "' AND password = '" + password + "';");
					while (resultSet.next())
					{
						User user = new User(resultSet.getInt("userId"), resultSet.getString("firstName"), resultSet.getString("lastName"), email, password, resultSet.getInt("typeId"), resultSet.getInt("Status"), resultSet.getBoolean("subpref"));
						connect.close();
						return user;
					}
				}
				connect.close();
				return null;
			}

		} catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
		return null;
	}
	
	public boolean deleteUser(User user)
	{
		Connection connect = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			//Drop on ground
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			connect = DriverManager.getConnection("jdbc:mysql://69.89.31.237:3306/ristiod8_dawgcinema?user=ristiod8_dcuser&password=cinemadb&useSSL=false");

			// Statements allow to issue SQL queries to the database
			statement = connect.createStatement();
			// Update a user's status to 2 (suspended)
			statement.executeUpdate("SET FOREIGN_KEY_CHECKS = 0;");
			statement.executeUpdate("DELETE FROM User WHERE userId = " + user.getId() + ";");
			statement.executeUpdate("SET FOREIGN_KEY_CHECKS = 1;");
			connect.close();
			return true;
		} catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	public boolean suspendCustomer(User user)
	{
		Connection connect = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			//Drop on ground
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			connect = DriverManager.getConnection("jdbc:mysql://69.89.31.237:3306/ristiod8_dawgcinema?user=ristiod8_dcuser&password=cinemadb&useSSL=false");

			// Statements allow to issue SQL queries to the database
			statement = connect.createStatement();
			// Update a user's status to 2 (suspended)
			statement.executeUpdate("UPDATE TABLE User SET status = 2 WHERE userId = " + user.getId() + ";");
			connect.close();
			return true;
		} catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	public void verifyUser(int id)
	{
		Connection connect = null;
		Statement statement = null;
		try {
			//Drop on ground
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			connect = DriverManager.getConnection("jdbc:mysql://69.89.31.237:3306/ristiod8_dawgcinema?user=ristiod8_dcuser&password=cinemadb&useSSL=false");

			// Statements allow to issue SQL queries to the database
			statement = connect.createStatement();
			// Query to update user in DB to set them as active
			statement.executeUpdate("UPDATE User SET status = 1 WHERE UserId = " + id + ";");
			connect.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public boolean setTemporaryPassword(String email, String temporaryPassword)
	{
		Connection connect = null;
		Statement statement = null;
		int userId = -1;
		try {
			//Drop on ground
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			connect = DriverManager.getConnection("jdbc:mysql://69.89.31.237:3306/ristiod8_dawgcinema?user=ristiod8_dcuser&password=cinemadb&useSSL=false");

			// Statements allow to issue SQL queries to the database
			statement = connect.createStatement();
			
			ResultSet rs = statement.executeQuery("SELECT User.userId FROM User WHERE email = '" + email + "';");
			
			if (!rs.next())
				return false;
			rs.beforeFirst();
			
			while (rs.next())
			{
				userId = rs.getInt("userId");
			}
			
			// Query to update user in DB to set their temporary password
			statement.executeUpdate("UPDATE User SET password = '" + temporaryPassword + "' WHERE UserId = " + userId + ";");
			statement.executeUpdate("UPDATE User SET temporary = true WHERE UserId = " + userId + ";");
			connect.close();
			return true;
		} catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean isTemporary(int id)
	{
		Connection connect = null;
		Statement statement = null;
		try {
			//Drop on ground
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			connect = DriverManager.getConnection("jdbc:mysql://69.89.31.237:3306/ristiod8_dawgcinema?user=ristiod8_dcuser&password=cinemadb&useSSL=false");

			// Statements allow to issue SQL queries to the database
			statement = connect.createStatement();
			// Query to update user in DB to set them as active
			ResultSet rs = statement.executeQuery("SELECT User.temporary FROM User WHERE UserId = " + id + ";");
			while (rs.next())
			{
				boolean temporary = rs.getBoolean("temporary");
				connect.close();
				return temporary;
			}
			connect.close();
			return false;

		} catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	public void changePassword(int id, String password) {
		Connection connect = null;
		Statement statement = null;
		try {
			//Drop on ground
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			connect = DriverManager.getConnection("jdbc:mysql://69.89.31.237:3306/ristiod8_dawgcinema?user=ristiod8_dcuser&password=cinemadb&useSSL=false");

			// Statements allow to issue SQL queries to the database
			statement = connect.createStatement();
			// Query to update user in DB to set them as active
			statement.executeUpdate("UPDATE User SET password = '" + password + "' WHERE UserId = " + id + ";");
			statement.executeUpdate("UPDATE User SET temporary = false WHERE UserId = " + id + ";");
			connect.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void updateProfile(int uId, String fN, String lN, String email, boolean sub_pref)
	{
		SessionFactory sessionFactory = new Configuration().configure().addAnnotatedClass(User.class).buildSessionFactory();
		Session session = sessionFactory.openSession();

		try {
			// Starting Transaction
			Transaction transaction = session.beginTransaction();
			User user = (User) session.get(User.class, uId);
			
			user.setFirstName(fN);
			user.setLastName(lN);
			user.setEmail(email);
			user.setSub_pref(sub_pref);
			
			session.saveOrUpdate(user);
			transaction.commit();
			System.out.println("\n\n Details Updated \n");

		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			System.out.println("error");
		}
		finally
		{
			sessionFactory.close();
		}
	}
	
	public boolean updateAccountType(User user)
	{
		Connection connect = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			//Drop on ground
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			connect = DriverManager.getConnection("jdbc:mysql://69.89.31.237:3306/ristiod8_dawgcinema?user=ristiod8_dcuser&password=cinemadb&useSSL=false");

			// Statements allow to issue SQL queries to the database
			statement = connect.createStatement();
			// Update a user's status to 2 (suspended)
			statement.executeUpdate("UPDATE User SET TypeId = " + user.getRank() + " WHERE userId = " + user.getId() + ";");
			connect.close();
			return true;
		} catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean updateStatus(User user)
	{
		Connection connect = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			//Drop on ground
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			connect = DriverManager.getConnection("jdbc:mysql://69.89.31.237:3306/ristiod8_dawgcinema?user=ristiod8_dcuser&password=cinemadb&useSSL=false");

			// Statements allow to issue SQL queries to the database
			statement = connect.createStatement();
			// Update a user's status to 2 (suspended)
			statement.executeUpdate("UPDATE User SET status = " + user.getStatus() + " WHERE userId = " + user.getId() + ";");
			connect.close();
			return true;
		} catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public ArrayList<String> retrieveSubscribedUserEmails()
	{
		Connection connect = null;
		Statement statement = null;
		ResultSet resultSet = null;
		ArrayList<String> emails = new ArrayList<String>();
		try {
			//Drop on ground
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			connect = DriverManager.getConnection("jdbc:mysql://69.89.31.237:3306/ristiod8_dawgcinema?user=ristiod8_dcuser&password=cinemadb&useSSL=false");

			// Statements allow to issue SQL queries to the database
			statement = connect.createStatement();
			// Update a user's status to 2 (suspended)
			resultSet = statement.executeQuery("SELECT * FROM User WHERE SubPref = true;");
			if (!resultSet.next())
				return null;
			
			resultSet.beforeFirst();
			while (resultSet.next())
			{
				emails.add(resultSet.getString("email"));
			}
			connect.close();
			return emails;
		} catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
}