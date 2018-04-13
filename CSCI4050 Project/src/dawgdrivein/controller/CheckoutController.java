package dawgdrivein.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dawgdrivein.entity.Booking;
import dawgdrivein.entity.CreditCard;
import dawgdrivein.entity.Seat;
import dawgdrivein.entity.Ticket;

/**
 * Servlet implementation class CheckoutController
 */
@WebServlet("/CheckoutController")
public class CheckoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CheckoutController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		if (request.getSession().getAttribute("userId") == null)
		{
			response.sendRedirect("signIn.html");
			return;
		}

		String cardNum = request.getParameter("cardNum");
		String month = request.getParameter("month");
		String year = request.getParameter("year");
		String code = request.getParameter("code");
		String cardType = request.getParameter("cardType");

		Date date = Date.valueOf(year + "-" + month + "-" + 11);
		CreditCard cc = new CreditCard(0, date, (Integer)request.getSession().getAttribute("userId"), cardNum, Integer.parseInt(code), cardType);
		if (cc.saveCreditCard() == false)
		{
			response.sendRedirect("Checkout.html");
			return;
		}
		
		
		Booking booking = new Booking((Integer)request.getSession().getAttribute("userId"), 
				0, 
				Float.parseFloat((String)request.getSession().getAttribute("total")), 
				(Integer)request.getSession().getAttribute("numSeats"), 
				(Integer)request.getSession().getAttribute("showId"),
				1,
				(Integer)request.getSession().getAttribute("movieId"));
		booking.saveBooking();
		
		ArrayList<String> seatList = (ArrayList<String>)request.getSession().getAttribute("seatList");
		
		for (int i = 0; i < (Integer)request.getSession().getAttribute("numSpaces"); i ++)
		{
			int seatNum = Integer.parseInt(seatList.get(i).replace("seat", ""));
			Seat seat = new Seat(0, (Integer)request.getSession().getAttribute("showId"), seatNum);
			seat.saveReservedSeat();
			
			Ticket ticket = new Ticket(booking.getBookingNo(), 0, seat.getSeat());
			ticket.saveTicket();
		}
		
		response.sendRedirect("OrderConfirmation.jsp");
	}

}
