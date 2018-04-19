package dawgdrivein.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dawgdrivein.entity.User;

/**
 * Servlet implementation class UpdateStatusController
 */
@WebServlet("/UpdateStatusController")
public class UpdateStatusController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateStatusController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);

		String uid = request.getParameter("userId");
		String stat = request.getParameter("status");
		
		if (!uid.equals("") && !stat.equals(""))
		{
			int userId = Integer.parseInt(uid);
			int status = Integer.parseInt(stat);

			User user = new User();
			user.setId(userId);
			user.setStatus(status);
			user.changeStatus();
		}
		response.sendRedirect("Administrator.jsp");
	}

}
