package coview.coview.repository;

import coview.coview.domain.JoinMeeting;
import coview.coview.domain.Meeting;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MeetingRepository {
    private final EntityManager em;

    public void save(Meeting meeting){
        em.persist(meeting);
    }

    public Meeting findOne(Long id){
        return em.find(Meeting.class, id);
    }

    public List<Meeting> findAll(){
        return em.createQuery("select m from Meeting m", Meeting.class)
                .getResultList();
    }

    // 회의 참여하는 멤버 조회하기 위해 현재 meetingId와 연관된 joinMeetings 조회
//    public Meeting findFetchMeeting(Long meetingId){
//        return em.createQuery("select m from Meeting m join fetch m.joinMeetings j" +
//                                "where m.id=:id", Meeting.class)
//                .setParameter("id", meetingId)
//                .getSingleResult();
//    }



}
