<%--
  Created by IntelliJ IDEA.
  User: Dori
  Date: 5/5/2021
  Time: 1:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>WowRoom</title>
    <link rel="stylesheet" href="styles.css">
    <script src="https://code.jquery.com/jquery-latest.min.js"></script>
    <script src="includes/js/jquery-latest.min.js"></script>
    <script src="calls.js"></script>

</head>
<body>
<div class = "main-container">
    <form class = "login-form" method="post">
        <label> Username :
            <input name = "username" placeholder="Username" type="text">
        </label>
        <label> Password :
            <input name = "password" placeholder="Password" type="password">
        </label>
        <input type="submit" value="Login" id = "login-submit-button">
    </form>
</div>
</body>
</html>
