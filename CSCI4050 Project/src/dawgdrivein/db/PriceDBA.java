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

import dawgdrivein.entity.Price;
import dawgdrivein.entity.SystemAdministrator;

public class PriceDBA {

	public double retrieveChildTicketPrice()
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
			resultSet = statement.executeQuery("SELECT * FROM Price WHERE type = 'childPrice';");

			//If ResultSet is empty the email doesn't exist, otherwise it does.
			if (!resultSet.next())
			{
				//Close connection
				connect.close();
				return -1;
			}
			else
			{
				double childTicketPrice = resultSet.getDouble("price");
				connect.close();
				return childTicketPrice;
			}

		} catch (Exception e)
		{
			e.printStackTrace();
			return -1;
		}
	}

	public double retrieveAdultTicketPrice()
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
			resultSet = statement.executeQuery("SELECT * FROM Price WHERE type = 'adultPrice';");

			//If ResultSet is empty the email doesn't exist, otherwise it does.
			if (!resultSet.next())
			{
				//Close connection
				connect.close();
				return -1;
			}
			else
			{
				double adultTicketPrice = resultSet.getDouble("price");
				connect.close();
				return adultTicketPrice;
			}

		} catch (Exception e)
		{
			e.printStackTrace();
			return -1;
		}
	}

	public double retrieveSeniorTicketPrice()
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
			resultSet = statement.executeQuery("SELECT * FROM Price WHERE type = 'seniorPrice';");

			//If ResultSet is empty the email doesn't exist, otherwise it does.
			if (!resultSet.next())
			{
				//Close connection
				connect.close();
				return -1;
			}
			else
			{
				double seniorTicketPrice = resultSet.getDouble("price");
				connect.close();
				return seniorTicketPrice;
			}

		} catch (Exception e)
		{
			e.printStackTrace();
			return -1;
		}
	}

	public double retrieveOnlineFee()
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
			resultSet = statement.executeQuery("SELECT * FROM Price WHERE type = 'bookingFee';");

			//If ResultSet is empty the email doesn't exist, otherwise it does.
			if (!resultSet.next())
			{
				//Close connection
				connect.close();
				return -1;
			}
			else
			{
				double bookingFee = resultSet.getDouble("price");
				connect.close();
				return bookingFee;
			}

		} catch (Exception e)
		{
			e.printStackTrace();
			return -1;
		}
	}

	public double retrieveParkingSpaceFee()
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
			resultSet = statement.executeQuery("SELECT * FROM Price WHERE type = 'parkingFee';");

			//If ResultSet is empty the email doesn't exist, otherwise it does.
			if (!resultSet.next())
			{
				//Close connection
				connect.close();
				return -1;
			}
			else
			{
				double parkingFee = resultSet.getDouble("price");
				connect.close();
				return parkingFee;
			}

		} catch (Exception e)
		{
			e.printStackTrace();
			return -1;
		}
	}

	public void updatePrice(Price price)
	{
		SessionFactory sessionFactory = new Configuration().configure().addAnnotatedClass(Price.class).buildSessionFactory();
		Session session = sessionFactory.openSession();

		try {
			// Starting Transaction
			Transaction transaction = session.beginTransaction();
			session.saveOrUpdate(price);
			session.flush();
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
	
	public double retrievePriceByType(String type)
	{
		Connection connect = null;
		Statement statement = null;
		double price = -1;
		try {
			//Drop on ground
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			connect = DriverManager.getConnection("jdbc:mysql://69.89.31.237:3306/ristiod8_dawgcinema?user=ristiod8_dcuser&password=cinemadb&useSSL=false");

			// Statements allow to issue SQL queries to the database
			statement = connect.createStatement();
			
			ResultSet rs = statement.executeQuery("SELECT * FROM Price WHERE type = '" + type + "';");
			
			if (!rs.next())
				return -1.0;
			
			rs.beforeFirst();
			
			price = rs.getDouble("price");
			
			connect.close();
			return price;
		} catch (Exception e)
		{
			e.printStackTrace();
			return -1.0;
		}
	}
}
