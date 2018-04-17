package dawgdrivein.controller;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dawgdrivein.entity.Booking;
import dawgdrivein.entity.Showtime;

/**
 * Servlet implementation class RefundController
 */
@WebServlet("/RefundController")
public class RefundController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RefundController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//booking id to refund. Can be refunded if no late than 60 minutes before showtime.
		int bookingId = Integer.parseInt(request.getParameter("bookingId"));
		
		Booking booking = new Booking();
		booking = booking.retrieveBooking(bookingId);
		Showtime showtime = new Showtime();
		showtime = showtime.retrieveShowtime(booking.getShowtimeID());
		
		long difference = showtime.getShowtime().getTime() - System.currentTimeMillis();
		long minutes = TimeUnit.MILLISECONDS.toMinutes(difference);
		if (minutes > 60)
		{
			booking.deleteBooking();
			response.sendRedirect("EditProfile.jsp");
		}
		else
		{
			response.sendRedirect("RefundError.html");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
