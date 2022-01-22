<%-- 
    Document   : admin_product_change
    Created on : Jul 12, 2021, 9:33:54 AM
    Author     : WilliamTrung
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product settings</title>
    </head>
    <body>
        <form class="search" action="MainController" style="margin:auto;max-width:300px">
            <button type="submit" name="action" value="Create Product"><i class="fa fa-plus"></i></button>
            <input type="text" value="Create new product" readonly="">
        </form>
        <form class="search" action="MainController" style="margin:auto;max-width:300px">
            <input type="text" placeholder="Search by Name" name="search">
            <button type="submit" name="action" value="SearchProduct"><i class="fa fa-search"></i></button>
        </form>
        <c:forEach var="book" varStatus="counter" items="${requestScope.LIST_ITEMS}">

            <div class="container" style="width:600px;height: 400px;margin-right: 20px;margin-left: 20px;margin-bottom: 20px;">
                <form action="MainController" style="display: inline-block; margin-bottom: 20px;">
                    <div class="row">
                        <div class="col-50">
                            <h3 style="margin: 0%">
                                <input type="text" name="productName" value="${book.productName}" style="margin: 0%"/>                              
                            </h3>
                               <input type="text" name="productImage" value="${book.image}" style="margin: 0%"/>                        
                            <table border="1">
                                <tbody>
                                    <tr>                                      
                                        <td><b>Catagory</b></td>
                                        <td>
                                            <select name="catagory">                         
                                                <option disabled selected>${book.catagory}</option>
                                                <c:forEach var="catagory" items="${CATAGORIES}">
                                                    <option value="${catagory}">${catagory}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Price (VND)</b></td>
                                        <td><input type="number" min="0" step="1000" value="${book.price}" name="price"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>Status</b></td>

                                        <td>
                                            <form action="MainController">
                                                <input type="hidden" name="productID" value="${book.productID}"/>
                                                <input type="hidden" name="search" value="${param.search}"/>                              
                                                <input type="hidden" name="status" value="${book.status}"/>   
                                                <input type="hidden" name="action" value="ChangeStatusProduct"/>
                                                <input id="activation" type="checkbox" ${book.status == '1' ? "checked=''" : "unchecked=''"} onChange="this.form.submit()">
                                                <label for="activation">${book.status eq '1' ? "Available":"Unavailable"}</br> In storage: ${book.quantity}</label>
                                            </form></td>
                                    </tr>
                                    <tr id="quantityInput">
                                        <td><b>Quantity adding</b></td>
                                        <td><input id="quantityIn"type="number" name="quantityAdding" min="0" value="0"></td>
                                    </tr>
                                </tbody>
                                <input type="hidden" name="productName" value="${book.productName}"/>
                                <input type="hidden" name="catagory" value="${book.catagory}"/>
                                <input type="hidden" name="quantity" value="${book.quantity}"/>
                                <input type="hidden" name="search" value="${param.search}"/>
                                <input type="hidden" name="productID" value="${book.productID}"/>
                                <input type="hidden" name="productImage" value="${book.image}"/>
                                <input class="btn" style="" type="submit" name="action" value="Modify item"/>
                            </table>

                        </div>
                        <div class="wrapper">
                            <img class="center" src="image/${book.image}" style="max-height: 300px;width: auto;max-width: 250px;display: inline-block"/>                          
                        </div>
                    </div>
                </form>
            </div>





        </c:forEach>

    </body>
</html>
