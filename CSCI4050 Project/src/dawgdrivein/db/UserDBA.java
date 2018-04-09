package dawgdrivein.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

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

	public RegisteredCustomer validateUser(String email, String password)
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
			
			System.out.println("Past resultSet null");
			//If ResultSet is empty the email doesn't exist, otherwise it does.
			resultSet.beforeFirst();
			while (resultSet.next())
			{
				System.out.println("Pls");
				int userId = resultSet.getInt("userId");
				int userType = resultSet.getInt("typeId");
				if (resultSet.getString("password").equals(password))
				{
					SessionFactory sessionFactory =  getAnnotatedSessionfactory(userType); //Creating a session factory object
					Session session = sessionFactory.openSession(); //Creating a session object for inserting users  object to the database table User
					try
					{
						Transaction trans = session.beginTransaction();
						RegisteredCustomer cust = (RegisteredCustomer) session.get(RegisteredCustomer.class, userId);
						trans.commit();
						return cust;
					} catch (Exception e)
					{
						e.printStackTrace();
						return null;
					}
				}
			}

		} catch (Exception e)
		{
			System.out.println("Stacktrace: ");
			e.printStackTrace();
			return null;
		}
		return null;
	}

	public boolean updateUser(User user)
	{
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();

		try {
			// Starting Transaction
			Transaction transaction = session.beginTransaction();
			session.saveOrUpdate(user);
			transaction.commit();
			System.out.println("\n\n Details Updated \n");
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
	
	public SessionFactory getAnnotatedSessionfactory(int rank)
	{
		if (rank == 1)
			return new Configuration().configure().addAnnotatedClass(RegisteredCustomer.class).buildSessionFactory();
		else if (rank == 2)
			return new Configuration().configure().addAnnotatedClass(Employee.class).buildSessionFactory();
		else if (rank == 3)
			return new Configuration().configure().addAnnotatedClass(Manager.class).buildSessionFactory();
		else if (rank == 4)
			return new Configuration().configure().addAnnotatedClass(SystemAdministrator.class).buildSessionFactory();
		else
			return null;
	}

	public boolean deleteUser(User user)
	{
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();

		try {
			// Starting Transaction
			Transaction transaction = session.beginTransaction();
			session.delete(user);
			transaction.commit();
			System.out.println("\n\n Details Deleted \n");
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

	public boolean suspendCustomer(RegisteredCustomer customer)
	{
		return true;
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

		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
