<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.sql.*"%>
<%@page import="java.util.Date"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>DDI Profile</title>
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
        
        input {
            font-size: 20px;
        }
        
        th {
        		font-size: 25px;
        		padding: 20px;
        		border: 3px solid black;
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
        
        .normalText {
            text-align: center;
            font-size: 25px;
        }
        
        .subTitle {
            text-align: center; 
            font-size: 40px;
        }
        
        .register {
            font-size: 30px;
            text-align: center;

        }

        .registerBox {
            border: 3px solid black;
            margin: 0 auto;

        }

        .boxTitle {
            font-size: 30px;
            padding-bottom: 15px;
            border: 3px solid black;
        }

        .boxContent {
            font-size: 20px;
            margin: 0 auto;
        }
        
    </style>
    <script>
    		function home(){
			window.location.href = "index.jsp";
		}
    </script>
</head>

<body>
    <div class="headerDiv">
        <table>
            <tr>
                <td><img src="transparentWebSiteLogo.png" class="logo"></td>
                <td>
                    <h1 class="title">Dawg Drive-in</h1>
                </td>
                <td style="width: 900px; font-size: 20px;">
                <p style="text-align: right; cursor:pointer;" onclick="home();">Return Home</p>
                </td>
            </tr>
        </table>
    </div>
    <div>
    		<h1 align="center">My Profile</h1>
    </div>
    <div id="EditProfile">
    		<form action="UpdateProfileController" method="post">
        <table class="registerBox">
        		<%
        			try{
        				Connection conn;
        				Class.forName("com.mysql.jdbc.Driver");
        			 	conn = DriverManager.getConnection("jdbc:mysql://69.89.31.237:3306/ristiod8_dawgcinema?user=ristiod8_dcuser&password=cinemadb&useSSL=false");
        			 	String status = "Established connection";
        			 	String user = (String) session.getAttribute("userId");
        			 	String query = "Select * from User where UserId = '" + user + "'";
        				Statement stmt = conn.createStatement();
        				ResultSet rs = stmt.executeQuery(query);
        				while(rs.next()){
        					String fn = rs.getString("FirstName");
        					String ln = rs.getString("LastName");
        					String email = rs.getString("Email");
  						query = "Select * from Address where UserId = '" + user + "'";
  						Statement stmtTwo = conn.createStatement();
  						ResultSet rsTwo = stmtTwo.executeQuery(query);
  						String street = rsTwo.getString("Street");
  						String city = rsTwo.getString("city");
  						String state = rsTwo.getString("State");
  						String zip = rsTwo.getString("Zip");
  						break;
        				}
        				conn.close();
        			}catch(Exception e){
        				e.printStackTrace();
        			}
     	
        		
        		
        		%>
        
        
            <caption class="boxTitle">Edit Profile</caption>
            <tr>
                <td class="boxContent">First Name:</td>

            </tr>
            <tr>
                <td><input type="text" name="fn" size="60" value="${fn}"></td>
            </tr>
            <tr>
                <td class="boxContent">Last Name:</td>

            </tr>
            <tr>
                <td><input type="text" name="ln" size="60" value="${ln}"></td>
            </tr>
            <tr>
                <td class="boxContent">Email:</td>

            </tr>
            <tr>
                <td><input type="text" size="60" name="email" value="${email}"></td>
            </tr>
            <tr>
                <td class="boxContent">Street Address:</td>

            </tr>
            <tr>
                <td><input type="text" size="60" name="street" value="${street}"></td>
            </tr>
            <tr>
                <td class="boxContent">City:</td>

            </tr>
            <tr>
                <td><input type="text" size="60" name="city" value="${city}"></td>
            </tr>
            <tr>
                <td class="boxContent">State:</td>

            </tr>
            <tr>
                <td><input type="text" size="60" name="state" value="${state}"></td>
            </tr>
            <tr>
                <td class="boxContent">Zip Code:</td>

            </tr>
            <tr>
                <td><input type="text" size="60" name="zip" value="${zip}"></td>
            </tr>
            <tr class="boxContent">
                <td><input type="checkbox" name="subscribe" value="yes" style="margin-left: 10px; margin-top: 15px;"> Subscribe to Promotions</td>
            </tr>
            <tr style="height: 50px;">
                <td align="center">
                    <input type="submit" value="Submit Changes" style="font-size: 20px;"/>
                </td>
            </tr>
        </table>
        </form>
    </div>
    <div id="orderHistory" style="margin-top: 70px;">
    		<div id="managePromotions">
    		<div>
        		<p class="register">Order History</p>
    		</div>
    		<div>
        		<table align="center" style="border: 3px solid black; border-collapse: collapse;">
        		    <tr>
                		<th class="tableCell">
                    		<p style="text-align: center; font-weight: 900;">BookingId</p>
                		</th>
                		<th class="tableCell">
                    		<p style="text-align: center; font-weight: 900;">ShowtimeId</p>
                		</th>
                		<th class="tableCell">
                    		<p style="text-align: center; font-weight: 900;">Number of Tickets</p>
                		</th>
                		<th class="tableCell">
                    		<p style="text-align: center; font-weight: 900;">PromoId</p>
                		</th>
                		<th class="tableCell">
                    		<p style="text-align: center; font-weight: 900;">Total Price</p>
                		</th>
            		</tr>
            		<%
			
			try {
				Connection conn;
				Class.forName("com.mysql.jdbc.Driver");
			 	conn = DriverManager.getConnection("jdbc:mysql://69.89.31.237:3306/ristiod8_dawgcinema?user=ristiod8_dcuser&password=cinemadb&useSSL=false");
			 	String status = "Established connection";
			 	String user = (String) session.getAttribute("userId");
			 	String query = "Select * from Booking where UserId = '" + user + "'";
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				while(rs.next()){
			%>
				<tr>
				<td style="font-size: 20px; padding: 15px; border: 3px solid black;" align="center">
				<%
					out.print(rs.getString("BookingId"));
				%>
				</td>
				<td style="font-size: 20px; padding: 15px; border: 3px solid black;" align="center">
				<%
					out.print(rs.getString("ShowtimeId"));
				%>
				</td>
				<td style="font-size: 20px; padding: 15px; border: 3px solid black;" align="center">
				<%
					out.print(rs.getString("NoOfTickets"));
				%>
				</td>
				<td style="font-size: 20px; padding: 15px; border: 3px solid black;" align="center">
				<%
					out.print(rs.getString("PromoId"));
				%>
				</td>
				<td style="font-size: 20px; padding: 15px; border: 3px solid black;" align="center">
				<%
					out.print(rs.getString("TotalPrice"));
				%>
				</td>
				</tr>
			<%	
				}
				conn.close();
			}catch(Exception e){
				e.printStackTrace();
			}
            %>
        		</table>
    		</div>
    		</div>
    </div>
    <div id="returnTicket" style="margin-top: 70px;">
    		<form action="RefundController" method="get">
    		<table align="center" style="border: 3px solid black; border-collapse: collapse;">
    			<tr>
    				<th style="border: 3px solid black;">Enter BookingId of Order to get Refund</th>
    			</tr>
    			<tr">
    				<td>BookingId: <input type="text" name="bookingId" style="width: 395px; margin-top: 30px; margin-bottom: 30px;" /></td>
    			</tr>
    			<tr">
    				<td align="center"><input type="submit" value="Submit" style="width: 200px; margin-top: 15px; margin-bottom: 15px;" /></td>
    			</tr>
    		</table>
    		</form>
    </div>
    

</body>

</html>