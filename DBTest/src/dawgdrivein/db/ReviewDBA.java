package dawgdrivein.db;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import dawgdrivein.entity.Promotion;
import dawgdrivein.entity.Review;

public class ReviewDBA {

	/**
	 * Saves the passed in Review to the database
	 * @param review the Review to be saved
	 * @return whether the Save was completed
	 */
	public boolean saveReview(Review review)
	{
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		try {
            // Starting Transaction
            Transaction transaction = session.beginTransaction();
            session.save(review);
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
	 * Updates the passed in Promo in the database
	 * @param review the Review to be updated
	 * @return whether the update was successful
	 */
	public boolean updateReview(Review review)
	{
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		try {
            // Starting Transaction
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(review);
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
	 * Deletes the passed in Review from the DB
	 * @param review the Review object to be deleted
	 * @return whether the delete was successful
	 */
	public boolean deleteReview(Review review)
	{
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		try {
            // Starting Transaction
            Transaction transaction = session.beginTransaction();
            session.delete(review);
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
	 * Retrieves a specific Review based on ID
	 * @param id the ID to use as a reference
	 * @return the retrieved Review object
	 */
	public Review retrieveReview(int id)
	{
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		try {
            // Starting Transaction
            Transaction transaction = session.beginTransaction();
            Review review = (Review)session.get(Review.class, id);
            transaction.commit();
            System.out.println("\n\n Retrieved Review by ID \n");
            return review;
 
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
