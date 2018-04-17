package dawgdrivein.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dawgdrivein.entity.Email;
import dawgdrivein.entity.Promotion;

/**
 * Servlet implementation class AddPromoController
 */
@WebServlet("/AddPromoController")
public class AddPromoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddPromoController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code = request.getParameter("code");
		int percentDiscount = Integer.parseInt(request.getParameter("discount"));
		String expDate = request.getParameter("expiration");
		
		Promotion promo = new Promotion(0, Date.valueOf(expDate), code, percentDiscount);
		promo.savePromo();
		
		Email email = new Email();
		email.sendPromoEmails(promo);
		
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
