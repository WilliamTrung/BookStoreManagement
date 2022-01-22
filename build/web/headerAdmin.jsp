<%-- 
    Document   : headerAdmin
    Created on : Jul 12, 2021, 10:00:52 AM
    Author     : WilliamTrung
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Header Admin</title>
    </head>
    <body>
        <c:url var="logoutLink" value="MainController">
            <c:param name="action" value="Logout"/>
        </c:url>
        <c:url var="productManagement" value="MainController">
            <c:param name="action" value="SearchProduct"/>
        </c:url>
        <c:url var="historyLink" value="MainController">
            <c:param name="action" value="View History Admin"/>
        </c:url>
        <c:url var="userManagement" value="MainController">
            <c:param name="action" value="Search"/>
        </c:url>
        <div class="navbar">
            <a class="active"href="${logoutLink}"><i class="fa fa-sign-out"></i> Logout</a>
            <a href="${userManagement}"><i class="fa fa-users"></i> User Management</a>
            <a href="${productManagement}"><i class="fa fa-book"></i> Product Management</a>            
            <a href="${historyLink}"><i class="fa fa-history"></i> Purchase History</a>
            <a href="updateSelf.jsp"><i class="fa fa-address-card-o"></i> Edit</a>
            <a class="user"style="float: right"><i class="fa fa-fw fa-user"></i> User: ${sessionScope.LOGIN_USER.userName}</a>          
        </div>    
        <div class="container-fluid">
            <h1>FPT Bookstore</h1>
        </div>
    </div>
</body>
</html>
