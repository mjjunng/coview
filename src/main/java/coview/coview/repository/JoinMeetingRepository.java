package coview.coview.repository;

import coview.coview.domain.JoinMeeting;
import coview.coview.service.MeetingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class JoinMeetingRepository {

    private final EntityManager em;

    public void save(JoinMeeting meeting){
        em.persist(meeting);
    }

    public JoinMeeting findOne(Long id){
        return em.find(JoinMeeting.class, id);
    }

    public List<JoinMeeting> findAll(){
        return em.createQuery("select m from JoinMeeting m", JoinMeeting.class)
                .getResultList();
    }

    public List<JoinMeeting> findByIds(Long meetingId, Long memberId){
        return em.createQuery("select j from JoinMeeting j " +
                "where j.meeting.id=:meetingId and j.member.id=:memberId", JoinMeeting.class)
                .setParameter("meetingId", meetingId)
                .setParameter("memberId", memberId)
                .getResultList();
    }

}
