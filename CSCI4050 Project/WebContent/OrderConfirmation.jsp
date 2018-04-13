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
    <title>DDI Order Confirmation</title>
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
    
    function home(){
		window.location.href = "index.jsp";
    }
    
    </script>
    
    
</head>

<%




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
                <td class="boxContent" style="font-weight:800;">Name</td>

            </tr>
            <tr>
                <td style="font-size: 25px;" class="boxContent"><p>${name}</p></td>
            </tr>
            <tr>
                <td class="boxContent" style="font-weight:800;">Email:</td>

            </tr>
            <tr>
                <td style="font-size: 25px;" class="boxContent"><p>${email}</p></td>
            </tr>
            <tr>
                <td class="boxContent" style="font-weight:800;">Movie:</td>

            </tr>
            <tr>
                <td style="font-size: 25px;" class="boxContent"> <p>${movieSelected}</p></td>
            </tr>
            <tr>
                <td class="boxContent" style="font-weight:800;">Date and Time:</td>

            </tr>
            <tr>
                <td style="font-size: 25px;" class="boxContent;"><p>${time}</p></td>
            </tr>
            <tr>
                <td class="boxContent" style="font-weight:800;">Parking Spot:</td>

            </tr>
            <tr>
                <td style="font-size: 25px;" class="boxContent"><p>${seating}</p></td>
            </tr>
            <tr>
                <td class="boxContent" style="font-weight:800;">Pricing and Total:</td>

            </tr>
            <tr>
            		<%
            			boolean discount = false;
            		 	String discountPercent = "0";
            			if(request.getParameter("code") != null){
            				String discountCode = request.getParameter("code");
            				try{
            					Connection conn;
            					Class.forName("com.mysql.jdbc.Driver");
            			 		conn = DriverManager.getConnection("jdbc:mysql://69.89.31.237:3306/ristiod8_dawgcinema?user=ristiod8_dcuser&password=cinemadb&useSSL=false");
            			 		String status = "Established connection";
            			 		String query = "Select * from Promotion where Code = '" + discountCode + "'";
            			 		Statement stmt = conn.createStatement();
            			 		ResultSet rs = stmt.executeQuery(query);
            			 		if(rs.next()){
            			 			discount = true;
            			 			discountPercent = rs.getString("PercentDiscount");
            			 		}
            			 		conn.close();
            				}catch(Exception e){
            					e.printStackTrace();
            				}

            			}
            		
    					DecimalFormat df = new DecimalFormat("0.00");
    					df.setMinimumFractionDigits(2);

    					session.setAttribute("spotsAndPrice", "Ticket Total: $" + df.format(session.getAttribute("preTotal")));

    					session.setAttribute("taxAmount", "" + df.format(Double.parseDouble((String)session.getAttribute("taxAmount"))));

    					session.setAttribute("total", "" + df.format(Double.parseDouble((String)session.getAttribute("total"))));

    					session.setAttribute("parkingFee", "" + df.format(Double.parseDouble((String)session.getAttribute("parkingFee"))));
            			
            			
            		%>
            
                <td style="font-size: 25px;" class="boxContent"><p style="margin-bottom:0px;">${spotsAndPrice}</p></td>
            </tr>
            <tr>
                <td style="font-size: 25px;" class="boxContent"><p style="margin-bottom:0px;">Taxes: $ ${taxes}</p></td>
            </tr>
            <tr>
                <td style="font-size: 25px;" class="boxContent"><p style="margin-bottom:0px; border-bottom: 1px dashed black;">Online Fee: $2.00</p></td>
            </tr>
            <tr>
                <td style="font-size: 25px;" class="boxContent"><p style="font-weight:900;">Total: $ ${total}</p></td>
            </tr>
            <tr>
            		<td align="center">
            			<p style="font-size:22px;"><b>The order confirmation has been sent to your email!</b></p>
            		</td>
            </tr>
     		<tr>
     			<td align="center" style="padding-bottom: 15px;">
     				<button style="font-size: 20px; width: 300px;" onclick="home();">Return Home</button>
     			</td>
     		</tr>
        </table>
    </div>
    
    <% 
    session.removeAttribute("spotsAndPrice");
    session.removeAttribute("taxAmount");
    session.removeAttribute("total");
    session.removeAttribute("parkingFee");
    session.removeAttribute("movieSelected");
    session.removeAttribute("seating");
    session.removeAttribute("seatList");
    session.removeAttribute("numSeats");
    session.removeAttribute("numSpaces");
    session.removeAttribute("timeSelected");
    session.removeAttribute("dateSelected");
    session.removeAttribute("movieId");
    session.removeAttribute("showId");
    session.removeAttribute("preTotal");
    session.removeAttribute("discountAmount");
    session.removeAttribute("taxAmount");
    session.removeAttribute("numSpaces");
    session.removeAttribute("numChildren");
    session.removeAttribute("numAdults");
    session.removeAttribute("numSeniors");
    %>
</body>

</html>