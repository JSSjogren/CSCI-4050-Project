package dawgdrivein.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dawgdrivein.entity.Showtime;

/**
 * Servlet implementation class TimePickerController
 */
@WebServlet("/TimePickerController")
public class TimePickerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TimePickerController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getSession().setAttribute("timeSelected", request.getParameter("time"));
		String time = (String)request.getSession().getAttribute("timeSelected");
		
		if (request.getSession().getAttribute("userId") != null && request.getSession().getAttribute("status").equals("1"))
		{
			Showtime showtime = new Showtime();
			System.out.println(request.getSession().getAttribute("dateSelected") + "T" + time);
			request.getSession().setAttribute("showId", showtime.getShowtimeIdFromDB((Integer)request.getSession().getAttribute("movieId"), request.getSession().getAttribute("dateSelected") + "T" + time));
			response.sendRedirect("SeatPicker.jsp");
		}
		else
			response.sendRedirect("signIn.html");
		System.out.println(time);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
