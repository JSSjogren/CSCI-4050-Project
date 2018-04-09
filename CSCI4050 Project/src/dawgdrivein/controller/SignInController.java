package dawgdrivein.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dawgdrivein.entity.RegisteredCustomer;

/**
 * Servlet implementation class SignInController
 */
@WebServlet("/SignInController")
public class SignInController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignInController() {
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
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		//Check if these strings match a record in the database. If these do not match or are empty, send to SignInError.html
		if(email.equals("") || password.equals("")) {
			response.sendRedirect("SignInError.html");
		}
		
		
		RegisteredCustomer customer = new RegisteredCustomer();
		customer = customer.validate(email, password);
		
		if (customer == null)
			response.sendRedirect("SignInError.html");
		
		//Setup session so that we can get important attributes of the signed in user
		request.getSession().setAttribute("userId", customer.getId());
		request.getSession().setAttribute("firstName", customer.getFirstName());
		request.getSession().setAttribute("lastName", customer.getLastName());
		request.getSession().setAttribute("rank", customer.getRank());
		request.getSession().setAttribute("status", customer.getStatus());
		
		System.out.println(request.getSession().getAttribute("firstName"));
		
		response.sendRedirect("index.jsp");
		
		//Now we know fields are not empty if they pass ^
		//Now check if database has a matching record...if so sign them in and create the session stuff...if not send redirect again
		//When signing someone into the session set a session attribute named 'rank' to the string representation of their ranking. This
		//is easier for me to use when determining if the person is an admin.
	}

}
