package com.uoi.blog.controller;

import com.uoi.blog.config.auth.PrincipalDetail;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

    @GetMapping({"","/"})
    public String index(@AuthenticationPrincipal PrincipalDetail principal){ // 컨트롤러에서 어떻게 세션을 찾는지?
        System.out.println("id: "+principal.getUsername());
        return "index";
    }
    // 세션 접근시 @AuthenticationPrincipal PrincipalDetail principal
}
