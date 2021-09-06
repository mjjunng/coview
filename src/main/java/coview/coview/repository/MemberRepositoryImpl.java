package coview.coview.repository;

import coview.coview.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl {
    private final EntityManager em;

        public Member findOne(Long memberId){
        return em.find(Member.class, memberId);
    }
}
