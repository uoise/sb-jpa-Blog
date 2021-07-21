let index={
    init: function(){
        $("#btn-save").on("click",()=>{
            this.save();
        });
    },

    save: function (){
        //alert('user의 save 함수 호출됨');
        let data={
            title: $("#title").val(),
            content: $("#content").val()
        };
        $.ajax({
            // 회원가입 수행 요청(100초)
            type: "POST",
            url: "/api/board",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf8",
            dataType: "json"
        }).done(function(resp){
            // 정상이면 이부분 실행
            alert("글쓰기가 완료되었습니다.");
            location.href="/";
        }).fail(function(error){
            // 실패하면 이부분 실행
            alert(JSON.stringify(error));
        });
    },
}

index.init();