package dawgdrivein.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dawgdrivein.entity.Address;
import dawgdrivein.entity.Email;
import dawgdrivein.entity.RegisteredCustomer;
import dawgdrivein.entity.User;

@WebServlet("/RegisterController")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	public RegisterController() {
		super();
	}
 
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		// Not passing ID through form???
		//int id = Integer.parseInt(request.getParameter("id"));
		
		String fn = request.getParameter("fn");
		String ln = request.getParameter("ln");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");
		// Form does not have phone number???
		//String phoneNumber = request.getParameter("phoneNumber");
		String addressStreet = request.getParameter("addressStreet");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		int zipCode = Integer.parseInt(request.getParameter("zip"));
		//value is "yes" if they chose to subscribe
		String subscribe = request.getParameter("subscribe");
		
		boolean sub = false;
		if (subscribe.equals("yes"))
		{
			sub = true;
		}
		else
		{
			sub = false;
		}
		
		int rank = 1;
		int status = 0;
		
		//checks if any form fields and empty
		if(fn.equals("") || ln.equals("") || email.equals("") || password.equals("") || confirmPassword.equals("") || addressStreet.equals("") || city.equals("") || state.equals("") || request.getParameter("zip").equals("") || !password.equals(confirmPassword)) {
			response.sendRedirect("RegistrationError.html");
			return;
		}
		//if all fields were entered....move on to handle all information, send verification code, etc...
		
		//Status 0 = Inactive, 1 = Active, 2 = Suspended
		//Create registered customer and save to database
		RegisteredCustomer rc = new RegisteredCustomer(fn, ln, email, password, 1, 0, sub);
		if (rc.emailExists() == false)
		{
			rc.saveRegisteredCustomer();
			
			Email verifyEmail = new Email();
			verifyEmail.verificationEmail(rc);
			
			request.getSession().setAttribute("userId", rc.getId());
			response.sendRedirect("Verification.html");
			
			int id = (Integer)request.getSession().getAttribute("userId");
			
			Address address = new Address(id, addressStreet, city, state, zipCode);
			address.saveAddress();
		}
		else
		{
			response.sendRedirect("RegistrationError.html");
		}
		
		
	}
}
