package com.uoi.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

// ORM -> java (다른언어) object -> 테이블로 매핑해주는 기술
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder // 빌더 패턴 나중에 설명
@Entity //User 클래스가 각 필드를 읽어서 MySql에 테이블 생성 됨
public class User {

    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //프로젝트에서 연결된 db의 넘버링 전략을 따라감
    private int id; // 시퀀스, auto_increment

    @Column(nullable = false, length = 30)
    private String username; // id

    @Column(nullable = false, length = 100) // 해쉬(비밀번호 암호화)
    private String password;

    @Column(nullable = false, length = 50)
    private String email;

    @ColumnDefault("'user'")
    private String role; // enum을 쓰는 게 좋음 -> 도메인 생성, string일 경우 오타가 생길수 있음
    // 도메인 = 범위가 정해짐,

    @CreationTimestamp // 시간이 자동으로 입력 됨
    private Timestamp createDate; // update도 필요하나 안 만듬
} //id, Timestamp는 자동
