package com.uoi.blog.service;

import com.uoi.blog.model.User;
import com.uoi.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

// 스프링이 컴포넌트 스캔을 통해서 Bean에 등록해줌, IoC
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void 회원가입(User user) {
        userRepository.save(user);
    }
}
