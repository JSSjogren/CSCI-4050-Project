package dawgdrivein.db;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import dawgdrivein.entity.Booking;
import dawgdrivein.entity.Seat;
import dawgdrivein.entity.Seat;

public class SeatDBA {

	
	public boolean saveReservedSeat(Seat seat)
	{
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		try {
            // Starting Transaction
            Transaction transaction = session.beginTransaction();
            session.save(seat);
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
	
	public boolean deleteReservedSeat(Seat seat)
	{
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		try {
            // Starting Transaction
            Transaction transaction = session.beginTransaction();
            session.delete(seat);
            transaction.commit();
            System.out.println("\n\n Details Deleted\n");
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
	
	public boolean isSeatAvailable(Seat seat)
	{
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		try {
            // Starting Transaction
            Transaction transaction = session.beginTransaction();
            
            Seat checkSeat = (Seat) session.createQuery("SELECT * FROM seat WHERE showId='" + seat.getShowtimeID() + "' AND seatNo='" + seat.getSeat() + "';");
            System.out.println("\n\n Retrieved Seat by showID and seatNo \n");
            if (checkSeat == null)
            		return false;
            else
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
}
