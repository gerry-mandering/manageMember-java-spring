package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

// 구현체
public class MemoryMemberRepository implements MemberRepository {

    // 동시성 문제는 고려하지 않은 설계, 실무에서는 ConcurrentHashMap과 AtomicLong을 사용한다.
    private static Map<Long, Member> store = new HashMap<>(); // 저장되는 메모리 DB
    // ? <키값: Long Id, 밸류값: Member 객체> 멤버 객체에도 ID가 저장되어 있는데 이러는 이유
    private static long sequence = 0L; // 0, 1, 2 순서대로 키값을 생성해주는 역할

    @Override
    public Member save(Member member) { // member 객체를 파라미터로 받아옴 회원 이름만 있는 상태?
        member.setId(++sequence); // 시스템 Id를 추가하기 위해 sequence를 이용해 Id를 부여
        store.put(member.getId(), member); // 부여된 Id를 키값으로 사용하고 받아온 member 객체를 밸류값으로 store에 저장
        return member; // 저장된 member 객체를 반환
    }

    @Override
    public Optional<Member> findById(Long id) { // 시스템 Id를 받아 멤버를 반환해주는 메소드
        return Optional.ofNullable(store.get(id)); // Null이 반환될 가능성이 있으면 Optional로 감싸줌 해당 member객체를 반환
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream() // store에 저장된 값들 중에서
                .filter(member -> member.getName().equals(name)) // member.getName()이 파라미터로 넘어온 name과 같은거만 필터링 되어서 그 member를 반환
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); // store에 있는 멤버들을 다 List형태로 가져옴
    }

    public void clearStore() {
        store.clear();
    } // store에 저장된 모든 값 제거
}
