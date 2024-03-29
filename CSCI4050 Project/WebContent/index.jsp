<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>DDI Home</title>
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
            /*border-bottom: 5px solid black;*/
            margin-top: 0;
            margin-left: 0;
            margin-right: 0;
            background-color: gray;
            border-bottom: 1px groove;
        }
        .tabButton {
            background-color: inherit;
            float: left;
            border: none;
            outline: none;
            cursor: pointer;
            padding: 14px 16px;
            transition: 0.3s;
            font-size: 20px;
            font-weight: 600;
            font-family: avenir,times,serif;
            color: #b0162d;
        }
        .tabButton:hover {
            background-color: darkgray;
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
        
        .movieInfo {
            text-align: center;
            color: whitesmoke
        }
        
    </style>
    <script>
    		function signIn(){
    			window.location.href = "signIn.html";
    		}
    		function food(){
    			window.location.href = "Food.html";
    		}
    		function lots(){
    			window.location.href = "OurLots.html";
    		}
    		function movie(){
    			window.location.href = "MovieBrowse.jsp";
    		}
    		function admin(){
    			window.location.href = "Administrator.jsp";
    		}
    		function profile(){
    			window.location.href = "EditProfile.jsp";
    		}
    		function movieSelect(){
    			window.location.href = "MovieSelect.jsp";
    		}
    </script>
</head>

<body>
    <div class="headerDiv">
        <table style=" width: 100%;">
            <tr>
                <td style="width: 8%"><img src="transparentWebSiteLogo.png" class="logo"></td>
                <td align="left" style="width: 28%;">
                    <h1 class="title" style="margin-right: 0px; margin-left: 0px; padding: 0px;">Dawg Drive-in</h1>
                </td>
                <td style=""><button class="tabButton" onclick="movie();">Movies</button></td>
                <td><button class="tabButton" onclick="lots();">Our Lots</button></td>
                <td><button class="tabButton" onclick="food();">Food & Drink</button></td>
                
                <%
                		if(session != null && session.getAttribute("userId") == null){
                %>
                
                <td><button class="tabButton" onclick="signIn();">Book Tickets</button></td>
                
                <%
                		}
                		else{
                %>
                	 <td><button class="tabButton" onclick="movieSelect();">Book Tickets</button></td>
                	 
                	<%
                		}
                	
                	%>
                
                <%
                		if(session != null && session.getAttribute("rank") != null){
                			int rank = (int) session.getAttribute("rank");
                			if(rank == 4){
                		
                %>
                <td><button class="tabButton" onclick="admin();">Admin</button></td>
                	
                <%
                			}
                		}
                		
                %>
                <td style="width: 300px; font-size: 20px; padding-right: 15px;">
                <%
                		
                %>
                
                    <p style="text-align: right; font-family: avenir, times, serif; padding-right: 15px;">
                    <% 
                			if(session != null && session.getAttribute("firstName") != null){
                				String name = (String) session.getAttribute("firstName");
                				out.print("Hello, ");
                		%>
                		<B onclick="profile();" style="cursor:pointer;">
                		<%
                		out.print(session.getAttribute("firstName"));
                		
                		int rank = (int) session.getAttribute("rank");
                			if(rank!=4){
                		
                		%>
   
                		</B>
                			
                			<span>
                			<!-- <form id="form" method="post" action="SignInController"> -->
                			<a href="LogoutController" accesskey="1" title="" style="color: black; text-decoration: none; cursor:pointer; margin-right: 5px; position: relative; left: -34%; top: 50px;"><B>Logout</B></a>
                    		<!-- <B onclick="signIn();" style="cursor:pointer; margin-right: 5px; position: absolute; left: 1295px; top: 120px;">Log out</B> -->
                    		<!-- </form> -->
                    		</span>
                		<%
                			}
                			else{
                				
                		%>
                			<span>
                			<!-- <form id="form" method="post" action="SignInController"> -->
                			<a href="LogoutController" accesskey="1" title="" style="color: black; text-decoration: none; cursor:pointer; margin-right: 5px; position: relative; left: -35%; top: 50px;">Logout</a>
                    		<!-- <B onclick="signIn();" style="cursor:pointer; margin-right: 5px; position: absolute; left: 1295px; top: 120px;">Log out</B> -->
                    		<!-- </form> -->
                    		</span>
                		
                		
                		<% 
                			
                				
                				
                			}
                		
                	
                			}
                			else{
                		%>
                		Have an account? 
                    <span>
                    <B onclick="signIn();" style="cursor:pointer; margin-right: 5px;">SIGN IN</B>
                    </span>
             		<% 
                			}
             		%>
                  
                    </p>
                </td>
            </tr>
        </table>
    </div>
    <div style="margin-top: 0px; background-color: gray; padding-bottom: 10px; border-bottom: 5px solid black;">
        <p style="text-align: center; font-size: 40px; margin-top: 0px; padding-top: 10px; font-family: avenir,times,serif;">Welcome!</p>
        <p style="text-align: center; font-size: 30px; margin-left: 70px; margin-right: 70px; font-family: avenir, times, serif">Dawg-Drive In is a drive-in movie theater experience! Here you get to see any of our movies from the comfort of your own car in one of our movie lots! All you need is to register for an account or sign-in to book your tickets now.</p>
    </div>
   <div>
        <p style="text-align: center;color: whitesmoke; font-size: 30px; padding-top: 10px; font-family: Apple Chancery, Time, serif;">Featured movies at Dawg Drive-In</p>
        <table align="center">
            <tr>
                <td><img src="gonewiththewindMoviePic.png" style="max-height: 500px; padding: 50px;"></td>
                <td><img src="breakfastclubMoviePic.png" style="max-height: 500px; padding: 50px;"></td>
                <td><img src="starwarsMoviePic.png" style="max-height: 500px; padding: 50px;"></td>
            </tr>
            <tr>
                <td>
                    <p class="movieInfo">Gone With the Wind</p>
                    <p class="movieInfo">Rating: G</p>
                    <p class="movieInfo">Released in 1939</p>
                </td>
                <td> 
                    <p class="movieInfo">Breakfast Club</p>
                    <p class="movieInfo">Rating: R</p>
                    <p class="movieInfo">Released in 1985</p>
                </td>
                <td>
                    <p class="movieInfo">Star Wars: A New Hope</p>
                    <p class="movieInfo">Rating: PG</p>
                    <p class="movieInfo">Released in 1977</p>
                </td>
            </tr>
        </table>
    </div>
    <div class="page-bg"></div>
</body>

</html>