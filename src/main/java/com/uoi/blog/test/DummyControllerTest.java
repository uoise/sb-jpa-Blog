package com.uoi.blog.test;

import com.uoi.blog.model.RoleType;
import com.uoi.blog.model.User;
import com.uoi.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

// http파일이 아닌 data를 리턴해주는 controller
@RestController
public class DummyControllerTest {

    @Autowired // DI
    private UserRepository userRepository;
    
    // {id}주소로 파라미터 전달받기 가능
    // http://localhost:8000/blog/dummy/user/3
    @GetMapping("/dummy/user/{id}")
    public User detail(@PathVariable int id){
        // 없는 데이터 탐색시, user가 null이 됨
        // Optional 로 User 객체를 감싸서 리턴함
        User user = userRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("해당 사용자가 없습니다.");
        });
        // req : webbrowser
        // user 객체 : java obj 
        // 변환 json (Gson lib같은걸로 함), sb는 MessageConvertor 가 jackson lib을 통해 json return
        return user;
    }

    // http://localhost:8000/blog/dummy/join (요청)
    // http의 body에 username, password, email 데이터를 가지고 (요청)
    @PostMapping("/dummy/join")
    public String join(User user){
        System.out.println("id : " +user.getId());
        System.out.println("username : "+ user.getUsername());
        System.out.println("password : "+ user.getPassword());
        System.out.println("email : "+ user.getEmail());
        System.out.println("role : " + user.getRole());
        System.out.println("createDate : "+ user.getCreateDate()); // 자바에서 시간을 받고 import

        user.setRole(RoleType.USER);
        userRepository.save(user);

        return "회원가입이 완료되었습니다.";
    }
}
