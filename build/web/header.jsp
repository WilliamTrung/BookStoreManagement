<%-- 
    Document   : header
    Created on : Jun 22, 2021, 2:46:45 PM
    Author     : WilliamTrung
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="Users.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>header</title>
    </head>
    <body>
        <c:url var="shopping" value="MainController">
            <c:param name="search" value=""></c:param>    
            <c:param name="action" value="LoadItem"></c:param>           
        </c:url>
        <div class="navbar">
            <a class="active"href="welcomePage.jsp"><i class="fa fa-fw fa-user"></i> Login</a>
            <a href="${shopping}"><i class="fa fa-cart-plus"></i> Shopping</a>
            <a href="viewCart.jsp"><i class="fa fa-shopping-cart"></i> View Cart</a>     
            <c:if test="${not empty sessionScope.LOGIN_USER}">
                <a class="user"style="float: right"><i class="fa fa-fw fa-user"></i> User: ${sessionScope.LOGIN_USER.userName}</a>
            </c:if>  
        </div>          
        <div class="container-fluid">
            <h1>FPT Bookstore</h1>
        </div>
    </body>
</html>
