<%-- 
    Document   : upload
    Created on : Apr 11, 2018, 9:05:39 AM
    Author     : nguye
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Upload</title>
    </head>
    <body>
        <form method="POST" action="UploadServlet" enctype="multipart/form-data">
            <input type="file" name="image[]" multiple="">
            <input type="submit" name="upload" value="Upload"/>
        </form>
    </body>
</html>
