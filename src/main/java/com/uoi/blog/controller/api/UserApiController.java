package com.uoi.blog.controller.api;

import com.uoi.blog.dto.ResponseDto;
import com.uoi.blog.model.RoleType;
import com.uoi.blog.model.User;
import com.uoi.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class UserApiController {

    @Autowired
    private UserService userService;

    @PostMapping("/api/user")
    public ResponseDto<Integer> save(@RequestBody User user){
        System.out.println("UAC.save() 호출");
        user.setRole(RoleType.USER);
        userService.회원가입(user); // 1이면 성공, 0이면 실패
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); // java obj -> json
    }

    @PostMapping("/api/user/login")
    public ResponseDto<Integer> login(@RequestBody User user, HttpSession session){
        System.out.println("UAC: login() 호출");
        User principal = userService.로그인(user); // principal (접근주체)

        if(principal != null){
            session.setAttribute("principal", principal);
        } // 세션도 @AutoWired로 변수 선언해서도 DI가능
        // 그냥 Parameter로도 가능
        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }
}
