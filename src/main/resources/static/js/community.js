function post() {
    var questionId = $("#question_id").val();
    var comment_content = $("#comment_content").val();

    $.ajax({
        type: "post",
        url: "/comment",
        contentType: 'application/json',
        data: JSON.stringify({
            parentId: questionId,
            comtent: comment_content,
            type: 1
        }),
        success: function (response) {
            if(response.code == 200)
            {
                $("#comment_section").hide();
            }
            else {
                if(response.code==300)
                {
                  var confirm1 = confirm(response.message);
                  if(confirm1)
                  {
                      window.open("https://github.com/login/oauth/authorize?client_id=079686b431e42084be6c&redirect_uri=http://localhost:8887/callback&scope=user&state=1")
                      window.localStorage.setItem("closeable",true);
                  }
                }
                else {
                    alert(response.message);
                }
            }
        },
        dataType: 'json'
    });
    console.log(comment_content);
    console.log(questionId);

}
