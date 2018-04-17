package dawgdrivein.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dawgdrivein.entity.Address;
import dawgdrivein.entity.RegisteredCustomer;
import dawgdrivein.entity.User;

/**
 * Servlet implementation class UpdateProfileController
 */
@WebServlet("/UpdateProfileController")
public class UpdateProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateProfileController() {
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
		String newFirstName = request.getParameter("fn");
		String newLastName = request.getParameter("ln");
		String newEmail = request.getParameter("email");
		String newStreet = request.getParameter("street");
		String newCity = request.getParameter("city");
		String newState = request.getParameter("state");
		String newZip = request.getParameter("zip");
		String sub_pref = request.getParameter("subscribe");
		
		boolean newSub = false;
		if (sub_pref != null && sub_pref.equals("yes"))
		{
			newSub = true;
		}
		else
		{
			newSub = false;
		}
			
		
		if (!newFirstName.chars().allMatch(Character::isLetter) || !newLastName.chars().allMatch(Character::isLetter))
		{
			response.sendRedirect("EditProfile.jsp");
			return;
		}
		
		//Credit to https://stackoverflow.com/questions/33433797/how-to-check-if-an-email-address-is-valid
		if (!newEmail.matches("^[a-zA-Z0-9.]+@[a-zA-Z0-9]+(.[a-zA-Z]{2,})$"))
		{
			response.sendRedirect("EditProfile.jsp");
			return;
		}
		
		RegisteredCustomer customer = new RegisteredCustomer();
		customer.updateProfile((Integer)request.getSession().getAttribute("userId"), newFirstName, newLastName, newEmail, newSub);
		
		Address addr = new Address();
		addr.updateAddress((Integer)request.getSession().getAttribute("userId"), newStreet, newCity, newState, Integer.parseInt(newZip));
			
		response.sendRedirect("EditProfile.jsp");
		
	}

}
