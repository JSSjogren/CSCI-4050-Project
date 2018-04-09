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
 
<div id="filterByGenre">
</div>  

<div id="filterByTitleSearch">
</div>
  
<div id="filterByDate">
</div>
  
</body>

</html>