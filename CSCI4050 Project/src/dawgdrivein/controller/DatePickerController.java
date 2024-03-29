package dawgdrivein.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DatePickerController
 */
@WebServlet("/DatePickerController")
public class DatePickerController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DatePickerController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);

		if (!request.getParameter("dateSelected").equals(""))
		{
			request.getSession().setAttribute("dateSelected", request.getParameter("dateSelected"));
			if (request.getSession().getAttribute("userId") != null && (int)request.getSession().getAttribute("status") == 1)
				response.sendRedirect("TimePicker.jsp");
			else
				response.sendRedirect("index.jsp");
		}
		else
		{
			response.sendRedirect("DatePicker.jsp");
		}
	}

}
