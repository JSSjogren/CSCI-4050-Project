package dawgdrivein.controller;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dawgdrivein.entity.Movie;
import dawgdrivein.entity.Showtime;

/**
 * Servlet implementation class AddTimeController
 */
@WebServlet("/AddTimeController")
public class AddTimeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddTimeController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		String movie = request.getParameter("movieTitle");
		String dateAndTime = request.getParameter("dateAndTime");
		
		if (!movie.equals("") && !dateAndTime.equals(""))
		{
			Movie lookUpMovie = new Movie();
			int movieId = lookUpMovie.getMovieIdByName(movie);

			//Save showtime
			Showtime showtime = new Showtime();
			showtime.setMovieId(movieId);
			showtime.setShowtime(Timestamp.valueOf(dateAndTime.replace("T", " ") + ":00"));
			showtime.saveShowtime();
		}
		response.sendRedirect("Administrator.jsp");
	}

}
