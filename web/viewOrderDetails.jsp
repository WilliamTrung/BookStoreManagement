<%-- 
    Document   : viewOrderDetails
    Created on : Jul 12, 2021, 7:15:18 PM
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
        <style><%@include file="css/table.css" %></style>   
        <title>View Order Details</title>
    </head>
    <body>

        <c:if test="${sessionScope.LOGIN_USER.role eq 'ad'}">
            <%@include file="headerAdmin.jsp" %>
        </c:if>
        <c:if test="${sessionScope.LOGIN_USER.role ne 'ad'}">  
            <%@include file="headerUser.jsp" %>
        </c:if>
        <h3><b>${requestScope.ERROR_MESSAGE.errorDetail} ${requestScope.ERROR_MESSAGE.errorMessage}</b></h3>
        <c:if test="${sessionScope.LIST_ORDERS.size() ne 0}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Price</th>
                        <th>Catagory</th>
                        <th>Quantity</th>
                        <th>Status</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="order" varStatus="counter" items="${requestScope.LIST_ORDER_DETAILS}">
                        <tr>
                            <td>${counter.count}</td>
                            <td>${order.productID}</td>
                            <td>${order.productName}</td>
                            <td>${order.price} VND</td>
                            <td>${order.catagory}</td>
                            <td>${order.quantity}</td>
                            <td>${order.status eq '1' ?"Unpaid":"Paid"}</td>       
                        </tr>
                    </c:forEach>
                </tbody>                           
            </table>   
            
            <c:if test="${sessionScope.LOGIN_USER.role eq 'ad'}">
                <form action="MainController">
                    <button name="action" value="View History Admin" class="btn" style="display: block;width: 100px; text-align: center">Back</button>
                </form>
            </c:if>
            <c:if test="${sessionScope.LOGIN_USER.role ne 'ad'}">  
                <form action="MainController">
                    <button name="action" value="View History" class="btn" style="display: block;width: 100px; text-align: center">Back</button>
                </form>
            </c:if>

        </c:if>
    </body>
</html>
