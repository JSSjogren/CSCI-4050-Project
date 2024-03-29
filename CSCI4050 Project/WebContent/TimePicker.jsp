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
    <title>DDI Pick Time</title>
    <style>
        body {
            background-color: whitesmoke;
            margin: 0;
            margin-bottom: 20px;
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

        .moviePic {
            max-height: 200px;
        }

        .movieTitle {
            color: white;
            font-family: "bookman", Helvetica, serif;
            font-weight: 900;
            margin-left: 40px;
            font-size: 30px;
        }
        
        .dateTitle {
            color: white;
            font-family: "bookman", Helvetica, serif;
            font-weight: 900;
            font-size: 25px;
        }

        .times {
            color: white;
            border: 2px solid white;
            font-family: "bookman", Helvetica, serif;
            margin-left: 20px;
            font-size: 25px;
            border-radius: 10px;
            padding: 4px;
            
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
                	<p style="text-align: right">Return to <b style="cursor: pointer;" onclick="home();">Home</b></p>
                </td>
            </tr>
           </table>
    </div>
    <div>
        <p style="text-align: center;color: whitesmoke; font-size: 30px; padding-top: 10px; font-family: Apple Chancery, Time, serif;">Pick a day</p>
    </div>
    <div>
        <table align="center">
            <tr>
            		<%
            		try {
						Connection conn;
    						Class.forName("com.mysql.jdbc.Driver");
    				 		conn = DriverManager.getConnection("jdbc:mysql://69.89.31.237:3306/ristiod8_dawgcinema?user=ristiod8_dcuser&password=cinemadb&useSSL=false");
    				 		String status = "Established connection";
    				 		String movieSelected = (String) session.getAttribute("movieSelected");
    				 		String query = "Select * from Movie where Title='" + movieSelected + "'";
    				 		Statement stmt = conn.createStatement();
        					ResultSet rs = stmt.executeQuery(query);
    				 		while(rs.next()){
            					String picture = rs.getString("TrailerPicture");
            					String title = rs.getString("Title");
            					String rating = rs.getString("MpaaRating");
            					String genre = rs.getString("Genre");
            					String releaseDate = rs.getString("releaseDate");
            					DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            					Date release = new Date();
            					try {
            				 	    release = df.parse(releaseDate);
            				 	} catch (ParseException e) {
            				 	    e.printStackTrace();
            				 	}
            					Date min = new Date();
            					if(release.after(min)){
            						min = release;
            					}
            					String strCurrent = df.format(min);
            					//System.out.println(strCurrent);
            					String expire = rs.getString("expiration");
            					//System.out.println(expire);
            					pageContext.setAttribute("strCurrent", strCurrent);
            					pageContext.setAttribute("expire", expire);
            					pageContext.setAttribute("picture", picture);
      						pageContext.setAttribute("title", title);
      						pageContext.setAttribute("rating", rating);
      						pageContext.setAttribute("genre", genre);
            		%>
            		
            
                <td>
                    <img src="${picture}" class="moviePic">
                </td>
                <td>
                    <p class="movieTitle">${title}<span style="margin-left:20px; font-size: 40px; background-color: white;">|</span> <span style="margin-left: 10px; font-size: 20px;"> ${genre} </span><span style="margin-left:20px; font-size: 40px; background-color: white;">|</span> <span style="margin-left: 10px; font-size: 20px;"> ${rating} </span></p>
                </td>
                
                <%
                		break;
    				 		}
    				 		conn.close();
            		}catch(Exception e){
            			e.printStackTrace();
            		}
                %>
                
            </tr>
        </table>

        <table align="center" style="margin-top: 100px;">
        
        	<tr>
            		<%
            		try {
						Connection conn;
    						Class.forName("com.mysql.jdbc.Driver");
    				 		conn = DriverManager.getConnection("jdbc:mysql://69.89.31.237:3306/ristiod8_dawgcinema?user=ristiod8_dcuser&password=cinemadb&useSSL=false");
    				 		String status = "Established connection";
    				 		String movieSelected = (String) session.getAttribute("movieSelected");
    				 		String dateSelected = (String) session.getAttribute("dateSelected");
    				 		String query = "Select * from Movie where Title='" + movieSelected + "'";
    				 		Statement stmt = conn.createStatement();
        					ResultSet rs = stmt.executeQuery(query);
    				 		rs.next();
    				 		String movieID = rs.getString("MovieId");
    				 		query = "Select * from Showtime where MovieId ='" + movieID + "'";
    				 		Statement stmtTwo = conn.createStatement();
    				 		ResultSet rsTwo = stmtTwo.executeQuery(query);
    				 		while(rsTwo.next()){
    				 			String dateTime = rsTwo.getString("TimeDate");
    				 			String date = dateTime.substring(0, 10);
    				 			pageContext.setAttribute("", dateTime);
    				 			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    				 			DateFormat dfTwo = new SimpleDateFormat("yyyy-MM-dd");
    				 			Date available = new Date();
    			
    				 			try {
            				 	    available = df.parse(dateTime);
            				 	    
            				 	} catch (ParseException e) {
            				 	    e.printStackTrace();
            				 	}
    				 			int hour = available.getHours();
    				 			String hourOriginal = Integer.toString(hour);
    				 			String part = "AM";
    				 			if(hour >= 12){
    				 				part = "PM";
    				 			}
    				 			else if(hour == 0){
    				 				hour = 12;
    				 			}
    				 			
    				 			String strHour = Integer.toString(hour);
    				 			String strMin = dateTime.substring(14,16);
    				 			String paramTime = hourOriginal + ":" + strMin;
    				 			String fullTime = strHour + ":" + strMin + " " + part;
    				 			pageContext.setAttribute("paramTime", paramTime);
    				 			pageContext.setAttribute("fullTime", fullTime);
    				 			
    				 			Date picked = new Date();
    				 			Date storedDate = new Date();
    				 			try {
            				 	    storedDate = dfTwo.parse(date);
            				 	   	picked = dfTwo.parse(dateSelected);
            				 	} catch (ParseException e) {
            				 	    e.printStackTrace();
            				 	}
    				 			if(storedDate.toString().equals(picked.toString())){
    				 				
    				 			
    				 			
            					
            		%>
            		<td>
            			<form id="${fullTime}"  action = "TimePickerController" method = "post">
            			<input type="text" value="${paramTime}" name="time" style="display:none;"/>
            			<button type="submit" form="${fullTime}" style="font-size: 22px; border-radius: 4px;">${fullTime}</button>
            			</form>
            		</td>
                
                <%
    				 			}
    				 		}
    				 		conn.close();
            		}catch(Exception e){
            			e.printStackTrace();
            		}
                %>
                
            </tr>
        
        
        
        
        </table>
        
        
        
    </div>
    
    <div class="page-bg"></div>
</body>

</html>