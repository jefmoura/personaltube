<%-- 
    Document   : index
    Created on : Jan 10, 2015, 7:10:45 PM
    Author     : jeferson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Boolean transcode = (Boolean) request.getAttribute("transcode");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>PersonalTube</title>
    </head>
    <body>
        <script>
            function disablePage() {
                document.getElementById("disablingDiv").style.width = "100%";
                document.getElementById("disablingDiv").style.height = "100%";
                document.getElementById("disablingDiv").style.visibility = "visible";
                document.getElementById("disablingDiv").style.position = "absolute";
                document.getElementById("disablingDiv").style.zIndex = "99";
                document.getElementById("disablingDiv").style.top = "0px";
                document.getElementById("disablingDiv").style.left = "0px";
                document.getElementById("disablingDiv").style.backgroundColor = "black";
                document.getElementById("disablingDiv").style.opacity = "0.4";
                document.getElementById("disablingDiv").style.display = 'block';
                document.getElementById("disablingDiv").innerHTML = '<center><div style="margin-top:20%;"><font color="white"><b>Wait! Sending File.</b></font></div></center>';
            }
        </script>
        <div id="disablingDiv"></div>
    <center>
        <h1>PersonalTube</h1>
        <form method="post" action="servletindex.do" enctype="multipart/form-data">
            <table>
                <tr><td>Select file to upload:</td></tr>
                <tr>
                    <td><input type="text" name="uuid" /></td>
                    <td><input type="file" name="uploadFile" /></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Upload" onclick="disablePage()"/></td>
                </tr>
            </table>
        </form>
        <%
            if ((transcode != null) && (transcode == true)) {
        %>
        <form method="post" action="transcodelistener.do">
            <table>
                <tr>
                    <td><h2>Now you can play your video!</h2></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Play Video"/></td>
                </tr>
            </table>
        </form>
        <%
            }
        %>
    </center>
</body>
</html>
