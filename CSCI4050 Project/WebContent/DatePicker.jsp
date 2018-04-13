<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@page import="java.sql.*"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.ParseException"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>DDI Pick Date</title>
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
                <p style="text-align: right">Return to <b>Home</b></p>
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
/*             				request.setAttribute("movieSelected", "13 Hours"); */
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
    				 		}
    				 		conn.close();
            		}catch(Exception e){
            			e.printStackTrace();
            		}
                %>
                
            </tr>
        </table>

        <table align="center">
            <tr>
                 <td>
                    <p class="dateTitle">Date: </p>
                </td>
                <form action = "DatePickerController" method = "get">
                <td>
                    <input style="margin-left: 40px;" type="date" min="${strCurrent}" max="${expire}" name="dateSelected">
                </td>
                <td>
                		<input type="submit" value="Select Date" />
                </td>
                </form>
            </tr>
        </table>
    </div>
    
    <div class="page-bg"></div>
</body>

</html>