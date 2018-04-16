package dawgdrivein.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dawgdrivein.entity.Promotion;

/**
 * Servlet implementation class UpdatePromoController
 */
@WebServlet("/UpdatePromoController")
public class UpdatePromoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatePromoController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code = request.getParameter("code");
		String percentDiscount = request.getParameter("discount");
		String expDate = request.getParameter("expiration");
		
		Promotion promo = new Promotion();
		promo.setCode(code);
		promo.setPercent_discount(Integer.parseInt(percentDiscount));
		promo.setExp_date(Date.valueOf(expDate));
		promo.updatePromo();
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
