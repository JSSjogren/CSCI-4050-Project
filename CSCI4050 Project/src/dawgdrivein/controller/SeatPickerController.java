package dawgdrivein.controller;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dawgdrivein.entity.Booking;

/**
 * Servlet implementation class SeatPickerController
 */
@WebServlet("/SeatPickerController")
public class SeatPickerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SeatPickerController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		if (request.getSession().getAttribute("userId") == null)
		{
			response.sendRedirect("signIn.html");
			return;
		}
		
		String seats = new String();
		int numSeats = 0;
		
		if (Collections.list(request.getParameterNames()).size() == 0)
		{
			response.sendRedirect("SeatPicker.jsp");
			return;
		}
		
		for (String key : Collections.list(request.getParameterNames()))
		{
			if (key.contains("seat"))
			{
				seats += key + ", ";
				numSeats += 1;
			}
		}
		
		int numChildren = 1;
		int numAdults = 2;
		int numSeniors = 1;
		int promoDiscount = 0;
		
		seats = seats.substring(0, seats.length() - 2);
		seats = seats.replaceAll("s", "S");
		seats = seats.replaceAll("t", "t ");
		
		request.getSession().setAttribute("numSeats", numSeats);
		request.getSession().setAttribute("seating", seats);
		System.out.println(seats);
		
		//Calculate total
		Booking booking = new Booking();
		//Pre-total before applying discounts/tax
		double preTotal = booking.calculatePreTotal(numChildren, numAdults, numSeniors);
		//Start total
		double total = preTotal;
		//Calculate/store discount
		double discountAmount = booking.calculateDiscount(total, promoDiscount);
		//Apply discount to total
		total -= discountAmount;
		//Calculate/store tax
		double taxAmount = booking.calculateTax(total);
		//Apply tax to total;
		total += taxAmount;
		total += booking.getOnlineFee();
		
		DecimalFormat df = new DecimalFormat("#.##");
		request.getSession().setAttribute("preTotal", Double.valueOf(df.format(preTotal)));
		request.getSession().setAttribute("discountAmount", Double.valueOf(df.format(discountAmount)));
		request.getSession().setAttribute("taxAmount", Double.valueOf(df.format(taxAmount)));
		request.getSession().setAttribute("total", Double.valueOf(df.format(total)));
		
		response.sendRedirect("OrderSummary.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
