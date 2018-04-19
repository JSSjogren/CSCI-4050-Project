package dawgdrivein.db;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import dawgdrivein.entity.Address;
import dawgdrivein.entity.User;

public class AddressDBA {

	
	public boolean saveAddress(Address address)
	{
		SessionFactory sessionFactory = new Configuration().configure().addAnnotatedClass(Address.class).buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		try {
            // Starting Transaction
            Transaction transaction = session.beginTransaction();
            session.save(address);
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
	
	public boolean deleteAddress(Address address)
	{
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		try {
            // Starting Transaction
            Transaction transaction = session.beginTransaction();
            session.delete(address);
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
	
	public void updateAddress(int userId, String street, String city, String state, int zip)
	{
		SessionFactory sessionFactory = new Configuration().configure().addAnnotatedClass(Address.class).buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		try {
            // Starting Transaction
            Transaction transaction = session.beginTransaction();
            
            Address address = (Address)session.get(Address.class, userId);
            	
            address.setStreet(street);
            address.setCity(city);
            address.setState(state);
            address.setZip(zip);
            
            session.saveOrUpdate(address);
            transaction.commit();
            System.out.println("\n\n Details Updated\n");
 
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            System.out.println("error");
        }
		finally
		{
			sessionFactory.close();
		}
	}
}
