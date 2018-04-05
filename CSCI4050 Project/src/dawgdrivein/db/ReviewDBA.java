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
		try {
            // 1. configuring hibernate
            Configuration configuration = new Configuration().configure();
 
            // 2. create sessionfactory
            SessionFactory sessionFactory = configuration.buildSessionFactory();
 
            // 3. Get Session object
            Session session = sessionFactory.openSession();
 
            // 4. Starting Transaction
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
	}
	
	/**
	 * Updates the passed in Promo in the database
	 * @param review the Review to be updated
	 * @return whether the update was successful
	 */
	public boolean updateReview(Review review)
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
            session.saveOrUpdate(review);
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
	 * Deletes the passed in Review from the DB
	 * @param review the Review object to be deleted
	 * @return whether the delete was successful
	 */
	public boolean deleteReview(Review review)
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
            session.delete(review);
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
	 * Retrieves a specific Review based on ID
	 * @param id the ID to use as a reference
	 * @return the retrieved Review object
	 */
	public Review retrieveReview(int id)
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
            Review review = (Review)session.get(Review.class, id);
            transaction.commit();
            System.out.println("\n\n Retrieved Review by ID \n");
            return review;
 
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            System.out.println("error");
            return null;
        }
	}
}
