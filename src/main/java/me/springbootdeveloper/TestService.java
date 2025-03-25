package me.springbootdeveloper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//비즈니스 계층
@Service
public class TestService {

    @Autowired
    MemberRepository memberRepository;

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }
}
