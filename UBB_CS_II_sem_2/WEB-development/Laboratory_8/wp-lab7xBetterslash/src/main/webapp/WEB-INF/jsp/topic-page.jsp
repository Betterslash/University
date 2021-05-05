<%--
  Created by IntelliJ IDEA.
  User: Dori
  Date: 5/5/2021
  Time: 5:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Topic : ${topic}</title>
    <script src="https://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>

  <c:forEach items="${posts}" var="post">
        <label> Text :
            <textarea id="${post.user}">
                ${post.text}
            </textarea>
        </label>
  </c:forEach>


  <script>
      $(document).ready(
          function (){
              $("textarea").each(
                  function (){
                      if($(this).attr('id') != localStorage.getItem('logged-user')){
                          $(this).prop("disabled", true);
                          console.log($(this).attr('id'));
                      }else {
                          console.log($(this).attr('id'));
                      }
                  }
              )
          }
      )
  </script>

</body>
</html>
