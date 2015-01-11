<%-- 
    Document   : mediaPlayer
    Created on : Jan 10, 2015, 7:11:45 PM
    Author     : jeferson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tocador de Video</title>
    </head>
    <body>
        <table>
            <tr>
                <td>
                    <form method="post" action="servletmediaplayer.do">
                        <input type="submit" value="Back" />
                    </form>
                </td>
            </tr>
            <tr>
                <td>
                    <embed src=${pathVideo} type="application/x-mplayer2" pluginspage="http://www.microsoft.com/Windows/MediaPlayer/" name="mediaplayer1" ShowStatusBar="true" EnableContextMenu="false" width="700" height="500" autostart="false" loop="false" align="middle" volume="60" >
                </td>
            </tr>
        </table>
    </body>
</html>
