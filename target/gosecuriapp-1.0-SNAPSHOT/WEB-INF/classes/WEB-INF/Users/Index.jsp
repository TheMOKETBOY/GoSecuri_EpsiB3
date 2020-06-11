<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.gosecuri_epsib3.beans.User" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 07/06/2020
  Time: 04:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ArrayList<User> users = (ArrayList<User>) request.getSession().getAttribute("usersDB");
    pageContext.setAttribute("users",users );

%>
<html>
    <head>
        <title>Manage users</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    </head>
    <body>

     <div class="container"><a href="<%=request.getContextPath()+"/"%>Admin/Users/new" class="btn btn-primary">Ajouter</a>
        <table class="table">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">Prenom</th>
                <th scope="col">Nom</th>
                <th scope="col">Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${users}" var="item">

                <tr>
                    <th scope="row">${item.id}</th>
                    <td>${item.firstName}</td>
                    <td>${item.lastName}</td>
                    <td><a href="<%=request.getContextPath()+"/"%>Admin/Users/show?id=${item.id}">Show</a>
                        <a href="<%=request.getContextPath()+"/"%>Admin/Users/delete?id=${item.id}">Delete</a>
                    </td>
                </tr>
            </c:forEach>

            </tbody>
        </table>
     </div>

    </body>
</html>
