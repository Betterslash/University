<%--
  Created by IntelliJ IDEA.
  User: Dori
  Date: 5/4/2021
  Time: 10:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <title>Admin</title>
    <link rel="stylesheet" href="istyles.css">
    <script src="https://code.jquery.com/jquery-latest.min.js"></script>
    <script src="includes/js/jquery-latest.min.js"></script>
    <script src="logout-call.js"></script>


</head>
<body>
<p>Hello ${username}</p>
<label>Available topics : </label>
<div class="topics-container">
    <c:forEach items="${topics}" var="topic">
        <a href="/topics/${topic.id}">${topic.subject}</a>
    </c:forEach>
</div>
<form method="post" id="logout-form">
    <input type="submit" value="Logout">
</form>
<p></p>
</body>
</html>
