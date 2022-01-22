<%-- 
    Document   : headerUser
    Created on : Jul 11, 2021, 12:08:12 PM
    Author     : WilliamTrung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">       
        <style><%@include file="css/header.css" %></style>
        <style><%@include file="css/mystyle.css"%></style>
        <title>Header for User</title>
    </head>
    <body>
    <c:url var="shopping" value="MainController">
        <c:param name="search" value=""></c:param>    
        <c:param name="action" value="LoadItem"></c:param>           
    </c:url>
    <c:url var="logoutLink" value="MainController">
        <c:param name="action" value="Logout"/>
    </c:url>
    <c:url var="historyLink" value="MainController">
        <c:param name="action" value="View History"/>
    </c:url>
    <div class="navbar">
        <a class="active"href="${logoutLink}"><i class="fa fa-sign-out"></i> Logout</a>
        <a href="${shopping}"><i class="fa fa-cart-plus"></i> Shopping</a>
        <a href="viewCart.jsp"><i class="fa fa-shopping-cart"></i> View Cart</a> 
        <a href="${historyLink}"><i class="fa fa-history"></i> Purchase History</a>
        <a href="updateSelf.jsp"><i class="fa fa-address-card-o"></i> Edit</a>
        <c:if test="${not empty sessionScope.LOGIN_USER}">
            <a class="user"style="float: right"><i class="fa fa-fw fa-user"></i> User: ${sessionScope.LOGIN_USER.userName}</a>
        </c:if>           
    </div>    
    <div class="container-fluid">
        <h1>FPT Bookstore</h1>
    </div>
</div>
</body>
</html>
