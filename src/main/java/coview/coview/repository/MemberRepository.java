package coview.coview.repository;

import coview.coview.domain.JoinMeeting;
import coview.coview.domain.Meeting;
import coview.coview.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    public void save(Member member){
        em.persist(member);
    }

    public Member findOne(Long memberId){
        return em.find(Member.class, memberId);
    }

    public List<Member> findAll(){
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public List<Member> findByEmail(String email){
        return em.createQuery("select m from Member m " +
                        "where m.email=:email", Member.class)
                .setParameter("email", email)
                .getResultList();
    }

    public Member findByEmailAndPassword(String email, String pwd){
        try {
            return em.createQuery("select m from Member m " +
                            "where m.email=:email and m.password=:pwd", Member.class)
                    .setParameter("email", email)
                    .setParameter("pwd", pwd)
                    .getSingleResult();
        } catch (Exception e){
            return null;
        }
    }

    public List<Member> findById(Long memberId){
        return em.createQuery("select distict m from Member m join fetch m.meetings" +
                        "where m.id=:id", Member.class).setParameter("id", memberId)
                .getResultList();

    }

}
