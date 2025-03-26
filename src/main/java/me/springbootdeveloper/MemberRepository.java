package me.springbootdeveloper;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//Member클래스와 member테이블을 매핑하는 코드(DB에서 데이터를 가져오는 퍼시스턴트 계층)
@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByName(String name);
}
