package dawgdrivein.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dawgdrivein.entity.User;

/**
 * Servlet implementation class PasswordChangeController
 */
@WebServlet("/PasswordChangeController")
public class PasswordChangeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PasswordChangeController() {
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
		String password = request.getParameter("password");
		String confirm = request.getParameter("confirmPassword");

		if (!password.equals("") && !confirm.equals(""))
		{
			if (password.equals(confirm))
			{
				User user = new User();
				user.setId((int)request.getSession().getAttribute("userId"));
				user.changePassword(password, confirm);
				response.sendRedirect("ChangePasswordConfirmation.html");
			}
		}
		else
			response.sendRedirect("ChangePassword.html");
	}

}
