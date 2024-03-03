package com.example.apitest.repository.member;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.apitest.domain.member.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);

    Optional<Member> findByEmailAndPassword(String email, String password);

    //todo 인덱스 관련해서 문제가 없으려나?
    Optional<Member> findByLoginToken(String loginToken);

    boolean existsByEmail(String email);
}
