package dawgdrivein.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import dawgdrivein.entity.Movie;

public class MovieDBA {

	/**
	 * Save a Movie to the database
	 * @param movie the movie to be inserted in the DB
	 * @return whether the save was successful
	 */
	public boolean saveMovie(Movie movie)
	{
		if (getMovieIdByName(movie.getTitle()) != -1)
				return false;
		
		SessionFactory sessionFactory = new Configuration().configure().addAnnotatedClass(Movie.class).buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		try {
            // Starting Transaction
            Transaction transaction = session.beginTransaction();
            session.save(movie);
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
	 * Update a Movie in the DB
	 * @param movie the object to be updated in the database
	 * @return whether the update was successful
	 */
	public boolean updateMovie(Movie movie)
	{
		System.out.println("Lol");
		SessionFactory sessionFactory = new Configuration().configure().addAnnotatedClass(Movie.class).buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		try {
            // Starting Transaction
            Transaction transaction = session.beginTransaction();
            Movie updatedMovie = (Movie) session.get(Movie.class, movie.getId());
            updatedMovie.setId(movie.getId());
            System.out.println(movie.getTitle());
            updatedMovie.setTitle(movie.getTitle());
            updatedMovie.setCast(movie.getCast());
            updatedMovie.setDescription(movie.getDescription());
            updatedMovie.setProducer(movie.getProducer());
            updatedMovie.setGenre(movie.getGenre());
            updatedMovie.setDirector(movie.getDirector());
            updatedMovie.setMPAA_rating(movie.getMPAA_rating());
            updatedMovie.setReleaseDate(movie.getReleaseDate());
            updatedMovie.setExpiration(movie.getExpiration());
            updatedMovie.setTrailer_picture(movie.getTrailer_picture());
            updatedMovie.setTrailer_video(movie.getTrailer_video());
            session.saveOrUpdate(updatedMovie);
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
	 * Delete a movie in the DB
	 * @param movie the movie to be deleted from the DB
	 * @return whether the delete was completed
	 */
	public boolean deleteMovie(Movie movie)
	{
		int movieId = getMovieIdByName(movie.getTitle());
		
		movie = retrieveMovie(movieId);
		
		final Calendar cal = Calendar.getInstance();
	    cal.add(Calendar.DATE, -1);
		movie.setExpiration(Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime())));
		
		return movie.updateMovie();
	}
	
	/**
	 * Retrieves Movie object from the database based on key ID
	 * @param id the primary key of the Movie we're grabbing
	 * @return the Movie object matching the one we're searching for
	 */
	public Movie retrieveMovie(int id)
	{
		SessionFactory sessionFactory = new Configuration().configure().addAnnotatedClass(Movie.class).buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		try {
            // Starting Transaction
            Transaction transaction = session.beginTransaction();
            Movie movie = (Movie)session.get(Movie.class, id);
            transaction.commit();
            System.out.println("\n\n Retrieved Movie by ID \n");
            return movie;
 
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
	
	public int getMovieIdByName(String name)
	{
		Connection connect = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			//Drop on ground
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			connect = DriverManager.getConnection("jdbc:mysql://69.89.31.237:3306/ristiod8_dawgcinema?user=ristiod8_dcuser&password=cinemadb&useSSL=false");

			// Statements allow to issue SQL queries to the database
			statement = connect.createStatement();
			// Result set get the result of the SQL query
			resultSet = statement.executeQuery("SELECT Movie.movieId FROM Movie WHERE title = '" + name + "';");

			//If ResultSet is empty the email doesn't exist, otherwise it does.
			if (!resultSet.next())
			{
				System.out.println("Email does not exist");
				//Close connection
				connect.close();
				return -1;
			}
			else
			{
				resultSet.beforeFirst();
				resultSet.next();
				int movieId = resultSet.getInt("movieId");
				//Close connection
				connect.close();
				return movieId;
			}

		} catch (Exception e)
		{
			e.printStackTrace();
			return -1;
		}
	}
}
