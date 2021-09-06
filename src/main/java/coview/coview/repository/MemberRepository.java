package coview.coview.repository;

import coview.coview.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//@Repository
//@RequiredArgsConstructor
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);



//    private final EntityManager em;

//    public void save(Member member){
//        em.persist(member);
//    }
//
//    public Member findOne(Long memberId){
//        return em.find(Member.class, memberId);
//    }
//
//    public List<Member> findAll(){
//        return em.createQuery("select m from Member m", Member.class)
//                .getResultList();
//    }
//
//
//    public Member findByEmail(String email){
//        return em.createQuery("select m from Member m " +
//                        "where m.email=:email", Member.class)
//                .setParameter("email", email)
//                .getSingleResult();
//    }
//
//    public Member findByEmailAndPassword(String email, String pwd){
//        try {
//            return em.createQuery("select m from Member m " +
//                            "where m.email=:email and m.password=:pwd", Member.class)
//                    .setParameter("email", email)
//                    .setParameter("pwd", pwd)
//                    .getSingleResult();
//        } catch (Exception e){
//            return null;
//        }
//    }
//
////    public Member findById(Long memberId){
////        return em.createQuery("select district m from Member m join fetch m.meetings" +
////                        "where m.id=:id", Member.class).setParameter("id", memberId)
////                .getSingleResult();
////
////    }
//
//    public List<Member> findTotalMember(Long joinMeetingId){
//        return em.createQuery("select m from Member join m.meetings t" +
//                "where t.id=:id", Member.class).setParameter("id", joinMeetingId)
//                .getResultList();
//    }

//    public Auth findMemberAuth(Long memberId){
//        return (Auth) em.createQuery("select m.auth from Member m " +
//                "where id=:id").setParameter("id", memberId)
//                .getSingleResult();
//    }


}
