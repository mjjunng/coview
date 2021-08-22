package coview.coview.repository;

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
}
