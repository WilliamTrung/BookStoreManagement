<%-- 
    Document   : checkout
    Created on : Jul 10, 2021, 11:43:43 AM
    Author     : WilliamTrung
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">              
        <link rel="stylesheet" 
              href="https://use.fontawesome.com/releases/v5.7.2/css/all.css" 
              integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" 
              crossorigin="anonymous">
        <title>Check Out</title>
    </head>
    <body>
        
            <div class="row">
                <div class="col-60">
                    <div class="container" style="margin-left: 20px">
                            <div class="row">
                                <div class="col-50">
                                    <h3>Billing Address</h3>
                                    <label for="fname"><i class="fa fa-user"></i> Full Name</label>
                                    <input type="text" id="fname" name="firstname" placeholder="Your name...">
                                    <label for="email"><i class="fa fa-envelope"></i> Email</label>
                                    <input type="text" id="email" name="email" placeholder="Your email...">
                                    <label for="adr"><i class="fa fa-address-card-o"></i> Address</label>
                                    <input type="text" id="adr" name="address" placeholder="Address...">
                                    <label for="city"><i class="fa fa-institution"></i> City</label>
                                    <input type="text" id="city" name="city" placeholder="Ho Chi Minh City">

                                    <div class="row">
                                        <div class="col-50">
                                            <label for="state">State</label>
                                            <input type="text" id="state" name="state" placeholder="Ho Chi Minh City">
                                        </div>
                                        <div class="col-50">
                                            <label for="zip">Zip</label>
                                            <input type="text" id="zip" name="zip" placeholder="70000">
                                        </div>
                                    </div>
                                </div>

                                <div class="col-50">
                                    <h3>Payment</h3>
                                    <label for="fname">Accepted Cards</label>
                                    <div class="icon-container">
                                        <i class="fab fa-cc-visa" style="color:navy;"></i>
                                        <i class="fab fa-cc-amex" style="color:blue;"></i>
                                        <i class="fab fa-cc-mastercard" style="color:red;"></i>
                                        <i class="fab fa-cc-discover" style="color:orange;"></i>
                                    </div>
                                    <label for="cname">Name on Card</label>
                                    <input type="text" id="cname" name="cardname" placeholder="Card Name">
                                    <label for="ccnum">Credit card number</label>
                                    <input type="text" id="ccnum" name="cardnumber" placeholder="1111-2222-3333-4444">
                                    <label for="expmonth">Exp Month</label>
                                    <input type="text" id="expmonth" name="expmonth" placeholder="September">

                                    <div class="row">
                                        <div class="col-50">
                                            <label for="expyear">Exp Year</label>
                                            <input type="text" id="expyear" name="expyear" placeholder="YYYY">
                                        </div>
                                        <div class="col-50">
                                            <label for="cvv">CVV</label>
                                            <input type="text" id="cvv" name="cvv" placeholder="XXX">
                                        </div>
                                    </div>
                                </div>

                            </div>
                            <label>
                                <input type="checkbox" checked="checked" name="sameadr"> Shipping address same as billing
                            </label>
                    </div>
                </div>

                <div class="col-25">
                    <form action="MainController">
                        <div class="container" style="width: 91%">
                        <h4>Cart
                            <span class="price" style="color:black">
                                <i class="fa fa-shopping-cart"></i>
                                <b>${sessionScope.CART.getCart().size()}</b>
                            </span>
                        </h4>
                        <c:set var="total" value="${0}"/>
                        <c:forEach var="item" items="${sessionScope.CART.getCart()}">
                            <c:set var="total" value="${total + item.value.price * item.value.quantity}"/>
                            <p>
                                <label>${item.value.productName} - ${item.value.quantity}</label> 
                                <span class="price">
                                    <label>${item.value.price * item.value.quantity} VND</label>
                                </span>                                                                    
                            </p>
                            <input type="hidden" name="uri" value="${item.value.productID}-${item.value.productName}-${item.value.price}-${item.value.catagory}-${item.value.quantity}"/>
                        </c:forEach>

                        <hr>
                        <p>Total <span class="price" style="color:black"><b>${total} VND</b></span></p>
                    </div>                
                    <input type="hidden" name="total" value="${total}"/>
                    <input type="submit" name="action" value="Continue to checkout" class="btn">
                    <button name="action" value="LoadItem" class="btn">Back to Shopping</button>                   
                        <label>${requestScope.CHECK_OUT_COMPLETED}</label>
                        <label>${requestScope.ERROR_MESSAGE}</label>
                        </form>
                </div>
            </div>
        
        <style><%@include file="css/mystyle.css"%></style>
    </body>
</html>
