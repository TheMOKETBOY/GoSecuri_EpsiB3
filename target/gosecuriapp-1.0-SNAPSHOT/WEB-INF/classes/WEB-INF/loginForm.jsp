<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 06/06/2020
  Time: 15:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>LoginForm</title>
</head>
<body>
<h1>Login</h1>
<p><%     String formErrors = (String) request.getAttribute("errors");
if(!(formErrors == null)){
    out.print(formErrors);
}
 %></p>
<form method="post">
    <input name="login" type="text" placeholder="johndoe">
    <input name="sumbit" type="submit" value="Authentification">
</form>


</body>
</html>
