<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.ParseException"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.text.DecimalFormat"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>DDI Order Summary</title>
<style>
body {
	background-color: whitesmoke;
	margin: 0;
	margin-bottom: 20px;
}

form {
	padding-left: 10px;
	padding-right: 10px;
	padding-bottom: 5px;
}

.logo {
	max-height: 150px;
}

.title {
	font-size: 50px;
	font-family: "Apple Chancery", Times, serif;
}

.headerDiv {
	border-bottom: 5px solid black;
	margin-top: 0;
	margin-left: 0;
	margin-right: 0;
	background-color: lightgray
}

.register {
	font-size: 40px;
	text-align: center;
}

.registerBox {
	border: 3px solid black;
	width: 40%;
}

.boxTitle {
	font-size: 30px;
	padding-bottom: 15px;
	border: 3px solid black;
}

.boxContent {
	font-size: 20px;
}
</style>
<script>
	function movie() {
		window.location.href = "MovieSelect.jsp";
	}
	function date() {
		window.location.href = "DatePicker.jsp";
	}
	function seats() {
		window.location.href = "SeatPicker.jsp";
	}
</script>


</head>

<%
	/* session.setAttribute("firstName", "John");
	session.setAttribute("lastName", "Doe");
	session.setAttribute("email", "johndoe@gmail.com");
	session.setAttribute("movieSelected", "13 Hours");
	session.setAttribute("seating", "Spot 3");
	session.setAttribute("numSeats", 1);
	session.setAttribute("time", "12:00 PM");
	session.setAttribute("day", "05-10-2018");
	 */

	String fn = (String) session.getAttribute("firstName");
	String ln = (String) session.getAttribute("lastName");
	String email = (String) session.getAttribute("email");
	String movieSelected = (String) session.getAttribute("movieSelected");
	String seating = (String) session.getAttribute("seating");
	int numSeats = (int) session.getAttribute("numSeats");
	String time = (String) session.getAttribute("timeSelected");
	String day = (String) session.getAttribute("dateSelected");

	String name = fn + " " + ln;
	String timeAndDate = day + " " + time;

	pageContext.setAttribute("name", name);
	pageContext.setAttribute("email", email);
	pageContext.setAttribute("movieSelected", movieSelected);
	pageContext.setAttribute("seating", seating);
	pageContext.setAttribute("numSeats", numSeats);
	pageContext.setAttribute("time", timeAndDate);
%>

<body>
	<div class="headerDiv">
		<table>
			<tr>
				<td><img src="transparentWebSiteLogo.png" class="logo"></td>
				<td>
					<h1 class="title">Dawg Drive-in</h1>
				</td>
			</tr>
		</table>
	</div>
	<div>
		<p class="register">Order Summary</p>
	</div>
	<div>
		<table class="registerBox" align="center">
			<caption class="boxTitle">Order Details</caption>
			<tr>
				<td class="boxContent" style="font-weight: 800;">Name</td>

			</tr>
			<tr>
				<td style="font-size: 25px;" class="boxContent"><p>${name}</p></td>
			</tr>
			<tr>
				<td class="boxContent" style="font-weight: 800;">Email:</td>

			</tr>
			<tr>
				<td style="font-size: 25px;" class="boxContent"><p>${email}</p></td>
			</tr>
			<tr>
				<td class="boxContent" style="font-weight: 800;">Movie:</td>

			</tr>
			<tr>
				<td style="font-size: 25px;" class="boxContent">
					<p>${movieSelected}<span><button
								style="font-size: 25px;" onclick="movie();">Edit</button></span>
					</p>
				</td>
			</tr>
			<tr>
				<td class="boxContent" style="font-weight: 800;">Date and Time:</td>

			</tr>
			<tr>
				<td style="font-size: 25px;" class="boxContent;"><p>${time}
						<span><button style="font-size: 25px;" onclick="date();">Edit</button></span>
					</p></td>
			</tr>
			<tr>
				<td class="boxContent" style="font-weight: 800;">Parking Spot:</td>

			</tr>
			<tr>
				<td style="font-size: 25px;" class="boxContent"><p>${seating}<span><button
								style="font-size: 25px;" onclick="seats();">Edit</button></span>
					</p></td>
			</tr>
			<tr>
				<td class="boxContent" style="font-weight: 800;">Pricing and
					Total:</td>

			</tr>
			<tr>
				<%
					DecimalFormat df = new DecimalFormat("0.00");
					df.setMinimumFractionDigits(2);
					df.setMaximumFractionDigits(2);

					/* if (session.getAttribute("taxAmount") == null) { */
					session.setAttribute("spotsAndPrice", "Ticket Total: $" + df.format(session.getAttribute("preTotal")));

					System.out.println(session.getAttribute("taxAmount").getClass());
					session.setAttribute("taxAmount", session.getAttribute("taxAmount"));

					session.setAttribute("total", session.getAttribute("total"));

					session.setAttribute("parkingFee", session.getAttribute("parkingFee"));
					/* } */
				%>

				<td style="font-size: 25px;" class="boxContent"><p
						style="margin-bottom: 0px;">${spotsAndPrice}</p></td>
			</tr>
			<%
				if (request.getSession().getAttribute("discountAmount") != null) {
					session.setAttribute("discountAmount", session.getAttribute("discountAmount"));
			%>
			<tr>
				<td style="font-size: 25px;" class="boxContent"><p
						style="margin-bottom: 0px;">
						Percent Discount: -$<%
					out.print(df.format(session.getAttribute("discountAmount")));
				%>
					</p></td>
			</tr>
			<%
				}
			%>
			<tr>
				<td style="font-size: 25px;" class="boxContent"><p
						style="margin-bottom: 0px;">Online Fee: $2.00</p></td>
			</tr>
			<tr>
				<td style="font-size: 25px;" class="boxContent"><p
						style="margin-bottom: 0px; border-bottom: 1px dashed black;">
						Parking Space Fee: $<%
					out.print(df.format(session.getAttribute("parkingFee")));
				%>
					</p></td>
			</tr>
			<tr>
				<td style="font-size: 25px;" class="boxContent"><p
						style="margin-bottom: 0px;">
						Taxes: $<%
					out.print(df.format(session.getAttribute("taxAmount")));
				%>
					</p></td>
			</tr>
			<tr>
				<td style="font-size: 25px;" class="boxContent"><p
						style="font-weight: 900;">
						Total: $<%
					out.print(df.format(session.getAttribute("total")));
				%>
					</p></td>
			</tr>
			<tr>
				<td align="center">
					<form action="OrderSummaryController" method="post"
						style="font-size: 20px;">
						Promo Code:<input type="text" name="code"
							style="width: 100px; font-size: 20px;" /> <input type="submit"
							value="Apply" style="font-size: 20px;" />
					</form>
				</td>
			</tr>
			<tr style="height: 50px;">
				<td align="center">
					<form action="Checkout.html">
						<button type="submit"
							style="font-size: 20px; margin-top: 10px; width: 90%;">Proceed
							to Checkout</button>
					</form>

				</td>
			</tr>
			<tr>
				<td align="center">
					<button style="font-size: 20px; margin-top: 10px; width: 90%;">Cancel</button>
				</td>
			</tr>
		</table>
	</div>
</body>

</html>