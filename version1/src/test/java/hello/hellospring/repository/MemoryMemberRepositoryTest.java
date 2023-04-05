package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository(); // MemoryMemberRepository 클래스의 인스턴스 repository를 생성

    @AfterEach
    public void afterEach() {
        repository.clearStore(); // @Test를 돌때마다 실행시켜줘서 리포지토리를 비움, 정확히는 repository.store 해시맵을 비움
    }

    @Test
    public void save() { // 회원을 저장
        Member member = new Member(); // 회원객체 생성
        member.setName("spring"); // 생성된 회원객체의 name을 "spring"으로 저장

        repository.save(member); // repository.store 해시맵에 생성된 "spring" 멤버를 저장

        Member result = repository.findById(member.getId()).get(); // 빈 회원객체 result를 새로 생성, 방금 저장된 멤버를 가져와 저장
        assertThat(member).isEqualTo(result); // 저장한 member와 불러온 result를 비교해서 서로 다르면 에러 출력
    }

    @Test
    public void findByName() {
        Member member1 = new Member(); //회원객체 member1을 생성
        member1.setName("spring1"); // member1.name을 "spring1"으로 설정
        repository.save(member1); // repository.store에 "spring1"이라는 이름이 들어간 member1 저장

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get(); // 저장된 member1을 이름으로 찾아서 불러와 result에 저장
        assertThat(result).isEqualTo(member1); // 저장한 member1과 불러온 result를 비교
    }

    @Test
    public void findAll() {
        Member member1 = new Member(); // 회원객체 member1을 생성
        member1.setName("spring1"); // member1.name을 "spring1"으로 설정
        repository.save(member1); // repository.store에 "spring1"이라는 이름이 들어간 member1 저장

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll(); // 멤버 리스트 result에 findAll()을 통해 repository.store 해시맵의 밸류값들을 불러와 저장

        assertThat(result.size()).isEqualTo(2); // 멤버 리스트 result의 크기가 저장한 2명사이즈가 맞는지 확인
    }
}
