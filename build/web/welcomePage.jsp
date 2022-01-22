<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>FPT BookStore</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">       
        <style><%@include file="css/header.css" %></style>
        <style><%@include file="css/table.css" %></style>
    </head>
    <body>

        <%@include file="header.jsp" %>
        <form action="MainController" method="POST">
            <div class="bg-img">
                <div class="centered">
                    <div class="container">
                        <label for="userName"><b>Username</b></label>
                        <input type="text" placeholder="Enter Username" name="userName" required>

                        <label for="password"><b>Password</b></label>
                        <input type="password" placeholder="Enter Password" name="password" required>

                        <button name="action" value="Login">Login</button>
                        <button type="button" class="resbtn"><a href="register.jsp" style="text-decoration: none;color: white">Register</a></button>
                    </div>
                </div>
            </div>
        </form>
    </body>
</html>
