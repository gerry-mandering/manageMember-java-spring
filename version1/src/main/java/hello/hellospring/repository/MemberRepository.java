package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member); // 회원을 저장하면 저장된 회원이 반환됨
    Optional<Member> findById(Long id); // 시스템 아이디로 회원을 찾음
    Optional<Member> findByName(String name); // 회원의 이름으로 회원을 찾음
    List<Member> findAll(); // 모든 회원을 찾아서 List로 반환옴
}
