package hello.login.domain.member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;

// 저장소
@Slf4j
@Repository
public class MemberRepository {

    private static Map<Long, Member> store = new HashMap<>(); // static 사용
    private static Long sequence = 0L; //static 사용

    public Member save(Member member){
        member.setId(++sequence);
        log.info("save : member={}", member);
        store.put(member.getId(), member);
        return member;
    }
    // 아이디 찾기
    public Member findById(Long id){
        return store.get(id);
    }

    // 로그인 아이디로 찾기 (Optional => 빈 통이라 생각하면 됨 값이 널로 반환하는 경우 사용)
    public Optional<Member> findByLoginId(String loginId){
       /* List<Member> all = findAll();
        for (Member m : all) {
            if (m.getLoginId().equals(loginId)) {
                return  Optional.of(m);
            }
        }
        return Optional.empty();*/
        // 람다식으로 표현 위에 코드랑 동일
        return findAll().stream()
                .filter(m -> m.getLoginId().equals(loginId))
                .findFirst();
    }

    //전체 찾기
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
