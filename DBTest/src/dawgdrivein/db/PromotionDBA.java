package dawgdrivein.db;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import dawgdrivein.entity.Promotion;
import dawgdrivein.entity.Promotion;

public class PromotionDBA {

	/**
	 * Saves a Promo object in the database
	 * @param promo the object to be stored in the DB
	 * @return whether the save was successful
	 */
	public boolean savePromo(Promotion promo)
	{
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		try {
            // Starting Transaction
            Transaction transaction = session.beginTransaction();
            session.save(promo);
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
	
	/**
	 * Update a promo in the DB
	 * @param promo the Promo object to be updated in the DB
	 * @return whether the update was successful
	 */
	public boolean updatePromo(Promotion promo)
	{
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		try {
            // Starting Transaction
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(promo);
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
	 * Remove a Promo object from the DB
	 * @param promo the Promo object to be deleted
	 * @return whether the delete was successful
	 */
	public boolean deletePromo(Promotion promo)
	{
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		try {
            // Starting Transaction
            Transaction transaction = session.beginTransaction();
            session.save(promo);
            transaction.commit();
            System.out.println("\n\n Details Removed \n");
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
	 * Retrieves a specific Promo based on ID
	 * @param id the ID to use as a reference
	 * @return the retrieved Promo object
	 */
	public Promotion retrievePromo(int id)
	{
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		try {
            // Starting Transaction
            Transaction transaction = session.beginTransaction();
            Promotion promotion = (Promotion)session.get(Promotion.class, id);
            transaction.commit();
            System.out.println("\n\n Retrieved Promotion by ID \n");
            return promotion;
 
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
