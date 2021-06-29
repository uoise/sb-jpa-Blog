package com.uoi.blog.test;

import com.uoi.blog.model.RoleType;
import com.uoi.blog.model.User;
import com.uoi.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

// http파일이 아닌 data를 리턴해주는 controller
@RestController
public class DummyControllerTest {

    @Autowired // DI
    private UserRepository userRepository;

    // password, email만 수정
    @Transactional
    @PutMapping("/dummy/user/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User requestUser){
    // json 데이터로 요청 @RequestBody
    // jackson lib이 java obj로 변환
        System.out.println("id : "+ id);
        System.out.println("password : "+ requestUser.getPassword());
        System.out.println("email : "+ requestUser.getEmail());

        User user = userRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("수정에 실패하였습니다");
        });
        user.setPassword(requestUser.getPassword());
        user.setEmail(requestUser.getEmail());
//        userRepository.save(user);
        // save 함수는 id를 전달하지 않거나, id에 대한 데이터가 없으면 insert, 있으면 update
        return user;
    }// @Transactional -> 더티 체킹, 값만 변경하면 save필요없이 변경됨
    // 함수 종료(리턴)시 자동 commit

    // http://localhost:8000/blog/dummy/user/
    @GetMapping("/dummy/users")
    public List<User> list(){
        return userRepository.findAll();
    }

    // http://localhost:8000/blog/dummy/user/3
    // 한 페이지 당 2건의 데이터 리턴, 최신순으로 정렬
    @GetMapping("/dummy/user")
    public List<User> pageList(@PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC)Pageable pageable){
        Page<User> pagingUser = userRepository.findAll(pageable);
        // Page<>를 통해 isFirst, isLast등의 로직 사용가능
        List<User> users = pagingUser.getContent();
        return users;
    }

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
