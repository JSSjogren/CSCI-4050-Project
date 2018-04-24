package dawgdrivein.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dawgdrivein.entity.Movie;

/**
 * Servlet implementation class MovieSelectionController
 */
@WebServlet("/MovieSelectionController")
public class MovieSelectionController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MovieSelectionController() {
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

		String movieSelected = request.getParameter("movieSelected");
		if (!movieSelected.equals(""))
		{
			request.getSession().setAttribute("movieSelected", movieSelected);
			if (request.getSession().getAttribute("userId") != null && (int)request.getSession().getAttribute("status") == 1)
			{
				Movie movie = new Movie();
				request.getSession().setAttribute("movieId", movie.getMovieIdByName(movieSelected));
				response.sendRedirect("DatePicker.jsp");
			}
			else
				response.sendRedirect("index.jsp");
		}
		else
		{
			response.sendRedirect("MovieBrowse.jsp");
		}
	}

}
