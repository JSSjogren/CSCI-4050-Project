package dawgdrivein.db;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import dawgdrivein.entity.Booking;
import dawgdrivein.entity.CreditCard;
import dawgdrivein.entity.Movie;

public class OrderHistoryDBA {

	public List<Booking> getOrderHistory(int userID)
	{
		try {
            // 1. configuring hibernate
            Configuration configuration = new Configuration().configure();
 
            // 2. create sessionfactory
            SessionFactory sessionFactory = configuration.buildSessionFactory();
 
            // 3. Get Session object
            Session session = sessionFactory.openSession();
 
            // 4. Starting Transaction
            Transaction transaction = session.beginTransaction();
            List<Booking> orderHistory = (List<Booking>) session.createQuery("SELECT * FROM booking WHERE userID='" + userID + "';").list();
            System.out.println("\n\n Retrieved list of bookings by userID \n");
            return orderHistory;
 
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            System.out.println("error");
            return null;
        }
		
	}
	
	public List<Booking> getOrderHistory()
	{
		try {
            // 1. configuring hibernate
            Configuration configuration = new Configuration().configure();
 
            // 2. create sessionfactory
            SessionFactory sessionFactory = configuration.buildSessionFactory();
 
            // 3. Get Session object
            Session session = sessionFactory.openSession();
 
            // 4. Starting Transaction
            Transaction transaction = session.beginTransaction();
            List<Booking> orderHistory = (List<Booking>) session.createQuery("SELECT * FROM booking;").list();
            System.out.println("\n\n Retrieved list of bookings by userID \n");
            return orderHistory;
 
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            System.out.println("error");
            return null;
        }
	}
	
}
