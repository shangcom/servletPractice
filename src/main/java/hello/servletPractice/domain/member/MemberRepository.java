package hello.servletPractice.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberRepository {

    // 데이터 저장소. 회원 ID와 회원(member) 정보(id, username, age)를 매핑하여 저장.
    private Map<Long, Member> store = new HashMap<>();

    // 회원 아이디(가입 순서대로 1씩 늘어남)
    private static long sequence = 0L;

    // 싱글톤 패턴. MemberRepository의 유일한 인스턴스.
    private static final MemberRepository instance = new MemberRepository();

    public static MemberRepository getInstance() {
        return instance;
    }

    private MemberRepository() {
    }

    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id) {
        return store.get(id);
    }

    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
