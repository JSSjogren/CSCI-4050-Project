package dawgdrivein.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dawgdrivein.entity.Price;

/**
 * Servlet implementation class PricesController
 */
@WebServlet("/PricesController")
public class PricesController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PricesController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String newBooking = request.getParameter("booking");
		String newParking = request.getParameter("parking");
		String newChild = request.getParameter("child");
		String newAdult = request.getParameter("adult");
		String newSenior = request.getParameter("senior");
		
		//new booking fee submitted
		if(!newBooking.equals(null)) {
			Price price = new Price();
			price.setType("bookingFee");
			price.setPrice(Double.parseDouble(newBooking));
			price.updatePrice();
		}
		//new parking fee submitted
		else if(!newParking.equals(null)) {
			Price price = new Price();
			price.setType("parkingFee");
			price.setPrice(Double.parseDouble(newParking));
			price.updatePrice();
		}
		//new child ticket price submitted
		else if(!newChild.equals(null)) {
			Price price = new Price();
			price.setType("childPrice");
			price.setPrice(Double.parseDouble(newChild));
			price.updatePrice();
		}
		//new adult ticket price submitted
		else if(!newAdult.equals(null)) {
			Price price = new Price();
			price.setType("adultPrice");
			price.setPrice(Double.parseDouble(newAdult));
			price.updatePrice();
		}
		//new senior ticket price submitted
		else if(!newSenior.equals(null)) {
			Price price = new Price();
			price.setType("seniorPrice");
			price.setPrice(Double.parseDouble(newSenior));
			price.updatePrice();
		}
		response.sendRedirect("Administrator.jsp");	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
