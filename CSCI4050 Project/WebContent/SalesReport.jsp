<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.sql.*"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>DDI Administrator</title>
    <style>
        body {
            background-color: rgb(245, 245, 245);
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
            margin: 0 auto;
            
            

        }

        .boxTitle {
            font-size: 30px;
            padding-bottom: 15px;
            border: 3px solid black;
        }

        .boxContent {
            font-size: 20px;
            
        }

        .promotitle {
            font-family: "bookman", Helvetica, serif;
            text-align: center;
            font-size: 25px;
        }

        .tableCell {
            border: 2px solid black;
            font-size: 20px;
            width: 200px;
        }

    </style>
    <script>
    function home(){
		window.location.href = "index.jsp";
	}
    function add(){
    		document.getElementById("update").style.display = "none";
    		document.getElementById("delete").style.display = "none";
		document.getElementById("add").style.display = "initial";
    }
    function update(){
    		document.getElementById("add").style.display = "none";
    		document.getElementById("delete").style.display = "none";
		document.getElementById("update").style.display = "initial";
    }
    function deletePromo(){
    		document.getElementById("add").style.display = "none";
    		document.getElementById("update").style.display = "none";
		document.getElementById("delete").style.display = "initial";
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
                	<p style="text-align: right; cursor: pointer;" onclick="home();">Return to <b>Home</b></p>
                </td>
            </tr>
           </table>
    </div>
    
	<div style="border: 3px solid black;">
		<p style="text-align: center; font-size: 30px;"><b>Sales Report-All-Time Sales:</b></p>
		<%
			Connection conn;
			try{
				Class.forName("com.mysql.jdbc.Driver");
			 	conn = DriverManager.getConnection("jdbc:mysql://69.89.31.237:3306/ristiod8_dawgcinema?user=ristiod8_dcuser&password=cinemadb&useSSL=false");
			 	String status = "Established connection";
			 	String query = "Select * from Booking";
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				double total = 0.0;
				int count = 0;
				while(rs.next()){
					count++;
					double sub = rs.getDouble("TotalPrice");
					total = total + sub;
				}
				DecimalFormat df = new DecimalFormat("#.00");
				pageContext.setAttribute("total", df.format(total));
				pageContext.setAttribute("count", count);
			
		%>
			<table align="center" style="padding: 15px; margin-bottom: 15px;">
			<tr>
				<td align="left" style="font-size: 20px;padding-bottom: 20px;">Total Bookings:</td>
				<td align="center" style="font-size: 20px; padding-bottom: 20px;">${count}</td>
			</tr>
			<tr>
				<td align="left" style="font-size: 20px;">Total Sales:</td>
				<td align="center" style="font-size: 20px;">$${total}</td>
			</tr>
			</table>
		<%
			conn.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		%>
	</div>
	
	<div style="border: 3px solid black;">
		<p style="text-align: center; font-size: 30px;"><b>Sales Report based on Movie:</b></p>
		<form action="SalesReport.jsp">
		<table align="center" style="padding-bottom: 15px;">
		<tr>
		<td>
		<select name="movie">
		<%
			try{
				Class.forName("com.mysql.jdbc.Driver");
			 	conn = DriverManager.getConnection("jdbc:mysql://69.89.31.237:3306/ristiod8_dawgcinema?user=ristiod8_dcuser&password=cinemadb&useSSL=false");
			 	String status = "Established connection";
			 	String query = "Select * from Movie";
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				
				while(rs.next()){
					String title = rs.getString("Title");
					String movieID = rs.getString("MovieId");
					pageContext.setAttribute("title", title);
					pageContext.setAttribute("id", movieID);
		%>
			<option value="${id}">${title}</option>
		
		<%
				}		
		%>
		</select>
		</td>
		<td><input type="submit" value="Submit" /></td>
		<%	
			conn.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		
		%>
		</tr>
		</table>
		</form>
		
		<%
			if(request.getParameter("movie") != null){
				String movieSelected = request.getParameter("movie");
				try{
					Class.forName("com.mysql.jdbc.Driver");
				 	conn = DriverManager.getConnection("jdbc:mysql://69.89.31.237:3306/ristiod8_dawgcinema?user=ristiod8_dcuser&password=cinemadb&useSSL=false");
				 	String status = "Established connection";
				 	String findTitle = "Select * from Movie where MovieId = '" + movieSelected +  "'";
				 	Statement s = conn.createStatement();
				 	ResultSet temp = s.executeQuery(findTitle);
				 	temp.next();
				 	String name = temp.getString("Title");
				 	pageContext.setAttribute("name", name);
				 	
				 	String query = "Select * from Booking where MovieId = '" + movieSelected +  "'";
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(query);
					double total = 0.0;
					int count = 0;
					while(rs.next()){
						count++;
						double sub = rs.getDouble("TotalPrice");
						total = total + sub;
					}
					DecimalFormat df = new DecimalFormat("#.00");
					pageContext.setAttribute("total", df.format(total));
					pageContext.setAttribute("count", count);
					
		%>
				<table align="center" style="padding: 15px; margin-bottom: 15px;">
				<tr>
					<td align="left" style="font-size: 20px;padding-bottom: 20px;">Total Bookings for <b>${name}:</b></td>
					<td align="center" style="font-size: 20px; padding-bottom: 20px;">${count}</td>
				</tr>
				<tr>
					<td align="left" style="font-size: 20px;">Total Sales:</td>
					<td align="center" style="font-size: 20px;">$${total}</td>
				</tr>
				</table>
		<%
					
				}catch(Exception e){
					e.printStackTrace();
				}	
			}
		%>
		
	</div>
	
	<div style="border: 3px solid black;">
		<p style="text-align: center; font-size: 30px;"><b>Sales Report based on Genre:</b></p>
		<form action="SalesReport.jsp">
		<table align="center" style="padding-bottom: 15px;">
			<tr>
				<td>
					<select name="genre">
    						<%
    						
    						try {
    							String genre;
    	    						Class.forName("com.mysql.jdbc.Driver");
    	    				 		conn = DriverManager.getConnection("jdbc:mysql://69.89.31.237:3306/ristiod8_dawgcinema?user=ristiod8_dcuser&password=cinemadb&useSSL=false");
    	    				 		String status = "Established connection";
    	    				 		String query = "Select * from Movie";
    	        					Statement stmt = conn.createStatement();
    	        					ResultSet rs = stmt.executeQuery(query);
    	        					Date current = new Date();
    	        					ArrayList<String> genres = new ArrayList<String>();
    	        					while(rs.next()){
    	        						Date date = rs.getDate("releaseDate");
    	        						genre = rs.getString("Genre");
    	        						boolean exists = false;
    	        						for(int i = 0; i < genres.size(); i++){
    	        							if(genres.get(i).equals(genre)){
    	        								exists = true;
    	        								break;
    	        							}
    	        						}
    	        						if(!exists){
    	        							genres.add(genre);
    	        							
    	        						}
    	        						
    	        						pageContext.setAttribute("genre", genre);
    	        						if(current.after(date) && !exists){
    	        							
    	        						
    	        				%>	
    	        					<option value="${genre}">${genre}</option>
    	        				<%	
    	        						}
    	        					}
    	        					conn.close();
    						}catch(Exception e){
    							e.printStackTrace();
    						}
    						%>
  					</select>
				</td>
				<td>
					<input type="submit" value="Submit" width="40px;" />
				</td>
			</tr>
		</table>
		</form>
		
		<%
			if(request.getParameter("genre") != null){
				String genreSelected = request.getParameter("genre");
				pageContext.setAttribute("chosenGenre", genreSelected);
				try{
					Class.forName("com.mysql.jdbc.Driver");
				 	conn = DriverManager.getConnection("jdbc:mysql://69.89.31.237:3306/ristiod8_dawgcinema?user=ristiod8_dcuser&password=cinemadb&useSSL=false");
				 	String status = "Established connection";
				 	String findTitle = "Select * from Movie where Genre = '" + genreSelected +  "'";
				 	Statement s = conn.createStatement();
				 	ResultSet temp = s.executeQuery(findTitle);
				 	ArrayList<String> idList = new ArrayList<String>();
				 	while(temp.next()){
				 		String id = temp.getString("MovieId");
				 		idList.add(id);
				 	}
				 	String query = "Select * from Booking";
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(query);
					double total = 0.0;
					int count = 0;
					while(rs.next()){
						if(idList.contains(rs.getString("MovieId"))){
							count++;
							double sub = rs.getDouble("TotalPrice");
							total = total + sub;
						}
					}
					DecimalFormat df = new DecimalFormat("#.00");
					pageContext.setAttribute("total", df.format(total));
					pageContext.setAttribute("count", count);
					
		%>
				<table align="center" style="padding: 15px; margin-bottom: 15px;">
				<tr>
					<td align="left" style="font-size: 20px;padding-bottom: 20px;">Total Bookings for Genre <b>${chosenGenre}:</b></td>
					<td align="center" style="font-size: 20px; padding-bottom: 20px;">${count}</td>
				</tr>
				<tr>
					<td align="left" style="font-size: 20px;">Total Sales:</td>
					<td align="center" style="font-size: 20px;">$${total}</td>
				</tr>
				</table>
		<%
					
				}catch(Exception e){
					e.printStackTrace();
				}	
			}
		%>
	</div>

</body>

</html>