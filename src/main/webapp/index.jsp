<%-- 
    Document   : index
    Created on : Jan 10, 2015, 7:10:45 PM
    Author     : jeferson
--%>

<%@page import="model.bean.Video"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
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
            <%
                if (transcode != null) {%>
            document.getElementById("refresh").submit();
            <%
                }
            %>
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
                    <td><textarea rows="4" cols="50" name="description"></textarea></td>
                </tr>
                <tr>
                    <td><input type="submit" id="upload" value="Upload" onclick="disablePage()"/></td>
                </tr>
            </table>
        </form>
        <form method="get" action="servletindex.do">
            <table>
                <tr>
                    <td><input type="submit" id="refresh" value="Refresh"/></td>
                </tr>
            </table>
        </form>
        <%
            List<Video> videos = new ArrayList();
            if ((List<Video>) getServletContext().getAttribute("videos") != null) {
                videos = (List<Video>) getServletContext().getAttribute("videos");
                getServletContext().setAttribute("videos", null);
            } else {
                videos = (List<Video>) request.getAttribute("videosAtt");
            }
            if(!videos.isEmpty()){
        %>
        <form method="post" action="servletmediaplayer.do">
            <table>
                <%for (Video v : videos) {%>
                <tr>
                <img src="<%="https://s3.amazonaws.com/sandboxencoded/" + v.getName() + "_0000.png"%>" width="350" height="200"/>
                </tr>
                <tr>
                <p><%= v.getDescription()%></p>
                </tr>
                <tr>
                    <td><input type="submit" name="<%= v.getName()%>" value="Play Video"/></td>
                </tr>
                <%}%>
            </table>
        </form>
            <%}%>
    </center>
</body>
</html>
