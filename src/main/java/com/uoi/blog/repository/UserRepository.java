package com.uoi.blog.repository;

import com.uoi.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

// DAO
// 자동으로 bean 등록됨
// @Repository // 생략가능하다.
public interface UserRepository extends JpaRepository<User, Integer> {

}
