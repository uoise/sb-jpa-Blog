package com.uoi.blog.controller.api;

import com.uoi.blog.dto.ResponseDto;
import com.uoi.blog.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApiController {

    @PostMapping("/api/user")
    public ResponseDto<Integer> save(@RequestBody User user){
        System.out.println("UAC.save() 호출");
        return new ResponseDto<Integer>(HttpStatus.OK, 1);
    }
}
