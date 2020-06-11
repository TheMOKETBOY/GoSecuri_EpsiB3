<%@ page import="com.gosecuri_epsib3.beans.User" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 07/06/2020
  Time: 18:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%  pageContext.setAttribute("user",(User)request.getAttribute("user"));  %>
<html>
<head>
    <title>Nouvel utilisateur</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">

</head>
<body>
<div class="container">

    <ul>
        <li>ID : ${user.id} : </li>
        <li>Fullname : ${user.fullName} : </li>
        <li>Nombre de visages : :${user.visages.size()} : </li>
    </ul>

</div>
</body>
</html>
