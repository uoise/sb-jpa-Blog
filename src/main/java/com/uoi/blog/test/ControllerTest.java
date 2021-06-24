package com.uoi.blog.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerTest {

@GetMapping("/test/hello")
    public String test(){
    return "<h1>hello spring boot</h1>";
    }
}