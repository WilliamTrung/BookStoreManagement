<%-- 
    Document   : viewHistoryPurchase
    Created on : Jul 12, 2021, 4:13:58 PM
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
        <title>History purchase</title>
    </head>
    <body>
        <%@include file="headerUser.jsp" %>
        <c:if test="${sessionScope.LOGIN_USER.role eq 'ad'}">
            <h1>
                Admin cannot shopping while on duty!
            </h1>
        </c:if>
        <c:if test="${sessionScope.LOGIN_USER.role ne 'ad'}">  
            <h3><b>${requestScope.ERROR_MESSAGE.errorDetail} ${requestScope.ERROR_MESSAGE.errorMessage}</b></h3>
            <c:if test="${sessionScope.LIST_ORDERS.size() ne 0}">
                    <table border="1">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>Order ID</th>
                                <th>Created Date</th>
                                <th>Total</th>
                                <th>Status</th>
                                <th>More</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="order" varStatus="counter" items="${requestScope.LIST_ORDERS}">
                                <tr>
                                    <td>${counter.count}</td>
                                    <td>${order.orderID}</td>
                                    <td>${order.orderDate}</td>
                                    <td>${order.orderTotal} VND</td>
                                    <td>${order.status eq '1' ?"Unpaid":"Paid"}</td>       
                                    <td>
                                        <form action="MainController">
                                            <input type="hidden" name="orderID" value="${order.orderID}"/>
                                            <button class="resbtn" style="background-color: #04AA6D"name="action" value="More order details"><i class="fa fa-plus"></i></button> 
                                        </form>
                                                                          
                                    </td>
                                </tr>
                            
                            </c:forEach>
                        </tbody>                           
                    </table>                                              
            </c:if>
        </c:if>
    </body>
</html>
