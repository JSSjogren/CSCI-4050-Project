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

import dawgdrivein.entity.Showtime;

public class ShowtimeDBA {

	//todo: Fill in DBAccess methods
	public boolean saveShowtime(Showtime showtime)
	{
		SessionFactory sessionFactory = new Configuration().configure().addAnnotatedClass(Showtime.class).buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		try {
            // Starting Transaction
            Transaction transaction = session.beginTransaction();
            session.save(showtime);
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
	
	public boolean updateShowtime(Showtime showtime)
	{
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		try {
            // Starting Transaction
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(showtime);
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
	
	public boolean deleteShowtime(Showtime showtime)
	{
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		try {
            // Starting Transaction
            Transaction transaction = session.beginTransaction();
            session.delete(showtime);
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
	
	public int getShowtimeIdFromDB(int movieId, String time)
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
			resultSet = statement.executeQuery("SELECT Showtime.showId FROM Showtime WHERE movieId = '" + movieId + "' AND timedate = '" + time + "';");

			//If ResultSet is empty the email doesn't exist, otherwise it does.
			if (!resultSet.next())
			{
				//Close connection
				connect.close();
				return -1;
			}
			else
			{
				resultSet.beforeFirst();
				resultSet.next();
				int showId = resultSet.getInt("showId");
				//Close connection
				connect.close();
				return showId;
			}

		} catch (Exception e)
		{
			e.printStackTrace();
			return -1;
		}
	}
	
}
