function navigateTo(id){
    window.location.href = "http://localhost:8080/topics/" + id + "?userId=" +localStorage.getItem("logged-user");
}
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
        $('#add-topic-form').submit(
            function (e) {
                let userId = localStorage.getItem("logged-user");
                const req = {
                    user : parseInt(userId),
                    subject: $('input[name=subject]').val(),
                    posts : []
                };
                console.log(req);
                $.ajax({
                    type: "POST",
                    contentType: "application/json",
                    url: "http://localhost:8080/topic-entity/add/" + userId,
                    data: JSON.stringify(req),
                    dataType: 'json',
                    cache: false,
                    timeout: 600000,
                    success: () => {
                        alert("Succesfully save the topic!");
                        location.reload();
                    }
                });

                e.preventDefault();
            }
        );
    }
);