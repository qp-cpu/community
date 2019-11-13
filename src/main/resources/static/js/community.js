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
                alert(response.message);
            }
            console.log(response)
        },
        dataType: 'json'
    });
    console.log(comment_content);
    console.log(questionId);
}