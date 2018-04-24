<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
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
                	<p style="text-align: right; cursor: pointer;" onclick="home();">Return to <b>Home</b></p>
                </td>
            </tr>
           </table>
    </div>
    <div>
    <h1 align="center">Select Movie</h1>
    </div>
  
  	<div id="filterByGenre" style="margin-top: 50px; border-top: 3px solid black;">
		<p style="text-align: center; font-size: 20px;">Select Movie By Category/Genre</p>
		<form action="MovieSelect.jsp">
		<table align="center">
			<tr>
				<td>
					<select name="genre">
    						<%
    						
    						try {
    							Connection conn;
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
			String pickedGenre = request.getParameter("genre");
			pageContext.setAttribute("pickedGenre", pickedGenre);
		
		%>
			<table align="center" style="border-collapse: collapse;">
				<tr>
					<th align="center" style="border: 3px solid black;">Movie Cover</th>
					<th align="center" style="border: 3px solid black;">Title</th>
					<th align="center" style="border: 3px solid black;">Rating</th>
					<th align="center" style="border: 3px solid black;">Trailer</th>
					<th align="center" style="border: 3px solid black;">Book Now!</th>
				</tr>
				<%
				try {
					Connection conn;
					Class.forName("com.mysql.jdbc.Driver");
				 	conn = DriverManager.getConnection("jdbc:mysql://69.89.31.237:3306/ristiod8_dawgcinema?user=ristiod8_dcuser&password=cinemadb&useSSL=false");
				 	String status = "Established connection"; 	
				 	String query = "Select * from Movie where Genre = '" + pickedGenre + "'";
				 	Statement stmt = conn.createStatement();
				 	ResultSet rs = stmt.executeQuery(query);
				 	Date current = new Date();
    					
    					while(rs.next()){
    						Date date = rs.getDate("releaseDate");
    						Date eDate = rs.getDate("expiration");
    						if(current.after(date) && current.before(eDate)){
    							String title = rs.getString("Title");
    					 		String rating = rs.getString("MpaaRating");
    					 		String trailer = rs.getString("TrailerVideo");
    					 		String picture = rs.getString("TrailerPicture");
    					 		pageContext.setAttribute("title", title);
    	    						pageContext.setAttribute("rating", rating);
    	    						pageContext.setAttribute("trailer", trailer);
    	    						pageContext.setAttribute("picture", picture);
				%>
					<tr>
						<td align="center" style="border: 3px solid black; padding: 15px;">
							<img src="${picture}" height="300" width="200">
						</td>
						<td align="center" style="border: 3px solid black;  padding: 15px; font-size: 20px; max-width: 200px;">${title}</td>
						<td align="center" style="border: 3px solid black;  padding: 15px; font-size: 20px;">${rating}</td>
						<td align="center" style="border: 3px solid black;  padding: 15px;">
							<iframe width="420" height="345" src="https://www.youtube.com/embed/${trailer}">
						</iframe>
						</td>
						<td align="center" style="border: 3px solid black;  padding: 15px;">
							<form action="MovieSelectionController" method="post">
								<input type="text" name="movieSelected" value="${title}" style="display:none;"/>
								<input type="submit" value="Book This Movie" />
							</form>
						</td>						
					</tr>
				
				<%
    						}
    					}
				conn.close();
				}catch(Exception e){
					e.printStackTrace();
				}
				%>
			</table>
		<%
		}
		%>
	</div>  





	<div id="filterByTitleSearch" style="margin-top: 50px; border-top: 3px solid black;">
		<p style="text-align: center; font-size: 20px;">Select Movie By Title Search</p>
		<form action="MovieSelect.jsp">
		<table align="center">
			<tr>
				<td>
					<p>Enter Keyword:</p>
				</td>
				<td>
					<input type="text" name="keyword" width="120px;"/>
				</td>
				<td>
					<input type="submit" value="Submit" width="40px;"/>
				</td>
			</tr>
		</table>
		</form>
		
		<%
		if(request.getParameter("keyword") != null){
			String keyword = request.getParameter("keyword");
			pageContext.setAttribute("keyword", keyword);
		
		%>
	
		<table align="center" style="border-collapse: collapse;">
				<%
				try {
					Connection conn;
					Class.forName("com.mysql.jdbc.Driver");
				 	conn = DriverManager.getConnection("jdbc:mysql://69.89.31.237:3306/ristiod8_dawgcinema?user=ristiod8_dcuser&password=cinemadb&useSSL=false");
				 	String status = "Established connection"; 	
				 	String query = "Select * from Movie where Title LIKE '%" + keyword + "%'";
				 	Statement stmt = conn.createStatement();
				 	Statement s = conn.createStatement();
				 	ResultSet check = s.executeQuery(query);
				 	boolean empty = true;
				 	if(check.next()){
				 		empty = false;
				 	}
				 	
				 	if(!empty){
				 		
				 	%>
				 	<tr>
					<th align="center" style="border: 3px solid black;">Movie Cover</th>
					<th align="center" style="border: 3px solid black;">Title</th>
					<th align="center" style="border: 3px solid black;">Rating</th>
					<th align="center" style="border: 3px solid black;">Trailer</th>
					<th align="center" style="border: 3px solid black;">Book Now!</th>
					</tr>
				 	
				 	
				 	<% 
				 		
				 		
				 	}
				 	 	
				 	ResultSet rs = stmt.executeQuery(query);
				 	Date current = new Date();
    					
    					while(rs.next()){
    						Date date = rs.getDate("releaseDate");
    						Date eDate = rs.getDate("expiration");
    						if(current.after(date) && current.before(eDate)){
    							String title = rs.getString("Title");
    					 		String rating = rs.getString("MpaaRating");
    					 		String trailer = rs.getString("TrailerVideo");
    					 		String picture = rs.getString("TrailerPicture");
    					 		pageContext.setAttribute("title", title);
    	    						pageContext.setAttribute("rating", rating);
    	    						pageContext.setAttribute("trailer", trailer);
    	    						pageContext.setAttribute("picture", picture);
				%>
					<tr>
						<td align="center" style="border: 3px solid black; padding: 15px;">
							<img src="${picture}" height="300" width="200">
						</td>
						<td align="center" style="border: 3px solid black;  padding: 15px; font-size: 20px; max-width: 200px;">${title}</td>
						<td align="center" style="border: 3px solid black;  padding: 15px; font-size: 20px;">${rating}</td>
						<td align="center" style="border: 3px solid black;  padding: 15px;">
							<iframe width="420" height="345" src="https://www.youtube.com/embed/${trailer}">
						</iframe>
						</td>
						<td align="center" style="border: 3px solid black;  padding: 15px;">
							<form action="MovieSelectionController" method="post">
								<input type="text" name="movieSelected" value="${title}" style="display:none;"/>
								<input type="submit" value="Book This Movie" />
							</form>
						</td>						
					</tr>
				
				<%
    						}
    					}
    					
    				%>
    				</table>
    				
    				<%
    					
    					if(empty){
    				%>	
    				<p style="text-align: center; font-size: 20px;">No Results</p>	
    				<%		
    					}
				conn.close();
				}catch(Exception e){
					e.printStackTrace();
				}
				%>
			</table>
	<%
		}
	%>
		
		
	</div>
  
  
  
  
  
	<div id="filterByDate" style="margin-top: 50px; border-top: 3px solid black;">
		<p style="text-align: center; font-size: 20px;">Select Movie By Show Date:</p>
		<form action="MovieSelect.jsp">
		<table align="center">
			<tr>
				<td>
					<p>Pick Day:</p>
				</td>
				<%
					Date today = new Date();
					DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					pageContext.setAttribute("today",today.toString());
					
				%>
				<td>
					<input type="date" name="datePicked" min="${today}" max="2018-06-01" width="120px;"/>
				</td>
				<td>
					<input type="submit" value="Submit" width="40px;"/>
				</td>
			</tr>
		</table>
		</form>
	</div>
	<div>
		<%
		if(request.getParameter("datePicked") != null){
			String datePicked = request.getParameter("datePicked");
			pageContext.setAttribute("datePicked", datePicked);
		
		%>
			
			<table align="center" style="border-collapse: collapse;">
				<tr>
					<th align="center" style="border: 3px solid black;">Movie Cover</th>
					<th align="center" style="border: 3px solid black;">Title</th>
					<th align="center" style="border: 3px solid black;">Rating</th>
					<th align="center" style="border: 3px solid black;">Trailer</th>
					<th align="center" style="border: 3px solid black;">Book Now!</th>
				</tr>
				<%
				try {
					Connection conn;
					Class.forName("com.mysql.jdbc.Driver");
				 	conn = DriverManager.getConnection("jdbc:mysql://69.89.31.237:3306/ristiod8_dawgcinema?user=ristiod8_dcuser&password=cinemadb&useSSL=false");
				 	String status = "Established connection"; 	
				 	String query = "Select * from Movie";
				 	Statement stmt = conn.createStatement();
				 	ResultSet rs = stmt.executeQuery(query);
				 	df = new SimpleDateFormat("yyyy-MM-dd");
				 	Date current = new Date();
				 	try {
				 	    current = df.parse(datePicked);
				 	} catch (ParseException e) {
				 	    e.printStackTrace();
				 	}
    					
    					while(rs.next()){
    						Date rdate = rs.getDate("releaseDate");
    						Date eDate = rs.getDate("expiration");
    						if(current.after(rdate) && current.before(eDate)){
    							String title = rs.getString("Title");
    					 		String rating = rs.getString("MpaaRating");
    					 		String trailer = rs.getString("TrailerVideo");
    					 		String picture = rs.getString("TrailerPicture");
    					 		pageContext.setAttribute("title", title);
    	    						pageContext.setAttribute("rating", rating);
    	    						pageContext.setAttribute("trailer", trailer);
    	    						pageContext.setAttribute("picture", picture);
				%>
					<tr>
						<td align="center" style="border: 3px solid black; padding: 15px;">
							<img src="${picture}" height="300" width="200">
						</td>
						<td align="center" style="border: 3px solid black;  padding: 15px; font-size: 20px; max-width: 200px;">${title}</td>
						<td align="center" style="border: 3px solid black;  padding: 15px; font-size: 20px;">${rating}</td>
						<td align="center" style="border: 3px solid black;  padding: 15px;">
							<iframe width="420" height="345" src="https://www.youtube.com/embed/${trailer}">
						</iframe>
						</td>
						<td align="center" style="border: 3px solid black;  padding: 15px;">
							<form action="MovieSelectionController" method="post">
								<input type="text" name="movieSelected" value="${title}" style="display:none;"/>
								<input type="submit" value="Book This Movie" />
							</form>
						</td>						
					</tr>
				
				<%
    						}
    					}
				conn.close();
				}catch(Exception e){
					e.printStackTrace();
				}
				%>
			</table>
		
		
		<%
			
		}
		%>
	</div>
	
</body>

</html>






