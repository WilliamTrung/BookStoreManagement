<%-- 
    Document   : register
    Created on : Jun 21, 2021, 5:28:54 PM
    Author     : WilliamTrung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">       
        <style><%@include file="css/header.css" %></style>
        <style><%@include file="css/mystyle.css" %></style>
        <style><%@include file="css/table.css" %></style>  
        <title>Register Account</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <form action="MainController">
            <div class="centered" style="margin-top: 50px">
            <div class="container">
                <label for="Username"><b>Username</b></label>
                <input type="text" placeholder="${sessionScope.LOGIN_USER.userName}" name="userName" id="userName" required>
                ${requestScope.USER_ERROR.userNameError}</br>

                <label for="userBirthday"><b>Birthday</b></label>
                <input type="date" value="${sessionScope.LOGIN_USER.userBirthday}" name="userBirthday" id="userBirthday" required>
                </br>

                <label for="password"><b>Password</b></label>
                <input type="password" placeholder="Enter Password" name="password" id="password">
                ${requestScope.USER_ERROR.passwordError}</br>

                <label for="confirmedPassword"><b>Repeat Password</b></label>
                <input type="password" placeholder="Repeat Password" name="confirmedPassword" id="confirmedPassword">
                ${requestScope.USER_ERROR.confirmPasswordError}</br>

                <hr>
                <button type="reset" >Reset</button>
                <button type="submit" name="action" value="Register">Register</button>
            </div>
            </div>
        </form>
        <div class="centered" style="margin-top: 310px">
            <label class="wrapper">
                ${requestScope.ERROR_MESSAGE.errorMessage}    
            </label>
        </div>
        
    </body>
</html>
