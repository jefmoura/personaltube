<%-- 
    Document   : index
    Created on : Jan 10, 2015, 7:10:45 PM
    Author     : jeferson
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Upload Video</title>
    </head>
    <h1>PersonalTube</h1>
    <body>
        <form method="post" action="servletindex.do">
            <table>
                <tr><td>Choose File:</td></tr>
                <tr>
                    <td><input type="hidden" value="" name="hd1" id="hdFile"/></td>
                    <td><input id="file" name="file" type="file" size="60" /></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Upload File" /></td>
                </tr>
            </table>
            <script type="text/javascript">
                var filePath = document.getElementById('file').value;
                document.getElementById('hdFile'
                        ).value = filePath;
                document.getElementById('file'
                        ).value = '';
            </script>
        </form>
    </body>
</html>
