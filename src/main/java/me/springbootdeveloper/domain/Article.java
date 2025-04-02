package me.springbootdeveloper.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter //클래스 필드에 대해 별도 코드 없이 모든 필드에 대한 접근자 메서드 생성 가능
@NoArgsConstructor(access = AccessLevel.PROTECTED) //접근 제어자가 protected인 기본 생성자 생성
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = true)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Builder //빌더 패턴으로 객체 생성(어느 필드에 어느 값이 매핑되는지 바로 알 수 있음)
    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    //@Getter, @NoArgsConstructor로 대체
//    protected Article() { //기본 생성자
//
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public String getContent() {
//        return content;
//    }
}
