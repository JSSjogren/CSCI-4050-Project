package dawgdrivein.db;

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
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		try {
            // Starting Transaction
            Transaction transaction = session.beginTransaction();
            session.delete(booking);
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
	
	public boolean updateBooking(Booking booking)
	{
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
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
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
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
