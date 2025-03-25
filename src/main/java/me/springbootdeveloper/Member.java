package me.springbootdeveloper;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

//DB에 접근할때 사용할 객체
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id; //DB테이블의 'id'컬럼과 매칭

    @Column(name = "name", nullable = false)
    private String name; //DB테이블의 'name'컬럼과 매칭
}
