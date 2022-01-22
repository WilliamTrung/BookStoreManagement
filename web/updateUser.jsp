<%-- 
    Document   : update
    Created on : Jun 8, 2021, 12:47:06 PM
    Author     : ACER
--%>

<%@page import="Users.UserDTO"%>
<%@page import="Users.UserError"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">       
        <style><%@include file="css/header.css" %></style>
        <style><%@include file="css/mystyle.css" %></style>
        <style><%@include file="css/searchbar.css" %></style>
        <style><%@include file="css/table.css" %></style>

        <title>Update Page</title>
    </head>
    <body>


        <%@include file="headerAdmin.jsp" %> 
        <form action="MainController">
            <div class="container">
                <h1>User ID: ${requestScope.USER_UPDATING.userID}</h1>
                <input type="hidden" name="userID" value="${requestScope.USER_UPDATING.userID}"/>
                <hr>

                <label for="Username"><b>Username</b></label>
                <input type="text" placeholder="${requestScope.USER_UPDATING.userName}" name="userName" id="userName" required>
                ${requestScope.USER_ERROR.userNameError}</br>

                <label for="userBirthday"><b>Birthday</b></label>
                <input type="date" value="${requestScope.USER_UPDATING.userBirthday}" name="userBirthday" id="userBirthday" required>
                </br>

                <label for="role"><b>Role </t> ${requestScope.USER_UPDATING.role}</b></label>
                <input type="hidden" name="oldRole" value="${requestScope.USER_UPDATING.role}"/>
                <select name="newRole">
                    <!--<option>null</option>-->                                
                    <option value="" disabled="" selected="">Choose role</option>
                    <option value="USER">USER</option>
                    <option value="ADMIN">ADMIN</option>
                </select>
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
