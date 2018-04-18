package dawgdrivein.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

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
}
