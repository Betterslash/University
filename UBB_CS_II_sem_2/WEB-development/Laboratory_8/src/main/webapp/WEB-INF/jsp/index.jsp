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
<body style="background-image: url('assets/background.png')">
<p style="color: antiquewhite; font-size: 2vh;">Hello ${username}</p>
<form method="post" id="logout-form">
    <input type="submit" value="Logout">
</form>
<label style="color: antiquewhite; font-size: 3vh;">Available topics : </label>
<div class="topics-container">
    <c:forEach items="${topics}" var="topic">
        <button onclick="navigateTo(${topic.id})">${topic.subject}</button>
    </c:forEach>
</div>


<form id="add-topic-form" method="post">
    <input type="text" name="subject" placeholder="Topic Subject">
    <input type="submit" value="Add Topic">
</form>
<p></p>
</body>
</html>
