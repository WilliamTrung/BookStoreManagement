<%-- 
    Document   : viewCart
    Created on : Jul 3, 2021, 9:10:18 AM
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
        <title>View Cart Page</title>
    </head>
    <body>
        <%@include file="headerUser.jsp" %>
        <c:if test="${sessionScope.LOGIN_USER.role eq 'ad'}">
            <h1>
                Admin cannot shopping while on duty!
            </h1>
        </c:if>
        <c:if test="${sessionScope.LOGIN_USER.role ne 'ad'}">  
            <c:if test="${empty sessionScope.CART}">
                <form action="MainController">
                    <div class="container" style="width: 100%;font-size: 200%;text-align: center"><b>Empty Cart</b></div>
                    <button name="action" value="LoadItem" class="btn">Back to Shopping</button>
                </form>

            </c:if>
            <c:if test="${not empty sessionScope.CART}">
                <c:if test="${sessionScope.CART.getCart().size() eq 0}">
                    <form action="MainController">
                        <div class="container" style="width: 100%;font-size: 200%;text-align: center"><b>Empty Cart</b></div>
                        <button name="action" value="LoadItem" class="btn">Back to Shopping</button>
                    </form>             
                </c:if>
                <c:if test="${sessionScope.CART.getCart().size() ne 0}">
                    <c:if test="${requestScope.ERROR_MESSAGE.id eq 'header'}">
                        <h3><b>${requestScope.ERROR_MESSAGE.errorDetail} ${requestScope.ERROR_MESSAGE.errorMessage}</b></h3>
                    </c:if>
                    <form action="MainController">
                        <table border="1">
                            <thead>
                                <tr>
                                    <th>No.</th>
                                    <th>ID</th>
                                    <th>Name</th>
                                    <th>Price</th>
                                    <th>Catagory</th>
                                    <th>Quantity</th>    
                                    <th>Remove</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:set var="total" value="${0}"/>
                                <c:forEach var="item" varStatus="counter" items="${sessionScope.CART.getCart()}">
                                    <c:set var="total" value="${total + item.value.price * item.value.quantity}"/>
                                    <tr>
                                        <td>${counter.count}</td>
                                        <td>${item.value.productID}</td>
                                        <td>${item.value.productName}</td>
                                        <td>${item.value.price} VND</td>
                                        <td>${item.value.catagory}</td>
                                        <td>
                                            <input id="quantityUpdate" type="number" name="quantity"min="0" value="${item.value.quantity}"pattern="\d*"/>
                                            <c:if test="${requestScope.ERROR_MESSAGE.id eq item.value.productID}">
                                                <p>${requestScope.ERROR_MESSAGE.errorDetail}    ${requestScope.ERROR_MESSAGE.errorMessage}</p>
                                            </c:if>
                                            <input type="hidden" name="action" value="Update Cart"/>
                                            <input type="hidden" name="uri" value="${item.value.productID}-${item.value.productName}-${item.value.price}-${item.value.catagory}"/>                                            

                                        </td>
                                        <td>
                                            <form action="MainController">
                                                <c:url var="delete" value="MainController">
                                                    <c:param name="action" value="RemoveItemInCart"></c:param>    
                                                    <c:param name="productID" value="${item.value.productID}"></c:param>  
                                                </c:url>
                                                <button onclick="location.href = '${delete}'" type="button"><i class="fa fa-trash"></i></button>
                                            </form>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="3" style="font-size: 20px;font-weight: bolder">
                                        Total:
                                    </td>
                                    <td colspan="4" style="font-size: 20px;font-weight: bolder">
                                        ${total} VND  
                                        <input type="hidden" name="total" value="${total}"/>
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                        <button class="resbtn"onclick="location.href = 'checkout.jsp'" type="button">Check out</button>
                        <button style="background-color: #04AA6D" class="resbtn" name="action" value="Update Cart" ><b>Update Cart</b></button>                                                
                    </form>
                    <form action="MainController" style="display: inline-block;color: #04AA6D">
                        <button style="background-color: #04AA6D" class="resbtn" name="action" value="Clear Cart"><b>Clear Cart</b></button>  
                    </form>
                </c:if>
            </c:if>
        </c:if>
    </body>
</html>
