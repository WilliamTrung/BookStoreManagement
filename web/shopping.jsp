<%-- 
    Document   : shopping
    Created on : Jun 16, 2021, 12:16:44 AM
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
        <title>FPT BookStore</title>
    </head>
    <body>
        <%@include file="headerUser.jsp"%>     
        <c:if test="${sessionScope.LOGIN_USER.role eq 'ad'}">
            <h1>
                Admin cannot shopping while on duty!
            </h1>
        </c:if>
        <c:if test="${sessionScope.LOGIN_USER.role ne 'ad'}">     

            <form class="search" action="MainController" style="margin:auto;max-width:300px">
                <input type="text" placeholder="Search by Name" name="search">
                <button type="submit" name="action" value="LoadItem"><i class="fa fa-search"></i></button>
            </form>
            <h3><b>${requestScope.ERROR_MESSAGE.errorDetail} ${requestScope.ERROR_MESSAGE.errorMessage}</b></h3>
            <c:forEach var="book" varStatus="counter" items="${requestScope.LIST_ITEMS}">
                <form action="MainController" style="display: inline-block; margin-bottom: 20px;">
                    <div class="container" style="width:600px;height: 300px;margin: auto;">
                        <div class="row">
                            <div class="col-50">
                                <h3>${book.productName}

                                </h3>
                                <table border="1">
                                    <tbody>
                                        <tr>
                                            <td><b>Catagory</b></td>
                                            <td>${book.catagory}</td>
                                        </tr>
                                        <tr>
                                            <td><b>Price</b></td>
                                            <td>${book.price}VND</td>
                                        </tr>
                                        <tr>
                                            <td><b>Status</b></td>
                                            <td>${book.status eq '1' ? "Available":"Unavailable"}</br> In storage: ${book.quantity}</td>
                                        </tr>
                                        <c:if test="${book.status eq '1'}">
                                            <tr id="quantityInput">
                                                <td>Quantity</td>
                                                <td><input id="quantityIn"type="number" name="quantityToCart" min="0" max="${book.quantity-sessionScope.CART.getCart()[book.productID].quantity}"  value="0"></td>
                                            </tr>
                                        </c:if>
                                    </tbody>
                                    <input type="hidden" name="productName" value="${book.productName}"/>
                                    <input type="hidden" name="catagory" value="${book.catagory}"/>
                                    <input type="hidden" name="price" value="${book.price}"/>
                                    <input type="hidden" name="quantity" value="${book.quantity}"/>
                                    <input type="hidden" name="search" value="${param.search}"/>
                                    <input type="hidden" name="productID" value="${book.productID}"/>
                                    <input class="btn" style="" type="submit" name="action" value="Add to Cart"/>
                                </table>

                            </div>
                            <div class="wrapper">
                                <img class="center" src="image/${book.image}" style="max-height: 300px;width: auto;display: inline-block"/>
                            </div>
                        </div>
                    </div>
                </form>
            </c:forEach>
        </c:if>
    </body>
</html>
