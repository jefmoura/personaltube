<%-- 
    Document   : transcoding
    Created on : Jan 12, 2015, 2:33:03 PM
    Author     : jeferson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Wait!</title>
    </head>
    <body>
        <form id="transcodeForm" method="post" action="servlettranscode.do">
            <center>
                <h2>Wait! Transcoding . . .</h2>
            </center>
        </form>
        <script>
            window.onload = function () {
                document.getElementById("transcodeForm").submit();
            }
        </script>
    </body>
</html>
