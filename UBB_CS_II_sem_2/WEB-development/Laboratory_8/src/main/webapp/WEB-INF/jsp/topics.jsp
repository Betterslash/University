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
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Topic : ${topic}</title>
    <style>
        /* width */
        ::-webkit-scrollbar {
            width: 15px;
        }

        /* Track */
        ::-webkit-scrollbar-track {
            background-color: #252727;
        }

        /* Handle */
        ::-webkit-scrollbar-thumb {
            background: #1fb4b3;
            border-radius: 3px;
        }

        /* Handle on hover */
        ::-webkit-scrollbar-thumb:hover {
            background: #026f68;
        }
    </style>
    <script src="https://code.jquery.com/jquery-latest.min.js"></script>
    <link href="../estyles.css" type="text/css"
          rel="stylesheet">
</head>
<body>
    <div class = "subject-container">
        <h2 >
            ${text}
        </h2>
    </div>
    <div class = "posts-container" style="display: flex;width: 100%; justify-content: center; align-items: center; flex-direction: column">
      <c:forEach items="${posts}" var="post">
          <form id="form-${post.id}" class="crud-form" method="POST">
            <label>
                <textarea id="${post.user}" class = "post-${post.id}">
                    ${post.text}
                </textarea>
            </label>
              <input  name = "update" class ="modify-post" type="submit" value="Modify" id ="post-${post.user}">
          </form>
      </c:forEach>


        <form class = "delete-post-form">
            <h3 class = "selected-container"></h3>
            <input class ="add-post-button" type="submit" value="Delete Selected">
        </form>

        <form class = "add-post-form">

            <label>
                <input name = "text" class="add-text-input" placeholder="TEXT">
            </label>
            <input class ="add-post-button" type="submit" value="Add comment!">
        </form>
    </div>
    <script>
        let URL = "http://localhost:8080/posts/";
        function replaceText(text){
            $('.selected-container').text(text);
        }
        $(document).ready(
            function (){
                $('.delete-post-form').submit(
                    function(e){
                        $.ajax({
                            type : "POST",
                            contentType: "application/json",
                            url: URL + 'delete/' + localStorage.getItem('selected-post'),
                            cache: false,
                            timeout: 600000,
                            success : (data) =>{
                                console.log(data);
                            }
                        });

                        e.preventDefault();
                        location.reload();
                    }
                );
                $('.crud-form').submit(
                    function(e){
                        let currentText = ".post-" + localStorage.getItem("selected-post");
                        const req = {
                            id : localStorage.getItem("selected-post"),
                            text : $(currentText).val(),
                            user : parseInt(localStorage.getItem("logged-user")),
                            topic : ${currentTopicId}
                        };
                        const url = URL + "update/"+localStorage.getItem("selected-post");
                        $.ajax({
                            type : "POST",
                            contentType: "application/json",
                            url: url,
                            data: JSON.stringify(req),
                            dataType: 'json',
                            cache: false,
                            timeout: 600000,
                            success : (data) =>{
                                console.log(data);
                                location.reload();
                            }
                        });
                        location.reload();
                        e.preventDefault();

                    }
                );


                $('textarea').click(
                    function (){
                        let id = $(this).attr('class').toString().split("-")[1];
                        localStorage.setItem("selected-post", id);
                        replaceText('Selected post has ID : ' + id + ' !');
                    }
                );
                $("textarea").each(
                    function (){
                        if($(this).attr('id') != localStorage.getItem('logged-user')){
                            $(this).prop("disabled", true);
                            console.log($(this).attr('id'));
                        }else {
                            console.log($(this).attr('id'));
                        }
                    }
                );
                $(".modify-post").each(
                    function(){
                        if($(this).attr('id') != "post-" +localStorage.getItem("logged-user")){
                            $(this).prop("hidden", true);
                        }else{
                            console.log($(this).attr('id'));
                        }
                    }
                );
                $(".add-post-form").submit(
                    function (e){

                        let req = {
                            text : $('input[name=text]').val(),
                            user : localStorage.getItem('logged-user')
                        };
                        $.ajax({
                            type : "POST",
                            contentType: "application/json",
                            url: URL + "save/" + localStorage.getItem("logged-user") + "/" + ${currentTopicId},
                            data: JSON.stringify(req),
                            dataType: 'json',
                            cache: false,
                            timeout: 600000,
                            success :  () => {
                                alert("Succesfully saved the comment!")
                            }
                        });
                        e.preventDefault();
                        location.reload();
                    }
                );
            }
        );
    </script>

</body>
</html>
