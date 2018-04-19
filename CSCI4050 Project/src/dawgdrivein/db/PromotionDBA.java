package dawgdrivein.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;

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
		SessionFactory sessionFactory = new Configuration().configure().addAnnotatedClass(Promotion.class).buildSessionFactory();
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
		Connection connect = null;
		Statement statement = null;
		try {
			//Drop on ground
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			connect = DriverManager.getConnection("jdbc:mysql://69.89.31.237:3306/ristiod8_dawgcinema?user=ristiod8_dcuser&password=cinemadb&useSSL=false");

			// Statements allow to issue SQL queries to the database
			statement = connect.createStatement();

			statement.executeUpdate("UPDATE Promotion SET PercentDiscount = " + promo.getPercent_discount() + ", ExpDate = '" + promo.getExp_date() + "' WHERE code = '" + promo.getCode() + "';");
			connect.close();
			return true;
		} catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Remove a Promo object from the DB
	 * @param promo the Promo object to be deleted
	 * @return whether the delete was successful
	 */
	public boolean deletePromo(Promotion promo)
	{
		Connection connect = null;
		Statement statement = null;
		try {
			//Drop on ground
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			connect = DriverManager.getConnection("jdbc:mysql://69.89.31.237:3306/ristiod8_dawgcinema?user=ristiod8_dcuser&password=cinemadb&useSSL=false");

			// Statements allow to issue SQL queries to the database
			statement = connect.createStatement();

			statement.executeUpdate("SET FOREIGN_KEY_CHECKS = 0;");
			statement.executeUpdate("DELETE FROM Promotion WHERE code = '" + promo.getCode() + "';");
			statement.executeUpdate("SET FOREIGN_KEY_CHECKS = 1;");
			connect.close();
			return true;
		} catch (Exception e)
		{
			e.printStackTrace();
			return false;
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

	public int retrievePromoAmount(String code)
	{
		int discountPercent = 0;
		try{
			Connection conn;
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://69.89.31.237:3306/ristiod8_dawgcinema?user=ristiod8_dcuser&password=cinemadb&useSSL=false");
			String status = "Established connection";
			String query = "Select * from Promotion where Code = '" + code + "';";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			if(rs.next()){
				discountPercent = rs.getInt("PercentDiscount");
			}
			conn.close();
			return discountPercent;
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}

	}
}
