package dawgdrivein.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dawgdrivein.entity.Booking;
import dawgdrivein.entity.CreditCard;
import dawgdrivein.entity.Email;
import dawgdrivein.entity.Promotion;
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

		if (request.getSession().getAttribute("userId") == null || (int)request.getSession().getAttribute("status") != 1)
		{
			response.sendRedirect("index.jsp");
			return;
		}

		String cardNum = request.getParameter("cardNum");
		String month = request.getParameter("month");
		String year = request.getParameter("year");
		String code = request.getParameter("code");
		String cardType = request.getParameter("cardType");

		if (!cardNum.equals("") && !month.equals("") && !year.equals("") && !code.equals("") && !cardType.equals(""))
		{
			Date date = Date.valueOf(year + "-" + month + "-" + 11);
			CreditCard cc = new CreditCard(0, date, (Integer)request.getSession().getAttribute("userId"), cardNum, Integer.parseInt(code), cardType);
			//		if (cc.saveCreditCard() == false)
			//		{
			//			response.sendRedirect("Checkout.html");
			//			return;
			//		}


			Promotion promo = new Promotion();
			
			Booking booking = new Booking((Integer)request.getSession().getAttribute("userId"), 
					0, 
					((Double)request.getSession().getAttribute("total")).floatValue(), 
					(Integer)request.getSession().getAttribute("numSeats"), 
					(Integer)request.getSession().getAttribute("showId"),
					(Integer)promo.retrievePromoId((String)request.getSession().getAttribute("code")),
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

			Email email = new Email();

			DecimalFormat df = new DecimalFormat("0.00");
			df.setMinimumFractionDigits(2);
			df.setMaximumFractionDigits(2);

			email.orderConfirmation(
					booking.getBookingNo(),
					(String)request.getSession().getAttribute("email"),
					(String)request.getSession().getAttribute("firstName"),
					(String)request.getSession().getAttribute("lastName"),
					df.format(request.getSession().getAttribute("total")),
					(String)request.getSession().getAttribute("movieSelected"),
					(String)request.getSession().getAttribute("seating"),
					(int)request.getSession().getAttribute("numSeats"),
					(int)request.getSession().getAttribute("numSpaces"),
					(String)request.getSession().getAttribute("timeSelected"),
					(String)request.getSession().getAttribute("dateSelected"));	
			
			request.getSession().removeAttribute("code");
			request.getSession().removeAttribute("percentDiscount");
		}//If all parameters are filled in do these things
		
		response.sendRedirect("OrderConfirmation.jsp");
	}

}
