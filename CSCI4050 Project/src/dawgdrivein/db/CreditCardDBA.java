package dawgdrivein.db;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import dawgdrivein.entity.Booking;
import dawgdrivein.entity.CreditCard;

public class CreditCardDBA {

	
	/**
	 * Save CreditCard in DB
	 * @param cc the object to be saved
	 * @return whether save was successful
	 */
	public boolean saveCreditCard(CreditCard cc)
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
            session.save(cc);
            transaction.commit();
            System.out.println("\n\n Details Added \n");
            return true;
 
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            System.out.println("error");
            return false;
        }
	}

	/**
	 * Update CreditCard object in database
	 * @param cc the object to be updated
	 * @return whether update was successful
	 */
	public boolean updateCreditCard(CreditCard cc)
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
            session.delete(cc);
            transaction.commit();
            System.out.println("\n\n Details Deleted \n");
            return true;
 
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            System.out.println("error");
            return false;
        }
	}
	
	/**
	 * Delete CreditCard object from DB
	 * @param cc the object to be deleted
	 * @return whether the delete was successful
	 */
	public boolean deleteCreditCard(CreditCard cc)
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
            session.saveOrUpdate(cc);
            transaction.commit();
            System.out.println("\n\n Details Updated \n");
            return true;
 
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            System.out.println("error");
            return false;
        }
	}
	
	/**
	 * Retrieve a single CreditCard object from DB
	 * @param id the key to compare in the DB
	 * @return the retrieved CreditCard object
	 */
	public CreditCard retrieveCreditCard(int id)
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
            CreditCard cc = (CreditCard)session.get(CreditCard.class, id);
            transaction.commit();
            System.out.println("\n\n Retrieved credit card by ID \n");
            return cc;
 
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            System.out.println("error");
            return null;
        }
	}
}

