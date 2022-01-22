
<%-- 
    Document   : admin_search
    Created on : Jun 23, 2021, 12:28:08 PM
    Author     : WilliamTrung
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="Users.UserDAO"%>
<%@page import="java.util.List"%>
<%@page import="Users.UserDTO"%>
<%@page import="Users.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <style><%@include file="css/table.css"%></style>
        <title>admin_search</title>
    </head>
    <body>       
            <form class="search" action="MainController" style="margin:auto;max-width:300px">
                <input type="text" placeholder="Search by Name" name="search">
                <button type="submit" name="action" value="Search"><i class="fa fa-search"></i></button>
            </form>
            <c:if test="${not empty requestScope.LIST_USER}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No</th>
                            <th>User ID</th>
                            <th>Full Name</th>
                            <th>Role ID</th>
                            <th>Birthday</th>
                            <th>Password</th>
                            <th>Update</th>
                            <th>Status</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" varStatus="counter" items="${requestScope.LIST_USER}">                         
                            <tr>
                                <td>${counter.count}</td>
                        <form action="MainController">
                            <td><input type="text" name="userID" value="${dto.userID}" readonly=""/></td>
                            <td>
                                <input type="text" name="userName" value="${dto.userName}"/>
                            </td>
                            <td>
                                <select name="newRole">                         
                                    <option disabled selected>${dto.role}</option>
                                    <option value="USER">USER</option>
                                    <option value="ADMIN">ADMIN</option>
                                </select>
                            </td>
                            <td><input type="date" name="userBirthday" value="${dto.userBirthday}"></td>
                            <td><input type="text" name="password" value="${dto.password}" readonly=""/></td>
                            <td>
                                <input type="hidden" name="oldRole" value="${dto.role}"/>
                                <input type="hidden" name="search" value="${param.search}"/>
                                <input type="submit" name="action" value="Confirm Update"/>
                            </td>
                        </form>

                        <td>
                            <form action="MainController">
                                <input type="hidden" name="userID" value="${dto.userID}"/>
                                <input type="hidden" name="search" value="${param.search}"/>                              
                                <input type="hidden" name="status" value="${dto.status}"/>   
                                <input type="hidden" name="action" value="ChangeStatus"/>
                                <input id="activation" type="checkbox" ${dto.status == 'Active' ? "checked=''" : "unchecked=''"} onChange="this.form.submit()">
                                <label for="activation">${dto.status}</label>
                            </form>
                        </td>
                        </tr>  
                    </c:forEach>
                    </tbody>                  
                </table>

            </c:if>
            <c:if test="${empty requestScope.LIST_USER}">
                No record found!
            </c:if>
                
    </body>
</html>
