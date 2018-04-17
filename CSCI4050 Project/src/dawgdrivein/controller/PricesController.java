package dawgdrivein.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
			
		}
		//new parking fee submitted
		else if(!newParking.equals(null)) {
			
		}
		//new child ticket price submitted
		else if(!newChild.equals(null)) {
			
		}
		//new adult ticket price submitted
		else if(!newAdult.equals(null)) {
	
		}
		//new senior ticket price submitted
		else if(!newSenior.equals(null)) {
			
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
