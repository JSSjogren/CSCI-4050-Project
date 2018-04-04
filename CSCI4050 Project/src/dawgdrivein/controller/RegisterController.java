package dawgdrivein.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dawgdrivein.entity.RegisteredCustomer;
import dawgdrivein.entity.User;


public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	public RegisterController() {
		super();
	}
 
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
 
		int id = Integer.parseInt(request.getParameter("id"));
		String fn = request.getParameter("fn");
		String ln = request.getParameter("ln");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");
		String phoneNumber = request.getParameter("phoneNumber");
		String address = request.getParameter("address");
		int rank = 4;
		int status = 0;
		//todo
		boolean subscribed = true;
		
		RequestDispatcher rd = null;
 
		if (!password.equals(confirmPassword))
			rd = request.getRequestDispatcher("/error.jsp");
		
		RegisteredCustomer rc = new RegisteredCustomer(id, fn, ln, email, password, phoneNumber, address, rank, status, subscribed);
		boolean success = rc.saveRegisteredCustomer(rc);
		
		if (success) {
			rd = request.getRequestDispatcher("/success.jsp");
		} else {
			rd = request.getRequestDispatcher("/error.jsp");
		}
		rd.forward(request, response);
	}
}
