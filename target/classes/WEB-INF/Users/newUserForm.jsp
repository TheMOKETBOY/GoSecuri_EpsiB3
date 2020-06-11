<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 07/06/2020
  Time: 18:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Nouvel utilisateur</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">

</head>
<body>
<div class="container">


    <form action="<%=request.getContextPath()+"/"%>Admin/Users/insert" method="post">
        <div class="form-group">
            <label for="FirstName">Prénom</label>
            <input type="text" class="form-control" name="FirstName" id="FirstName" aria-describedby="FirstName" placeholder="John">
        </div>
        <div class="form-group">
            <label for="LastName">Nom</label>
            <input type="text" class="form-control" name="LastName" id="LastName" aria-describedby="LastName" placeholder="Doe">
        </div>

        <button type="submit" class="btn btn-primary">Ajouter</button>
    </form>
</div>
</body>
</html>
