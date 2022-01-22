<%-- 
    Document   : updateSelf
    Created on : Jul 12, 2021, 3:36:29 PM
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
        <title>Edit personal information</title>
    </head>
    <body>
        <c:if test="${sessionScope.LOGIN_USER.role eq 'ad'}">
            <%@include file="headerAdmin.jsp" %> 
        </c:if>
        <c:if test="${sessionScope.LOGIN_USER.role eq 'us'}">
            <%@include file="headerUser.jsp"%> 
        </c:if>       
        <form action="MainController">
            <div class="container">
                <h1>User ID: ${sessionScope.LOGIN_USER.userID}</h1>
                <input type="hidden" name="userID" value="${sessionScope.LOGIN_USER.userID}"/>
                <hr>

                <label for="Username"><b>Username</b></label>
                <input type="text" placeholder="${sessionScope.LOGIN_USER.userName}" name="userName" id="userName" required>
                ${requestScope.USER_ERROR.userNameError}</br>

                <label for="userBirthday"><b>Birthday</b></label>
                <input type="date" value="${sessionScope.LOGIN_USER.userBirthday}" name="userBirthday" id="userBirthday" required>
                </br>

                <label for="role"><b>Role: </t> ${sessionScope.LOGIN_USER.role eq 'ad' ?"ADMIN":"USER"}</b></label>
                <input type="hidden" name="oldRole" value="${sessionScope.LOGIN_USER.role}"/>
                </br>

                <label for="password"><b>Password</b></label>
                <input type="password" placeholder="Enter Password" name="password" id="password">
                ${requestScope.USER_ERROR.passwordError}</br>

                <label for="confirmedPassword"><b>Repeat Password</b></label>
                <input type="password" placeholder="Repeat Password" name="confirmedPassword" id="confirmedPassword">
                ${requestScope.USER_ERROR.confirmPasswordError}</br>

                <hr>
                <button type="reset" >Reset</button>
                <button type="submit" name="action" value="Confirm Update">Update</button>
            </div>
        </form>  
    </body>
</html>
