<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>DDI Showtimes</title>
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
            margin-left: 100px;
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
            margin-left: 277px;
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
        <p style="text-align: center;color: whitesmoke; font-size: 30px; padding-top: 10px; font-family: Apple Chancery, Time, serif;">Showtimes</p>
    </div>
    <div>
        <table>
            <tr>
                <td>
                    <img src="forceawakensMoviePic.png" class="moviePic">
                </td>
                <td>
                    <p class="movieTitle">Star Wars: The Force Awakens <span style="margin-left:20px; font-size: 40px; background-color: white;">|</span> <span style="margin-left: 10px; font-size: 20px;"> 2 H 15 MIN </span><span style="margin-left:20px; font-size: 40px; background-color: white;">|</span> <span style="margin-left: 10px; font-size: 20px;"> PG-13 </span></p>
                </td>
            </tr>
        </table>

        <table>
            <tr>
                 <td>
                    <p class="dateTitle">Date: </p>
                </td>
                <td>
                    <input style="margin-left: 40px;" id="eventDate" type="date" min="2018-02-01" name="date">
                </td>
            </tr>
        </table>
        <table style="margin-left: 258px; margin-top: 50px;">
            <tr>
                 <td>
                    <p class="times">8:00 AM</p>
                </td>
                <td>
                    <p class="times">11:00 AM</p>
                </td>
                <td>
                    <p class="times">12:00 AM</p>
                </td>
                <td>
                    <p class="times">2:00 PM</p>
                </td>
                <td>
                    <p class="times">4:00 PM</p>
                </td>
                <td>
                    <p class="times">6:00 PM</p>
                </td>
                <td>
                    <p class="times">8:00 PM</p>
                </td>
                <td>
                    <p class="times">10:00 PM</p>
                </td>
            </tr>
        </table>
    </div>
    
    <div class="page-bg"></div>
</body>

</html>