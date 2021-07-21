package com.uoi.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
    private int id;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob //대용량 데이터
    private String content; // summernote lib, <html>섞여서 디자인

    private int count;

    @ManyToOne // many boards to one user
    @JoinColumn(name="userId")
    private User user; // DB는 오브젝트를 저장할 수 없음, 따라서 FK 사용. 자바는 오브젝트를 저장할 수 있다.
    // orm을 사용하면 오브젝트가 fk로 저장됨

    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER) // 연관관계의 주인이 아니다. (fk가 아니다), db에 칼럼을 만들지 말것
    private List<Reply> reply;
    // fk가 필요 없음, replyid를 더 추가하여 가질수 없음, 1정규화 깨짐 -> 원자성
    
    @CreationTimestamp
    private Timestamp createDate;
}
