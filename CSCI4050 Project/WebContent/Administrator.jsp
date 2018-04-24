<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.sql.*"%>
<%@page import="java.util.Date"%>
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
    function report(){
		window.location.href = "SalesReport.jsp";
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
    
    <div id="addMovie" style="border: 3px solid black;">
    		<p class="register">Add Movie</p>
    		<form action="AddMovieController" method="post" id="form" style="margin-bottom: 30px;">
        <table class="registerBox" align="center">
            <caption class="boxTitle">Enter Movie Info</caption>
            <tr>
                <td class="boxContent">Title:</td>

            </tr>
            <tr>
                <td><input type="text" size="60" name="title"></td>
            </tr>
            <tr>
                <td class="boxContent">Genre:</td>

            </tr>
            <tr>
                <td><input type="text" size="60" name="genre"></td>
            </tr>
            <tr>
                <td class="boxContent">Cast:</td>

            </tr>
            <tr>
                <td><input type="text" size="60" name="cast"></td>
            </tr>
            <tr>
                <td class="boxContent">Director:</td>

            </tr>
            <tr>
                <td><input type="text" size="60" name="director"></td>
            </tr>
            <tr>
                <td class="boxContent">Producer:</td>

            </tr>
            <tr>
                <td><input type="text" size="60" name="producer"></td>
            </tr>
            <tr>
                <td class="boxContent">Description:</td>

            </tr>
            <tr>
                <td><input type="text" size="60" name="description"></td>
            </tr>
            <tr>
                <td class="boxContent">Trailer Picture Link:</td>

            </tr>
            <tr>
                <td><input type="text" size="60" name="picture"></td>
            </tr>
            <tr>
                <td class="boxContent">Trailer Video:</td>

            </tr>
            <tr>
                <td><input type="text" size="60" name="video"></td>
            </tr>
            <tr>
                <td class="boxContent">MpaaRating:</td>

            </tr>
            <tr>
                <td><input type="text" size="60" name="rating"></td>
            </tr>
            <tr>
                <td class="boxContent">Release Date:</td>
            </tr>
            <tr>
                <td><input type="date" size="100" name="releaseDate" style="font-size: 15px;"></td>
            </tr>
            <tr>
                <td class="boxContent">Expiration Date</td>
            </tr>
            <tr>
                <td><input type="date" size="100" name="expiration" style="font-size: 15px;"></td>
            </tr>
            <tr style="height: 50px;">
                <td align="center">
                    <button style="font-size: 20px; margin-top: 10px; width: 400px; cursor:pointer;" form="form">Submit</button>
                  
                </td>
            </tr>
        </table>
        </form>
    	
    	</div>
    	
    	<div id="addMovieTime" style="border: 3px solid black;">
    		<p class="register">Add Movie Time</p>
    		<form id="addTime" method="post" action="AddTimeController">
    		<table class="registerBox" align="center" style="margin-bottom: 30px;">
    			<tr>
    				<td>
    					<select name="movieTitle" form="addTime" style="font-size: 20px; margin-bottom: 15px;margin-top: 15px;">
    					<%
    						Connection conn;
    						try{
    							Class.forName("com.mysql.jdbc.Driver");
    						 	conn = DriverManager.getConnection("jdbc:mysql://69.89.31.237:3306/ristiod8_dawgcinema?user=ristiod8_dcuser&password=cinemadb&useSSL=false");
    						 	String status = "Established connection";
    						 	String query = "Select * from Movie";
    							Statement stmt = conn.createStatement();
    							ResultSet rs = stmt.executeQuery(query);
    							while(rs.next()){
    								String title = rs.getString("Title");
    								pageContext.setAttribute("title", title);
    					%>
    						<option value="${title}">${title}</option>
    					<%
    							}
    					%>
    					</select>
    				</td>
    			</tr>
    				<%
    						}catch(Exception e){
    							e.printStackTrace();
    						}
    				
    				%>
    			<tr>
    				<td align="center">
    					<input type="datetime-local" name="dateAndTime" style="font-size: 20px; margin-bottom: 15px;" />
    				</td>
    			</tr>
    			<tr>
    				<td align="center">
    					<input type="submit" value="Add Time" style="font-size: 20px; margin-bottom: 15px;" />
    				</td>
    			</tr>
    		</table>
    		</form>
    	
    	</div>
    	
    	
    
    <div id="deleteMovie" style="border: 3px solid black;">
    		<p class="register">Delete Movie</p>
    		<table class="registerBox" align="center">
            <caption class="boxTitle">Movie List</caption>
            <%
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
			 	conn = DriverManager.getConnection("jdbc:mysql://69.89.31.237:3306/ristiod8_dawgcinema?user=ristiod8_dcuser&password=cinemadb&useSSL=false");
			 	String status = "Established connection";
			 	String query = "Select * from Movie";
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				while(rs.next()){
					Date today = new Date();
					Date rDate = rs.getDate("releaseDate");
					Date eDate = rs.getDate("expiration");
			%>
				<tr>
				<td>
				<%
					if(today.before(eDate)){
						out.print(rs.getString("title"));
					}
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
        <div></div>
        <form action="DeleteMovieController" method="post">
        		<table align="center">
        			<tr>
        				<td align="center">Enter Movie Name to be deleted: <input type="text" name="movie" style="width: 300px; margin-top: 30px; margin-bottom: 30px"/><td>
        				<td align="center"><input type="submit" value="Delete" style="font-size: 20px;"/></td>
        			</tr>
        		</table>
        </form>
    </div>
    
    <div id="updateMovie" style="border: 3px solid black;">
    		<p class="register">Update Movie</p>
    		<table class="registerBox" align="center" style="max-width: 100%; margin-bottom: 30px;">
    		<tr>
    			<th>MovieId</th>
    			<th>Title</th>
    			<th>Genre</th>
    			<th>Cast</th>
    			<th>Director</th>
    			<th>Producer</th>
    			<th>Description</th>
    			<th>Trailer Picture</th>
    			<th>Trailer Video</th>
    			<th>Rating</th>
    			<th>Release</th>
    			<th>Expiration</th>
    			<th>Update Button</th>
    		
    		</tr>
    		<%
    			try{
    				Class.forName("com.mysql.jdbc.Driver");
    			 	conn = DriverManager.getConnection("jdbc:mysql://69.89.31.237:3306/ristiod8_dawgcinema?user=ristiod8_dcuser&password=cinemadb&useSSL=false");
    			 	String status = "Established connection";
    			 	String query = "Select * from Movie";
    				Statement stmt = conn.createStatement();
    				ResultSet rs = stmt.executeQuery(query);
    				while(rs.next()){
    					String movieId = rs.getString("MovieId");
    					String title = rs.getString("Title");
    					String genre = rs.getString("Genre");
    					String cast = rs.getString("Cast");
    					String director = rs.getString("Director");
    					String producer = rs.getString("Producer");
    					String description = rs.getString("Description");
    					String picture = rs.getString("TrailerPicture");
    					String video = rs.getString("TrailerVideo");
    					String rating = rs.getString("MpaaRating");
    					String release = rs.getString("releaseDate");
    					String expiration = rs.getString("expiration");
    					request.setAttribute("movieId", Integer.parseInt(movieId));
    					pageContext.setAttribute("movieId", movieId);
					pageContext.setAttribute("title", title);
					pageContext.setAttribute("genre", genre);
					pageContext.setAttribute("cast", cast);
					pageContext.setAttribute("director", director);
					pageContext.setAttribute("producer", producer);
					pageContext.setAttribute("description", description);
					pageContext.setAttribute("picture", picture);
					pageContext.setAttribute("video", video);
					pageContext.setAttribute("rating", rating);
					pageContext.setAttribute("release", release);
					pageContext.setAttribute("expiration", expiration);
    		%>
    			<tr>
    				<td align="center">${movieId}</td>
    				<input type="text" name="movieId" value="${movieId}" form="${movieId}" style="display:none"/>
    				<td align="center"><input type="text" name="title" value="${title}" form="${movieId}" /></td>
    				<td align="center"><input style="width:40px;" type="text" name="genre" value="${genre}" form="${movieId}" /></td>
    				<td align="center"><input type="text" name="cast" value="${cast}" form="${movieId}" /></td>
    				<td align="center"><input type="text" name="director" value="${director}" form="${movieId}" /></td>
    				<td align="center"><input type="text" name="producer" value="${producer}" form="${movieId}" /></td>
    				<td align="center"><input type="text" name="description" value="${description}" form="${movieId}" /></td>
    				<td align="center"><input type="text" name="picture" value="${picture}" form="${movieId}" /></td>
    				<td align="center"><input type="text" name="video" value="${video}" form="${movieId}" /></td>
    				<td align="center"><input style="width:30px;" type="text" name="rating" value="${rating}" form="${movieId}" /></td>
    				<td align="center"><input style="width:70px;" type="text" name="release" value="${release}" form="${movieId}" /></td>
    				<td align="center"><input style="width:70px;" type="text" name="expiration" value="${expiration}" form="${movieId}" /></td>
    				<td align="center"><form id="${movieId}" action="UpdateMovieController" method = "post"><input type="submit" value="Update"/></form></td>
    				
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
    
    <div id="updateType" style="border: 3px solid black;">
    		<p class="register">Change Account Type</p>
    		<table class="registerBox" align="center" style="border-collapse: collapse; margin-bottom: 30px;">
            <caption class="boxTitle">Current Users</caption>
            <tr>
            	<th style="font-size: 20px; padding: 15px; border: 3px solid black;" align="center">UserId</th>
            	<th style="font-size: 20px; padding: 15px; border: 3px solid black;" align="center">First Name</th>
            	<th style="font-size: 20px; padding: 15px; border: 3px solid black;" align="center">Last Name</th>
            	<th style="font-size: 20px; padding: 15px;border: 3px solid black;" align="center">TypeId</th>
            </tr>
            <%
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
			 	conn = DriverManager.getConnection("jdbc:mysql://69.89.31.237:3306/ristiod8_dawgcinema?user=ristiod8_dcuser&password=cinemadb&useSSL=false");
			 	String status = "Established connection";
			 	String query = "Select * from User";
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				while(rs.next()){
			%>
				<tr>
				<td style="font-size: 20px; padding: 15px; border: 3px solid black;" align="center">
				<%
					out.print(rs.getString("UserId"));
				%>
				</td>
				<td style="font-size: 20px; padding: 15px; border: 3px solid black;" align="center">
				<%
					out.print(rs.getString("FirstName"));
				%>
				</td>
				<td style="font-size: 20px; padding: 15px; border: 3px solid black;" align="center">
				<%
					out.print(rs.getString("LastName"));
				%>
				</td>
				<td style="font-size: 20px; padding: 15px; border: 3px solid black;" align="center">
				<%
					out.print(rs.getString("TypeId"));
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
        <form action="UpdateTypeController" method="post" style="margin-bottom: 30px;">
        		<table align="center">
        			<tr>
        				<td>UserId to be changed: <input type="number" name="userId" min="1" max="1000" style="margin-top: 30px; margin-bottom: 10px"/></td>
        			</tr>
        			<tr>
        				<td>New TypeId: <input type="number" min="1" max="4" name="typeId" style="margin-bottom: 10px;"></td>
        			</tr>
        			<tr>
        				<td><input type="submit" value="Change" /></td>
        			</tr>
        		</table>
        </form>
        <div></div>
    </div>
    
    <div id="updateStatus" style="border: 3px solid black;">
    		<p class="register">Change Status </p>
    		<table class="registerBox" align="center" style="border-collapse: collapse; margin-bottom: 30px;">
            <caption class="boxTitle">Current Users</caption>
            <tr>
            	<th style="font-size: 20px; padding: 15px; border: 3px solid black;" align="center">UserId</th>
            	<th style="font-size: 20px; padding: 15px; border: 3px solid black;" align="center">First Name</th>
            	<th style="font-size: 20px; padding: 15px; border: 3px solid black;" align="center">Last Name</th>
            	<th style="font-size: 20px; padding: 15px;border: 3px solid black;" align="center">Status</th>
            </tr>
            <%
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
			 	conn = DriverManager.getConnection("jdbc:mysql://69.89.31.237:3306/ristiod8_dawgcinema?user=ristiod8_dcuser&password=cinemadb&useSSL=false");
			 	String status = "Established connection";
			 	String query = "Select * from User";
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				while(rs.next()){
			%>
				<tr>
				<td style="font-size: 20px; padding: 15px; border: 3px solid black;" align="center">
				<%
					out.print(rs.getString("UserId"));
				%>
				</td>
				<td style="font-size: 20px; padding: 15px; border: 3px solid black;" align="center">
				<%
					out.print(rs.getString("FirstName"));
				%>
				</td>
				<td style="font-size: 20px; padding: 15px; border: 3px solid black;" align="center">
				<%
					out.print(rs.getString("LastName"));
				%>
				</td>
				<td style="font-size: 20px; padding: 15px; border: 3px solid black;" align="center">
				<%
					out.print(rs.getString("Status"));
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
        <form action="UpdateStatusController" method="post" style="margin-bottom: 30px;">
        		<table align="center">
        			<tr>
        				<td>UserId to be changed: <input type="number" name="userId" min="1" max="1000" style="margin-top: 30px; margin-bottom: 10px"/></td>
        			</tr>
        			<tr>
        				<td>New Status: <input type="number" min="0" max="2" name="status" style="margin-bottom: 10px;"></td>
        			</tr>
        			<tr>
        				<td><input type="submit" value="Change" /></td>
        			</tr>
        		</table>
        </form>
        <div></div>
    </div>
    
    <div id="deleteUser" style="border: 3px solid black;">
    		<p class="register">Delete User</p>
    		<table class="registerBox" align="center" style="border-collapse: collapse; margin-bottom: 30px;">
            <caption class="boxTitle">Current Users</caption>
            <tr>
            	<th style="font-size: 20px; padding: 15px; border: 3px solid black;" align="center">UserId</th>
            	<th style="font-size: 20px; padding: 15px; border: 3px solid black;" align="center">First Name</th>
            	<th style="font-size: 20px; padding: 15px; border: 3px solid black;" align="center">Last Name</th>
            	<th style="font-size: 20px; padding: 15px;border: 3px solid black;" align="center">Status</th>
            </tr>
            <%
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
			 	conn = DriverManager.getConnection("jdbc:mysql://69.89.31.237:3306/ristiod8_dawgcinema?user=ristiod8_dcuser&password=cinemadb&useSSL=false");
			 	String status = "Established connection";
			 	String query = "Select * from User";
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				while(rs.next()){
			%>
				<tr>
				<td style="font-size: 20px; padding: 15px; border: 3px solid black;" align="center">
				<%
					out.print(rs.getString("UserId"));
				%>
				</td>
				<td style="font-size: 20px; padding: 15px; border: 3px solid black;" align="center">
				<%
					out.print(rs.getString("FirstName"));
				%>
				</td>
				<td style="font-size: 20px; padding: 15px; border: 3px solid black;" align="center">
				<%
					out.print(rs.getString("LastName"));
				%>
				</td>
				<td style="font-size: 20px; padding: 15px; border: 3px solid black;" align="center">
				<%
					out.print(rs.getString("Status"));
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
        <form action="DeleteUserController" method="post" style="margin-bottom: 30px;">
        		<table align="center">
        			<tr>
        				<td>UserId to be deleted: <input type="number" name="userId" min="1" max="1000" style="margin-top: 30px; margin-bottom: 10px"/></td>
        			</tr>
        			<tr>
        				<td><input type="submit" value="Delete" /></td>
        			</tr>
        		</table>
        </form>
        <div></div>
    </div>
    
    
    <div id="managePromotions" style="border: 3px solid black;">
    		<div>
        		<p class="register">Manage Promotions</p>
        		<p class="promotitle">Current Promotions</p>
    		</div>
    		<div>
        		<table align="center" style="border: 3px solid black; border-collapse: collapse;">
        		    <tr>
                		<th class="tableCell">
                    		<p style="text-align: center; font-weight: 900;">Promo Code</p>
                		</th>
                		<th class="tableCell">
                    		<p style="text-align: center; font-weight: 900;">Discount</p>
                		</th>
                		<th class="tableCell">
                    		<p style="text-align: center; font-weight: 900;">Expiration Date</p>
                		</th>
            		</tr>
            		<%
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
			 	conn = DriverManager.getConnection("jdbc:mysql://69.89.31.237:3306/ristiod8_dawgcinema?user=ristiod8_dcuser&password=cinemadb&useSSL=false");
			 	String status = "Established connection";
			 	String query = "Select * from Promotion";
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				while(rs.next()){
			%>
				<tr>
				<td style="font-size: 20px; padding: 15px; border: 3px solid black;" align="center">
				<%
					out.print(rs.getString("Code"));
				%>
				</td>
				<td style="font-size: 20px; padding: 15px; border: 3px solid black;" align="center">
				<%
					out.print(rs.getString("PercentDiscount"));
				%>
				</td>
				<td style="font-size: 20px; padding: 15px; border: 3px solid black;" align="center">
				<%
					out.print(rs.getString("ExpDate"));
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
        		<div>
        			<table align="center">
        				<tr>
        					<td align="center" style="padding: 15px;"><button style="font-size: 20px;" onclick="add();">Add</button></td>
        					<td align="center" style="padding: 15px;"><button style="font-size: 20px;" onclick="update();">Update</button></td>
        					<td align="center" style="padding: 15px;"><button style="font-size: 20px;" onclick="deletePromo();">Delete</button></td>
        				</tr>
        			</table>
        		</div>
    		</div>
    		<div id="add" style="display: none;">
    			<form id="addPromo" action="AddPromoController" method="post">
        		<table align="center">
        			<tr>
        				<td>Add Promotion by Entering the Information Below</td>
        			</tr>
            		<tr>
                		<td>
                    		<p>Enter Promo Code: <input type="text" name="code" width="30px" size="23px;"></p>
                		</td>
            		</tr>
            		<tr>
                		<td>
                    		<p>Discount: <input type="text" name="discount" size="32px;"></p>
                		</td>
            		</tr>
            		<tr>
                		<td>
                    		<p>Enter Expiration Date (yyyy-mm-dd): <input type="date" name="expiration" width="30px"></p>
                		</td>
            		</tr>
            		<tr>
            			<td align="center">
            				<input type="submit" value="Add" style="font-size: 30px; width: 80px;" />
            			</td>
            		</tr>
        		</table>
        		</form>
    		</div>
    		<div id="update"  style="display: none;">
    			<form id="updatePromo" action="UpdatePromoController" method="post">
        		<table align="center">
        			<tr>
        				<td align="center">Update Promotion by Entering the Promo Code and Updated Info</td>
        			</tr>
            		<tr>
                		<td>
                    		<p>Enter Promo Code: <input type="text" name="code" width="30px" size="23px;"></p>
                		</td>
            		</tr>
            		<tr>
                		<td>
                    		<p>Discount: <input type="text" name="discount" size="32px;"></p>
                		</td>
            		</tr>
            		<tr>
                		<td>
                    		<p>Enter Expiration Date (yyyy-mm-dd): <input type="date" name="expiration" width="30px"></p>
                		</td>
            		</tr>
            		<tr>
            			<td align="center">
            				<input type="submit" value="Update" style="font-size: 30px; width: 80px;" />
            			</td>
            		</tr>
        		</table>
        		</form>
    		</div>
    		<div id="delete" style="display: none;">
    			<form id="deletePromo" action="DeletePromoController" method="post">
        		<table align="center">
        			<tr>
        				<td align="center">Delete Promotion by Entering the Code Below</td>
        			</tr>
            		<tr>
                		<td>
                    		<p>Enter Promo Code: <input type="text" name="code" width="30px" size="23px;"></p>
                		</td>
            		</tr>
            		<tr>
            			<td align="center">
            				<input type="submit" value="Delete" style="font-size: 30px; width: 80px;" />
            			</td>
            		</tr>
        		</table>
        		</form>
    		</div>
    </div>
    
    <div id="adjustPrices" style="border: 3px solid black; padding-bottom: 30px;">
    		<p class="register">Adjust Prices</p>
    		<table align="center" style="border: 3px solid black; border-collapse: collapse; margin-bottom: 15px;">
    			<%
    				try{
    					Class.forName("com.mysql.jdbc.Driver");
    				 	conn = DriverManager.getConnection("jdbc:mysql://69.89.31.237:3306/ristiod8_dawgcinema?user=ristiod8_dcuser&password=cinemadb&useSSL=false");
    				 	String status = "Established connection";
    				 	String query = "Select * from Price";
    				 	Statement stmt = conn.createStatement();
    					ResultSet rs = stmt.executeQuery(query);
    					while(rs.next()){
    						String type = rs.getString("type");
    						double price = rs.getDouble("price");
    						pageContext.setAttribute(type, price);
    						
    					}
    					conn.close();
    				}catch(Exception e){
    					e.printStackTrace();
    				}		
    			
    			%>
    	
    			<tr>
    				<td valign="middle"><p>Booking Fee: $</p></td>
    				<td valign="middle" style="padding-top: 5px;"><form action="PricesController" method="post"><input type="text" name="booking" value="${bookingFee}" /> <input type="submit" value="Change" /></form></td>
    			</tr>
    			<tr>
    				<td valign="middle"><p>Parking Fee: $</p></td>
    				<td valign="middle"><form action="PricesController" method="post"><input type="text" name="parking" value="${parkingFee}" /> <input type="submit" value="Change" /></form></td>
    			</tr>
    			<tr>
    				<td valign="middle"><p>Child Ticket: $</p></td>
    				<td valign="middle"><form action="PricesController" method="post"><input type="text" name="child" value="${childPrice}" /> <input type="submit" value="Change" /></form></td>
    			</tr>
    			<tr>
    				<td valign="middle"><p>Adult Ticket: $</p></td>
    				<td valign="middle"><form action="PricesController" method="post"><input type="text" name="adult" value="${adultPrice}" /> <input type="submit" value="Change" /></form></td>
    			</tr>
    			<tr>
    				<td valign="middle"><p>Senior Ticket: $</p></td>
    				<td valign="middle"><form action="PricesController" method="post"><input type="text" name="senior" value="${seniorPrice}" /> <input type="submit" value="Change" /></form></td>
    			</tr>
    		</table>
    	</div>
    	<div id="sales" style="border-top: 3px solid black; padding-bottom: 30px;">
    		<p class="register">View Sales Reports</p>
    		<p style="text-align: center; font-size: 25px;">Click <b onclick="report();" style="cursor:pointer;">HERE</b> to view Sales Report</p>
    	</div>
</body>

</html>