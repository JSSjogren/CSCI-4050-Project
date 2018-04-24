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

import dawgdrivein.entity.Booking;
import dawgdrivein.entity.Movie;
import dawgdrivein.entity.Showtime;
import dawgdrivein.entity.User;

public class BookingDBA {

	public boolean saveBooking(Booking booking)
	{
			
		SessionFactory sessionFactory = sessionFactory = new Configuration().configure().addAnnotatedClass(Booking.class).addAnnotatedClass(Showtime.class).addAnnotatedClass(User.class).addAnnotatedClass(Movie.class).buildSessionFactory();

		Session session = sessionFactory.openSession();

		try {
			// Starting Transaction
			Transaction transaction = session.beginTransaction();
			session.save(booking);
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
	
	public boolean deleteBooking(Booking booking)
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
			// Delete a booking
			//Have to set a foreign key check to off
			statement.executeUpdate("SET FOREIGN_KEY_CHECKS = 0;");
			statement.executeUpdate("DELETE FROM Booking WHERE bookingId = " + booking.getBookingNo() + ";");
			statement.executeUpdate("SET FOREIGN_KEY_CHECKS = 1;");
			connect.close();
			return true;
		} catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean updateBooking(Booking booking)
	{
		SessionFactory sessionFactory = new Configuration().configure().addAnnotatedClass(Booking.class).buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		try {
            // Starting Transaction
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(booking);
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
	
	public Booking retrieveBooking(int id)
	{
		SessionFactory sessionFactory = new Configuration().configure().addAnnotatedClass(Booking.class).buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		try {
            // Starting Transaction
            Transaction transaction = session.beginTransaction();
            Booking booking = (Booking)session.get(Booking.class, id);
            transaction.commit();
            System.out.println("\n\n Retrieved booking by ID \n");
            return booking;
 
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            System.out.println("error");
            return null;
        }
		finally
		{
			sessionFactory.close();
		}
	}
	
}
