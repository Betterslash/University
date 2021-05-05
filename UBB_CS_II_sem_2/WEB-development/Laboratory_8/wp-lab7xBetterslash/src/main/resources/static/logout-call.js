$(document).ready(
    function (){
        $("#logout-form").submit(
            function (e){
                $.ajax({
                    type : "POST",
                    contentType: "application/json",
                    url: "http://localhost:8080/users/logout/" + localStorage.getItem("logged-user"),
                    cache: false,
                    timeout: 600000,
                    success :  (data) => {
                        alert(data)
                    }
                });
                window.location.href = "http://localhost:8080"
                localStorage.clear();
                e.preventDefault();
            }
        );
    }
);