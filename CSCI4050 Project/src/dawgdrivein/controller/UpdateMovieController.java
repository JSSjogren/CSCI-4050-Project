package dawgdrivein.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dawgdrivein.entity.Movie;

/**
 * Servlet implementation class UpdateMovieController
 */
@WebServlet("/UpdateMovieController")
public class UpdateMovieController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateMovieController() {
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
		int movieId = Integer.parseInt(request.getParameter("movieId"));
		String title = request.getParameter("title");
		String genre = request.getParameter("genre");
		String cast = request.getParameter("cast");
		String director = request.getParameter("director");
		String producer = request.getParameter("producer");
		String description = request.getParameter("description");
		String picture = request.getParameter("picture");
		String video = request.getParameter("video");
		String rating = request.getParameter("rating");
		String rd = request.getParameter("release");
		String exp = request.getParameter("expiration");

		if (!title.equals("") && !genre.equals("") && !cast.equals("") && !director.equals("") && !producer.equals("") && !description.equals("") && !picture.equals("") && !video.equals("") && !rating.equals("") && !rd.equals("") && !exp.equals(""))
		{
			Date releaseDate = Date.valueOf(rd);
			Date expiration = Date.valueOf(exp);

			Movie movie = new Movie(movieId, title, genre, cast, director, producer, description, picture, video, rating, releaseDate, expiration);
			movie.updateMovie();
		}
		
		response.sendRedirect("Administrator.jsp");
	}

}
