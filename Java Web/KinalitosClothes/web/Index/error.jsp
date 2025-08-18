<%-- 
    Document   : error
    Created on : 17/08/2025, 01:52:46
    Author     : joshu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%-- error.jsp --%>
        <h2 style="color:red;"><%= request.getAttribute("mensajeError")%></h2>
        <a href="Controlador?menu=Index">Volver al inicio</a>
    </body>
</html>
