package coview.coview.service;

import coview.coview.domain.JoinMeeting;
import coview.coview.domain.Meeting;
import coview.coview.domain.Member;
import coview.coview.repository.JoinMeetingRepository;
import coview.coview.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class JoinMeetingService {
    private final JoinMeetingRepository joinMeetingRepository;

    /**
     * JoinMeeting 저장
     */
    @Transactional
    public Long save(JoinMeeting joinMeeting){
        joinMeetingRepository.save(joinMeeting);
        return joinMeeting.getId();
    }

    /**
     * 조회
     */
    public JoinMeeting findOne(Long id){
        return joinMeetingRepository.findOne(id);
    }

    public List<JoinMeeting> findAll(){ return joinMeetingRepository.findAll(); }

    public List<JoinMeeting> findByIds(Long meetingId, Long memberId){
        return joinMeetingRepository.findByIds(meetingId, memberId);
    }


}
