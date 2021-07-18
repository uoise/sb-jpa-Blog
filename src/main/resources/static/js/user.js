let index={
    init: function(){
        $("#btn-save").on("click",()=>{ // function 을 사용하지 않는 이유 : this를 바인딩 하기 위해서!!
            this.save();
        });
    },

    save: function (){
        //alert('user의 save 함수 호출됨');
        let data={
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val()
        };
        //console.log(data);

        // ajax 호출시 default가 비동기 호출
        // ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청
        // ajax가 통신을 성공 후, json을 리턴하면 자동으로 java obj로 변환됨 <-신기
        $.ajax({
            // 회원가입 수행 요청(100초)
            type: "POST",
            url: "/auth/joinProc",
            data: JSON.stringify(data), // http body data
            contentType: "application/json; charset=utf8", // body data type(MIME)
            dataType: "json" // 요청을 서버로해서 응답이 왔을때 기본적으로 모든 것이 String(생긴 모양이 json일때) -> js obj로 변경

        }).done(function(resp){
            // 정상이면 이부분 실행
            alert("회원가입이 완료 되었습니다.");
            //console.log(resp);
            location.href="/";
        }).fail(function(error){
            // 실패하면 이부분 실행
            alert(JSON.stringify(error));
        });
    },
}

index.init();