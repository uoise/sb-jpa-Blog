package com.uoi.blog.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HttpControllerTest {

    public static final String TAG = "HttpControllerTest : ";

    @GetMapping("/http/lombok")
    public String lombokTest(){
        Member m = Member.builder().username("ssar").password("1234").email("uuu@gmail.com").build() ;
        System.out.println("getter : " + m.getUsername());
        m.setUsername("cos");
        System.out.println("setter : "+m.getUsername());
        return "lombok test";
    }

    @GetMapping("/http/get")
    public String getTest(){
        return "get test";
    }
    @PutMapping("/http/put")
    public String putTest(){
        return "put test";
    }
    @PostMapping("/http/post")
    public String postTest(){
        return "post test";
    }
    @GetMapping("/http/delete")
    public String deleteTest(){
        return "delete test";
    }
}
