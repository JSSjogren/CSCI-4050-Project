package dawgdrivein.db;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import dawgdrivein.entity.CreditCard;
import dawgdrivein.entity.Hall;

public class HallDBA {

	/**
	 * Saves a Hall object into the database
	 * @param hall the hall to save
	 * @return whether the save was completed
	 */
	public boolean createHall(Hall hall)
	{
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		try {
            // Starting Transaction
            Transaction transaction = session.beginTransaction();
            session.save(hall);
            transaction.commit();
            System.out.println("\n\n Details Saved \n");
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
	
	/**
	 * Update a Hall object in the database
	 * @param hall the object to update in the DB
	 * @return whether the update was successful
	 */
	public boolean updateHall(Hall hall)
	{
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		try {
            // Starting Transaction
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(hall);
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
	
	/**
	 * Delete Hall object from the database
	 * @param hall the object to delete
	 * @return whether the delete was completed
	 */
	public boolean deleteHall(Hall hall)
	{
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		try {
            // Starting Transaction
            Transaction transaction = session.beginTransaction();
            session.delete(hall);
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
	
	/**
	 * Retrieves Hall object from the database based on key ID
	 * @param id the primary key of the hall we're grabbing
	 * @return the hall object matching the one we're searching for
	 */
	public Hall retrieveHall(int id)
	{
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		try {
            // Starting Transaction
            Transaction transaction = session.beginTransaction();
            Hall hall = (Hall)session.get(Hall.class, id);
            transaction.commit();
            System.out.println("\n\n Retrieved hall by ID \n");
            return hall;
 
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