package com.uoi.blog.controller.api;

import com.uoi.blog.dto.ResponseDto;
import com.uoi.blog.model.RoleType;
import com.uoi.blog.model.User;
import com.uoi.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserApiController {


    @Autowired
    private UserService userService;

    @PostMapping("/auth/joinProc")
    public ResponseDto<Integer> save(@RequestBody User user){
        System.out.println("UAC.save() 호출");
        userService.회원가입(user); // 1이면 성공, 0이면 실패
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); // java obj -> json
    }

}
