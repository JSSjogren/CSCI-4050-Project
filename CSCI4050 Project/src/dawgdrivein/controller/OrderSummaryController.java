package dawgdrivein.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dawgdrivein.entity.Booking;
import dawgdrivein.entity.Promotion;

/**
 * Servlet implementation class OrderSummaryController
 */
@WebServlet("/OrderSummaryController")
public class OrderSummaryController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OrderSummaryController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		if (request.getParameter("code") != null)
		{
			int percentDiscount = new Promotion().retrievePromoAmount(request.getParameter("code"));
			//Calculate total
			Booking booking = new Booking();
			//Pre-total before applying discounts/tax
			double preTotal = (double)request.getSession().getAttribute("preTotal");
			//Start total
			double total = preTotal;
			//Calculate/store discount
			double discountAmount = booking.calculateDiscount(total, ((double)percentDiscount) / 100);
			System.out.println();
			//Apply discount to total
			total -= discountAmount;
			//Calculate/store tax
			double taxAmount = booking.calculateTax(total);
			//Apply tax to total;
			total += taxAmount;
			total += booking.getOnlineFee();
			total += (double)request.getSession().getAttribute("parkingFee");

			request.getSession().setAttribute("parkingFee", (double)request.getSession().getAttribute("parkingFee"));
			request.getSession().setAttribute("preTotal", preTotal);
			request.getSession().setAttribute("discountAmount", discountAmount);
			request.getSession().setAttribute("taxAmount", taxAmount);
			request.getSession().setAttribute("total", total);
			
		}
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
