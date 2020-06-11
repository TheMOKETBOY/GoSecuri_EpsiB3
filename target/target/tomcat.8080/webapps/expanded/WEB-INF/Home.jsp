<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page import="static com.gosecuri_epsib3.beans.DAO.getEquipements" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.gosecuri_epsib3.beans.Equipement" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 06/06/2020
  Time: 16:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
<h1>Hello,  <% out.print(session.getAttribute("AUTH"));  %> </h1>

<button onclick="window.location.href='/Logout'">Logout</button>

<form method="post">


<fieldset>
    <ul>
        <%@ page import="java.util.*" %>
<%

    pageContext.setAttribute("equipements", getEquipements());

%>

<c:forEach items="${equipements}" var="item">
    <li>
        <input name="Equipements" value="${item.id}" type="checkbox">${item.label} </input>

    </li>

</c:forEach>
    </ul>

</fieldset>
<input type="submit" name="Enregister">
    </form>


</body>
</html>
