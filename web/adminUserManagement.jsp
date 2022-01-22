<%-- 
    Document   : admin
    Created on : Jun 19, 2021, 2:31:32 PM
    Author     : WilliamTrung
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">       
        <style><%@include file="css/header.css" %></style>
        <style><%@include file="css/mystyle.css" %></style>
        <style><%@include file="css/searchbar.css" %></style>
        <style><%@include file="css/table.css" %></style>
        <title>UserManagement Page</title>
    </head>
    <body>        
        <%@include  file="headerAdmin.jsp" %>    
        <section id="userManagement">
            <%@include  file="adminUserChange.jsp" %>       
        </section>
    </body>
</html>
