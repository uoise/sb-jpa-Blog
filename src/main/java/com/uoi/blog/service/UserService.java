package com.uoi.blog.service;

import com.uoi.blog.model.RoleType;
import com.uoi.blog.model.User;
import com.uoi.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


// 스프링이 컴포넌트 스캔을 통해서 Bean에 등록해줌, IoC
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;



    @Transactional
    public void 회원가입(User user) {
        String rawPassword = user.getPassword();
        String encPassword = encoder.encode(rawPassword);
        user.setPassword(encPassword);
        user.setRole(RoleType.USER);
        userRepository.save(user);
    }
    
    @Transactional
    public void 회원수정(User user){
        // 수정시 영속성 컨텍스트 User 오브젝트를 영속화 시키고, 영속화된 User 오브젝트를 수정
        // Select를 해서 User 오브젝트를 가져오는 이유는 영속화 하기 위해서
        // 영속화된 오브젝트를 변경시 자동으로 DB에 update <- 더티체킹
        User persistence = userRepository.findById(user.getId())
                .orElseThrow(()->{
                    return new IllegalArgumentException("회원 찾기 실패");
                });
        String rawPassword = user.getPassword();
        String encPassword = encoder.encode(rawPassword);
        persistence.setPassword(encPassword);
        persistence.setEmail(user.getEmail());
        // 회원 수정 함수 종료시 = 서비스 종료 = 트랜잭션 종료 = commit 수행
        // persistance의 변화 감지시 더티체킹으로 db update문 날림

        // 세션 등록

    }
}
