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
    
    <div id="addMovie" style="border: 3px solid black;">
    		<p class="register">Add Movie</p>
    		<form action="" method="get" id="form" style="margin-bottom: 30px;">
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
                <td><input type="password" size="60" name="director"></td>
            </tr>
            <tr>
                <td class="boxContent">Producer:</td>

            </tr>
            <tr>
                <td><input type="password" size="60" name="producer"></td>
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
                <td class="boxContent">Release Date (yyyy-mm-dd):</td>
            </tr>
            <tr>
                <td><input type="text" size="60" name="releaseDate"></td>
            </tr>
            <tr style="height: 50px;">
                <td>
                    <button style="font-size: 20px; margin-top: 10px; width: 400px; margin-left: 15px; cursor:pointer;" form="form">Submit</button>
                  
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
            Connection conn;
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
			 	conn = DriverManager.getConnection("jdbc:mysql://69.89.31.237:3306/ristiod8_dawgcinema?user=ristiod8_dcuser&password=cinemadb&useSSL=false");
			 	String status = "Established connection";
			 	String query = "Select * from Movie";
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				while(rs.next()){
			%>
				<tr>
				<td>
				<%
					out.print(rs.getString("title"));
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
        <form action="" method="get" style="margin-left: 400px;">
        		Enter Movie Name to be deleted: <input type="text" name="movie" style="width: 300px; margin-top: 30px; margin-bottom: 30px"/>
        		<input type="submit" value="Delete" />
        </form>
    </div>
    
    <div id="updateMovie" style="border: 3px solid black;">
    		<p class="register">Update Movie</p>
    		<form action="" method="post" id="form2" style="margin-bottom: 30px;">
        <table class="registerBox" align="center">
            <caption class="boxTitle">Enter Updated Movie Info</caption>
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
                <td><input type="password" size="60" name="director"></td>
            </tr>
            <tr>
                <td class="boxContent">Producer:</td>

            </tr>
            <tr>
                <td><input type="password" size="60" name="producer"></td>
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
                <td class="boxContent">Release Date (yyyy-mm-dd):</td>
            </tr>
            <tr>
                <td><input type="text" size="60" name="releaseDate"></td>
            </tr>
            <tr style="height: 50px;">
                <td>
                    <button style="font-size: 20px; margin-top: 10px; width: 400px; margin-left: 15px; cursor:pointer;" form="form2">Submit</button>
                  
                </td>
            </tr>
        </table>
        </form>
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
        <form action="" method="get" style="margin-left: 600px; margin-bottom: 30px;">
        		UserId to be changed: <input type="number" name="userId" min="1" max="1000" style="margin-top: 30px; margin-bottom: 10px"/><br>
        		New TypeId: <input type="number" min="1" max="6" name="typeId" style="margin-bottom: 10px;"><br>
        		<input type="submit" value="Change" />
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
        <form action="" method="get" style="margin-left: 600px; margin-bottom: 30px;">
        		UserId to be changed: <input type="number" name="userId" min="1" max="1000" style="margin-top: 30px; margin-bottom: 10px"/><br>
        		New Status: <input type="number" min="0" max="2" name="status" style="margin-bottom: 10px;"><br>
        		<input type="submit" value="Change" />
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
        <form action="" method="get" style="margin-left: 600px; margin-bottom: 30px;">
        		UserId to be deleted: <input type="number" name="userId" min="1" max="1000" style="margin-top: 30px; margin-bottom: 10px"/><br>
        		<input type="submit" value="Delete" />
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
    			<form id="addPromo">
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
    			<form id="updatePromo">
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
    			<form id="deletePromo">
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
</body>

</html>