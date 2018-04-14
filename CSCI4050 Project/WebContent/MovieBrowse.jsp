<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.sql.*"%>
<%@page import="java.util.Date"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>DDI Browse</title>
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
        
    </style>
    <script>
    		function home(){
			window.location.href = "index.jsp";
		}
    		function showNow(){
    			document.getElementById("comingSoon").style.display = "none";
    			document.getElementById("nowPlaying").style.display = "initial";
    		}
    		function showSoon(){
    			document.getElementById("nowPlaying").style.display = "none";
    			document.getElementById("comingSoon").style.display = "initial";
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
                	<p style="text-align: right; cursor:pointer;" onclick="home();">Return to <b>Home</b></p>
                </td>
            </tr>
           </table>
    </div>
    <div>
    		<h1 align="center">Browse Movies</h1>
    </div>
    <table align="center">
    		<tr>
    			<td><button style="font-size: 15px; margin-right:15px;" onclick="showNow();">Browse Now PLaying Movies</button></td>
    			<td><button style="font-size: 15px; margin-left: 15px;" onclick="showSoon();">Browse Coming Soon Movies</button></td>
    		</tr>
    </table>
    <div id="nowPlaying" style="display: none;">
    		<table align="center" style="border: 3px solid black; border-collapse: collapse; margin-top: 15px;">
    			<tr>
    				<th>Title</th>
    				<th>Rating</th>
    				<th>Trailer</th>
    			</tr>
    			<%
    				Connection conn;
    			
    				try {
    					Class.forName("com.mysql.jdbc.Driver");
    				 	conn = DriverManager.getConnection("jdbc:mysql://69.89.31.237:3306/ristiod8_dawgcinema?user=ristiod8_dcuser&password=cinemadb&useSSL=false");
    				 	String status = "Established connection";
    				 	String query = "Select * from Movie";
        				Statement stmt = conn.createStatement();
        				Statement s = conn.createStatement();
        				ResultSet rs = stmt.executeQuery(query);
        				ResultSet rsTwo;
        				int count = 0;
        				while(rs.next()){
        					count++;
        				}
        				pageContext.setAttribute("count", count);
        				rsTwo = s.executeQuery(query);
    				%>
    			<c:forEach begin="1" end="${count}">
    				<%
    				rsTwo.next();
    				String title = rsTwo.getString("Title");
    				String rating = rsTwo.getString("MpaaRating");
    				Date date = rsTwo.getDate("releaseDate");
    				Date eDate = rsTwo.getDate("expiration");
    				String trailer = rsTwo.getString("TrailerVideo");
    				pageContext.setAttribute("title", title);
    				pageContext.setAttribute("rating", rating);
    				pageContext.setAttribute("trailer", trailer);
    				Date current = new Date();
    				boolean now = false;
    				boolean showing = false;
    				now = current.after(date);
    				showing = current.before(eDate);
    				if(now && showing){
    				%>
    				<tr>
    					<td  align="center" style="font-size: 30px; border: 3px solid black; padding: 15px;">${title}</td>
    					<td  align="center" style="font-size: 30px; border: 3px solid black; padding: 15px;">${rating}</td>
    					<td style="border:3px solid black; ">
    						<iframe width="420" height="345" src="https://www.youtube.com/embed/${trailer}">
						</iframe>
    					</td>
    				</tr>
    				<%
    				}
    				%>
    			</c:forEach>
    			<%
    				}catch(Exception e) {
    					e.printStackTrace();
    				}
    			%>
    		</table>
    </div>
    <div id="comingSoon" style="display: none;">
    		<table align="center" style="border: 3px solid black; border-collapse: collapse; margin-top: 15px;">
    			<tr>
    				<th>Title</th>
    				<th>Rating</th>
    				<th>Trailer</th>
    			</tr>
    			<%
    			
    				try {
    					Class.forName("com.mysql.jdbc.Driver");
    				 	conn = DriverManager.getConnection("jdbc:mysql://69.89.31.237:3306/ristiod8_dawgcinema?user=ristiod8_dcuser&password=cinemadb&useSSL=false");
    				 	String status = "Established connection";
    				 	String query = "Select * from Movie";
        				Statement stmt = conn.createStatement();
        				Statement s = conn.createStatement();
        				ResultSet rs = stmt.executeQuery(query);
        				ResultSet rsTwo;
        				int count = 0;
        				while(rs.next()){
        					count++;
        				}
        				pageContext.setAttribute("count", count);
        				rsTwo = s.executeQuery(query);
    				%>
    			<c:forEach begin="1" end="${count}">
    				<%
    				rsTwo.next();
    				String title = rsTwo.getString("Title");
    				String rating = rsTwo.getString("MpaaRating");
    				Date date = rsTwo.getDate("releaseDate");
    				Date eDate = rsTwo.getDate("expiration");
    				String trailer = rsTwo.getString("TrailerVideo");
    				pageContext.setAttribute("title", title);
    				pageContext.setAttribute("rating", rating);
    				pageContext.setAttribute("trailer", trailer);
    				Date current = new Date();
    				boolean showing = false;
    				boolean now = false;
    				showing = current.before(eDate);
    				now = current.before(date);
    				if(now && showing){
    				%>
    				<tr>
    					<td  align="center" style="font-size: 30px; border: 3px solid black; padding: 15px;">${title}</td>
    					<td  align="center" style="font-size: 30px; border: 3px solid black; padding: 15px;">${rating}</td>
    					<td style="border:3px solid black; ">
    						<iframe width="420" height="345" src="https://www.youtube.com/embed/${trailer}">
						</iframe>
    					</td>
    				</tr>
    				<%
    				}
    				%>
    			</c:forEach>
    			<%
    				}catch(Exception e) {
    					e.printStackTrace();
    				}
    			%>
    		</table>
    </div>

</body>

</html>