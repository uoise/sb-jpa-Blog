package com.uoi.blog.controller.api;

import com.uoi.blog.dto.ResponseDto;
import com.uoi.blog.model.User;
import com.uoi.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class UserApiController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @PostMapping("/auth/joinProc")
    public ResponseDto<Integer> save(@RequestBody User user){
        System.out.println("UAC.save() 호출");
        userService.회원가입(user); // 1이면 성공, 0이면 실패
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); // java obj -> json
    }

    @PutMapping("/user")
    public ResponseDto<Integer> update(@RequestBody User user){
        userService.회원수정(user);
        // 트랜잭션 종료로 db값은 변경되나 세션값은 변경안됨
        // 강제로 Token 통해서 Authentication 만들어 
        // Session 에 SecurityContext.SecurityContextHolder 에 넣는 방식 불가능
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }
}
