package com.uoi.blog.repository;

import com.uoi.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

// DAO
// 자동으로 bean 등록됨
// @Repository // 생략가능하다.
public interface UserRepository extends JpaRepository<User, Integer> {

    // SELECT * user WHERE username = 1?;
    Optional<User> findByUsername(String username);

}
// JPA Naming 쿼리 전략
// SELECT * FROM user WHERE username = ? AND password = ?;
// parameter 를 통해 쿼리문 자동 수행
// User findByUsernameAndPassword(String username, String password);

// @Query(value="SELECT * FROM user WHERE username = ?1 AND password = ?2", nativeQuery = true)
// User login(String username, String password);
// 간단한 로직이라서 네이티브 쿼리 쓸필요없음