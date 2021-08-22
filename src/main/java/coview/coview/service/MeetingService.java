package coview.coview.service;

import coview.coview.domain.Meeting;
import coview.coview.repository.MeetingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MeetingService {

    private final MeetingRepository meetingRepository;

    /**
     *
     * 회의 저장
     */
    @Transactional
    public Long save(Meeting meeting){
        meetingRepository.save(meeting);
        return meeting.getId();
    }

    /**
     * 회의 조회
     */
    public Meeting findOne(Long meetingId){
        return meetingRepository.findOne(meetingId);
    }

    public List<Meeting> findAll(){
        return meetingRepository.findAll();
    }
}
