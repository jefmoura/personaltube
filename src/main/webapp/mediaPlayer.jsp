<%-- 
    Document   : mediaPlayer
    Created on : Jan 10, 2015, 7:11:45 PM
    Author     : jeferson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
  String videoMP4 = (String) request.getAttribute("videoMP4");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tocador de Video</title>
    </head>
    <body>
    <center>
        <table>
            <tr>
                <td>
                    <form method="post" action="servletmediaplayer.do">
                        <input type="submit" value="Back" />
                    </form>
                </td>
                <td>
                    <p><b>If your video is not playing, probably was not completely transcoded yet.</b></p>
                    <p><b>So click the Back button, wait a time and put Play Video again!</b></p>
                </td>
            </tr>
            <tr>
                <td>
                    <video width="700" height="500" controls>
                        <source src="<%=videoMP4%>" type="video/mp4">
                        Your browser does not support the video tag.
                    </video>
                </td>
            </tr>
        </table>
    </center>
</body>
</html>
