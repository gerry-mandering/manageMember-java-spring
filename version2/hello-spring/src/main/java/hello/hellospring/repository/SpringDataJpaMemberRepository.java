package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    //JPQL select m from Member m where m.name = ?
    //이름 만으로 쿼리를 자동으로 짜줌 findByNameAndId(String name, Long id);
    @Override
    Optional<Member> findByName(String name);
}
