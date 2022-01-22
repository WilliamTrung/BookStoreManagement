<%-- 
    Document   : error
    Created on : Jun 18, 2021, 11:52:08 AM
    Author     : WilliamTrung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">       
        <style><%@include file="css/header.css" %></style>
        <style><%@include file="css/table.css" %></style>
        <title>Error Page</title>
    </head>
    <body>       
        <%@include file="header.jsp" %>
        <h1>Error : ${sessionScope.ERROR_MESSAGE}</h1>       
    </body>
</html>
