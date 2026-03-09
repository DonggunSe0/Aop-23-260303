package com.back.domain.member.service;

import com.back.domain.member.entity.Member;
import com.back.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member join(String username, String nickname) {
        return memberRepository.save(new Member(username, nickname)); //자체 트랜잭션이 있어서, save() 메서드가 실행될 때 트랜잭션이 자동으로 시작되고,
        // 메서드가 끝나면 트랜잭션이 커밋돼. 그래서 별도의 @Transactional 어노테이션이 없어도 돼.
    }

    public long count() {
        return memberRepository.count();
    }

    public Optional<Member> findByUsername(String username) {
        return memberRepository.findByUsername(username);
    }
}
