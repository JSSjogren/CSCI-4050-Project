<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.ParseException"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>DDI Seating</title>
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
            background-color: gray;
        }

        .page-bg {
            background: linear-gradient( rgba(0, 0, 0, 0.6), rgba(0, 0, 0, 0.6)), url("backgroundPic.jpg");
            background-size: cover;
            background-repeat: no-repeat;
            -webkit-filter: blur(5px);
            -moz-filter: blur(5px);
            -o-filter: blur(5px);
            -ms-filter: blur(5px);
            filter: blur(5px);
            position: fixed;
            width: 110%;
            height: 110%;
            top: 0;
            left: 0;
            z-index: -1;
            border: 5px solid black;
            margin-left: -5px;
        }

        .screen {
            border: solid 4px white; 
            text-align: center;
            color: white;
            font-family: "bookman", Helvetica, serif;
            margin-bottom: 50px;
            border-radius: 5px;
            max-width: 400px;
            
        }

        .parking  {
            border: 3px solid white;
            padding-top: 20px;
            padding-bottom: 20px;
            padding-left: 10px;
            padding-right: 10px;
        }

        

        .seatingChart {
            margin-left: 600px;
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
        <table style="width: 100%;">
            <tr>
                <td style="width: 8%"><img src="transparentWebSiteLogo.png" class="logo"></td>
                <td style="margin-left: 0%;">
                    <h1 class="title">Dawg Drive-in</h1>
                </td>
                <td align="right"style="font-size: 20px; padding-right: 10px;">
                	<p style="text-align: right">Return to <b>Home</b></p>
                </td>
            </tr>
           </table>
    </div>
    <div>
        <p style="text-align: center;color: whitesmoke; font-size: 30px; padding-top: 10px; font-family: Apple Chancery, Time, serif;">Select Parking Spot</p>
    </div>

    <div align="center">
        <p class="screen">Movie Screen</p>
    </div>
    <div>
    	
    	<%
    		try{
			/* request.setAttribute("time", "12:00"); */
			Connection conn;
			Class.forName("com.mysql.jdbc.Driver");
	 		conn = DriverManager.getConnection("jdbc:mysql://69.89.31.237:3306/ristiod8_dawgcinema?user=ristiod8_dcuser&password=cinemadb&useSSL=false");
	 		String status = "Established connection";
	 		String movieSelected = (String) session.getAttribute("movieSelected");
	 		String dateSelected = (String) session.getAttribute("dateSelected");
	 		String timeSelected = (String) session.getAttribute("timeSelected");
	 		String query = "Select * from Movie where Title='" + movieSelected + "'";
	 		Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
	 		rs.next();
	 		String movieID = rs.getString("MovieId");
	 		query = "Select * from Showtime where MovieId = '" + movieID + "' AND TimeDate = '" + dateSelected + "T" + timeSelected + "'";
	 		Statement stmtTwo = conn.createStatement();
	 		ResultSet rsTwo = stmtTwo.executeQuery(query);
	 		rsTwo.next();
	 		String showId = rsTwo.getString("ShowId");
	 		query= "Select * from Seat where ShowId = '" + showId + "'";
	 		Statement stmtThree = conn.createStatement();
	 		ResultSet rsThree = stmtThree.executeQuery(query);
	 		ArrayList<String> seatsTaken = new ArrayList<String>();
	 		while(rsThree.next()){
	 			String seat = rsThree.getString("Number");
	 			seatsTaken.add(seat);
	 		}
	 		
    	%>
    		<form id="seats" action="SeatPickerController" method="post">
        <table align="center">
            <tr>
                <td class="parking">
                		<%
                			if(!seatsTaken.contains("1")){
                		%>
                    <input type="checkbox" name="seat1">
                    <%
                			}
                    %>
                    
                </td>
                <td class="parking">
                    <%
                			if(!seatsTaken.contains("2")){
                		%>
                    <input type="checkbox" name="seat2">
                    <%
                			}
                    %>
                </td>
                <td class="parking">
                    <%
                			if(!seatsTaken.contains("3")){
                		%>
                    <input type="checkbox" name="seat3">
                    <%
                			}
                    %>
                </td>
                <td class="parking">
                    <%
                			if(!seatsTaken.contains("4")){
                		%>
                    <input type="checkbox" name="seat4">
                    <%
                			}
                    %>
                </td>
                <td class="parking">
                    <%
                			if(!seatsTaken.contains("5")){
                		%>
                    <input type="checkbox" name="seat5">
                    <%
                			}
                    %>
                </td>
            </tr>
            <tr>
                <td style="padding-bottom: 20px;"></td>
            </tr>
            <tr>
                <td class="parking">
                    <%
                			if(!seatsTaken.contains("6")){
                		%>
                    <input type="checkbox" name="seat6">
                    <%
                			}
                    %>
                </td>
                <td class="parking">
                    <%
                			if(!seatsTaken.contains("7")){
                		%>
                    <input type="checkbox" name="seat7">
                    <%
                			}
                    %>
                </td>
                <td class="parking">
                    <%
                			if(!seatsTaken.contains("8")){
                		%>
                    <input type="checkbox" name="seat8">
                    <%
                			}
                    %>
                </td>
                <td class="parking">
                    <%
                			if(!seatsTaken.contains("9")){
                		%>
                    <input type="checkbox" name="seat9">
                    <%
                			}
                    %>
                </td>
                <td class="parking">
                    <%
                			if(!seatsTaken.contains("10")){
                		%>
                    <input type="checkbox" name="seat10">
                    <%
                			}
                    %>
                </td>
            </tr>
            <tr>
                <td style="padding-bottom: 20px;"></td>
            </tr>
            <tr>
                <td class="parking">
                    <%
                			if(!seatsTaken.contains("11")){
                		%>
                    <input type="checkbox" name="seat11">
                    <%
                			}
                    %>
                </td>
                <td class="parking">
                    <%
                			if(!seatsTaken.contains("12")){
                		%>
                    <input type="checkbox" name="seat12">
                    <%
                			}
                    %>
                </td>
                <td class="parking">
                    <%
                			if(!seatsTaken.contains("13")){
                		%>
                    <input type="checkbox" name="seat13">
                    <%
                			}
                    %>
                </td>
                <td class="parking">
                    <%
                			if(!seatsTaken.contains("14")){
                		%>
                    <input type="checkbox" name="seat14">
                    <%
                			}
                    %>
                </td>
                <td class="parking">
                    <%
                			if(!seatsTaken.contains("15")){
                		%>
                    <input type="checkbox" name="seat15">
                    <%
                			}
                    %>
                </td>
            </tr>
            <tr>
                <td style="padding-bottom: 20px;"></td>
            </tr>
            <tr>
                <td class="parking">
                    <%
                			if(!seatsTaken.contains("16")){
                		%>
                    <input type="checkbox" name="seat16">
                    <%
                			}
                    %>
                </td>
                <td class="parking">
                    <%
                			if(!seatsTaken.contains("17")){
                		%>
                    <input type="checkbox" name="seat17">
                    <%
                			}
                    %>
                </td>
                <td class="parking">
                    <%
                			if(!seatsTaken.contains("18")){
                		%>
                    <input type="checkbox" name="seat18">
                    <%
                			}
                    %>
                </td>
                <td class="parking">
                   	<%
                			if(!seatsTaken.contains("19")){
                		%>
                    <input type="checkbox" name="seat19">
                    <%
                			}
                    %>
                </td>
                <td class="parking">
                    <%
                			if(!seatsTaken.contains("20")){
                		%>
                    <input type="checkbox" name="seat20">
                    <%
                			}
                    %>
                </td>
            </tr>
            <tr>
                <td style="padding-bottom: 20px;"></td>
            </tr>
            <tr>
                <td class="parking">
                    <%
                			if(!seatsTaken.contains("21")){
                		%>
                    <input type="checkbox" name="seat21">
                    <%
                			}
                    %>
                </td>
                <td class="parking">
                    <%
                			if(!seatsTaken.contains("22")){
                		%>
                    <input type="checkbox" name="seat22">
                    <%
                			}
                    %>
                </td>
                <td class="parking">
                    <%
                			if(!seatsTaken.contains("23")){
                		%>
                    <input type="checkbox" name="seat23">
                    <%
                			}
                    %>
                </td>
                <td class="parking">
                    <%
                			if(!seatsTaken.contains("24")){
                		%>
                    <input type="checkbox" name="seat24">
                    <%
                			}
                    %>
                </td>
                <td class="parking">
                    <%
                			if(!seatsTaken.contains("25")){
                		%>
                    <input type="checkbox" name="seat25">
                    <%
                			}
                    %>
                </td>
            </tr>
        </table>
        <table align="center">
        		<tr>
        			<td align="center">
        				<p style="color: white; text-align: center; margin-top: 50px; font-size: 20px;">Select number of tickets based on age:</p>
        			</td>
        		</tr>
        		<tr>
        			<td align="center" style="margin-bottom: 15px; margin-top: 15px; padding: 15px;">
        				<span style="color: white; font-size: 20px;">Number of Children: </span> <input  type="number" value="0" name="numChildren" min="0" max="20"  />
        			</td>
        		</tr>
        		<tr>
        			<td align="center" style="margin-bottom: 15px; margin-top: 15px; padding: 15px;">
        				<span style="color: white; font-size: 20px; ">Number of Adults: </span> <input  type="number" value="0" name="numAdults" min="0" max="20"  />
        			</td>
        		<tr/>
        		<tr>
        			<td align="center" style="margin-bottom: 15px; margin-top: 15px; padding: 15px;">
        				<span style="color: white; font-size: 20px;">Number of Seniors: </span> <input  type="number" value="0" name="numSeniors" min="0" max="20"  />
        			</td>
        		</tr>
        		<tr>
                <td align="center">
                    <button style="font-size: 20px; margin-top: 10px; width: 300px;" form="seats">Submit</button>
                </td>
            </tr>
        </table>
        </form>
        <table align="center" style="margin-top: 15px;">
        		<tr>
        			<td><button style="font-size: 20px; width: 300px;" onclick="home();">Cancel</button></td>
        		</tr>
        </table>
        
        <%
    		}catch(Exception e){
    			e.printStackTrace();
    		}
        
        %>
    </div>
    
    <div class="page-bg"></div>
</body>

</html>