package hello.hellospring.domain;

import javax.persistence.*;

// 회원 객체
@Entity //Jpa가 매핑하는 entity가 됨

public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) //DB가 알아서 Id를 생성해주는 방식
    private Long id; // 시스템이 저장하는 ID

//    @Column(name = "username") DB에 있는 colum명이 "username"인 경우
    private String name; // 회원의 이름

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
